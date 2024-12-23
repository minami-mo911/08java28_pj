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
import model.CategoryLogic;
import model.GetPostListLogic;


@WebServlet("/Main")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 投稿リスト取得
        GetPostListLogic getPostListLogic = new GetPostListLogic();
        List<Post> postList = getPostListLogic.execute();
        request.setAttribute("postList", postList);

        // ▼デバッグ用ログ▼
        System.out.println("投稿リストのサイズ: " + postList.size());
        for (Post post : postList) {
            System.out.println("投稿ID: " + post.getId() + ", キャプション: " + post.getCaption());
        }

        // カテゴリーリスト取得
        CategoryLogic categoryLogic = new CategoryLogic();
        List<Category> categoryList = categoryLogic.getCategories();
        if (categoryList == null || categoryList.isEmpty()) {
            System.out.println("カテゴリーが取得できませんでした");
        }
        request.setAttribute("categories", categoryList);

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
}