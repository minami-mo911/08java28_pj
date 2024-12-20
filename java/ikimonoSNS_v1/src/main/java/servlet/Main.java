package servlet;

import java.io.IOException;
import java.util.List;

import beans.Category;
import beans.Post;
import beans.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.CategoryLogic;
import model.GetPostListLogic;
import model.ImageUploadLogic;
import model.PostLogic;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 投稿リスト取得
    	GetPostListLogic getPostListLogic = new GetPostListLogic();
        List<Post> postList = getPostListLogic.execute();
        request.setAttribute("postList", postList);

        
        // カテゴリーリスト取得
        CategoryLogic categoryLogic = new CategoryLogic();
        List<Category> categoryList = categoryLogic.getCategories();
        request.setAttribute("categories", categoryList);  // categoriesがnullでないか確認
        
        
        // ログイン確認
        HttpSession session = request.getSession();
        Users loginUser = (Users) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("index.jsp");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("post".equals(action)) {
        	// 画像アップロード処理
            Part filePart = request.getPart("image");
            ImageUploadLogic imageUploadLogic = new ImageUploadLogic();
            String imageUrl = imageUploadLogic.uploadImage(filePart, getServletContext().getRealPath("/"));
            
            // フォームデータ取得
            String caption = request.getParameter("caption");
            int categoryId = Integer.parseInt(request.getParameter("categoryId")); // カテゴリーID取得
            
            HttpSession session = request.getSession();
            Users loginUser = (Users) session.getAttribute("loginUser");

            
            // Postオブジェクト作成
            Post post = new Post();
            post.setUserId(loginUser.getUserId());
            post.setCaption(caption);
            post.setImageUrl(imageUrl);    // 画像URLが存在すれば設定
            post.setDateTime(java.time.LocalDateTime.now().toString()); // 投稿日時を現在時刻に設定

            // PostLogicを使って投稿処理を実行
            PostLogic logic = new PostLogic();
            boolean success = logic.execute(post, categoryId);

            if (!success) {
                // 投稿処理が失敗した場合のエラー対応
                request.setAttribute("errorMsg", "投稿に失敗しました。再度お試しください。");
                doGet(request, response);
                return;
            }
        }

        response.sendRedirect("Main");
    }
}