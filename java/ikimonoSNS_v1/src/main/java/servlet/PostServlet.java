package servlet;

import java.io.IOException;

import beans.Post;
import beans.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.ImageUploadLogic;
import model.PostLogic;

@WebServlet("/Post")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
       

        // ▼デバッグ用ログ▼
        System.out.println("受け取ったアクション: " + action);

        if ("post".equals(action)) {
            // 画像アップロード処理
            Part filePart = request.getPart("image");
            ImageUploadLogic imageUploadLogic = new ImageUploadLogic();
            String imageUrl = imageUploadLogic.uploadImage(filePart, getServletContext().getRealPath("/"));

            // ▼デバッグ用ログ▼
            System.out.println("アップロードされた画像URL: " + imageUrl);
            if (imageUrl == null || imageUrl.isEmpty()) {
                System.out.println("画像URLが設定されていません。");
            }

            // フォームデータ取得
            String caption = request.getParameter("caption");
            int categoryId = Integer.parseInt(request.getParameter("categoryId")); // カテゴリーID取得
            

            // ▼デバッグログ▼
            System.out.println("キャプション: " + caption + ", カテゴリーID: " + categoryId);

            // ログインユーザー確認
            HttpSession session = request.getSession();
            Users loginUser = (Users) session.getAttribute("loginUser");

            // ▼デバッグログ▼
            if (loginUser == null) {
                System.out.println("ログインユーザーがnullです。");
                response.sendRedirect("index.jsp"); // ログインページにリダイレクト 
                return;
            }

            // Postオブジェクト作成
            Post post = new Post();
            post.setUserId(loginUser.getUserId());
            post.setCaption(caption);
            post.setImageUrl(imageUrl);    // 画像URLが存在すれば設定
            post.setDateTime(java.time.LocalDateTime.now().toString()); // 投稿日時を現在時刻に設定

            // 投稿処理を実行
            PostLogic postLogic = new PostLogic();
            boolean success = postLogic.execute(post, categoryId);

            if (!success) {
                // 投稿処理が失敗した場合のエラー対応
                request.setAttribute("errorMsg", "投稿に失敗しました。再度お試しください。");
                request.getRequestDispatcher("/Main").forward(request, response);
                return;
            }
        }

        // 投稿後はタイムラインにリダイレクト
        response.sendRedirect("Main");
    }
}