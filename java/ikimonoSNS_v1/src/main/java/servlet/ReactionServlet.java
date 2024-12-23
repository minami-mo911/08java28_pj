package servlet;

import java.io.IOException;

import beans.Reaction;
import beans.Users;
import dao.ReactionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReactionServlet")
public class ReactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // セッションからUserオブジェクトを取得
        Users loginUser = (Users) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("index.jsp"); // 未ログインの場合はリダイレクト
            return;
        }

        // パラメータからタスクIDと絵文字を取得
        int postId = Integer.parseInt(request.getParameter("postId"));
        String emoji = request.getParameter("emoji");

        // Reactionデータを生成
        Reaction reaction = new Reaction(postId, loginUser.getUserName(), emoji);

        // DAOを呼び出してデータベースに保存
        ReactionDAO dao = new ReactionDAO();
        boolean isSuccess = false;

        // addReactionメソッドの呼び出しをtry-catchで囲む
        try {
            isSuccess = dao.addReaction(reaction);  // Exceptionがスローされる可能性があるため
        } catch (Exception e) {
            e.printStackTrace();
            // エラーメッセージをリクエストに設定
            request.setAttribute("errorMsg", "リアクションの追加中にエラーが発生しました");
            request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
            return;  // エラーハンドリング後に処理を終了
        }

        // 処理結果に応じて遷移
        if (isSuccess) {
            response.sendRedirect("Main"); // 成功時はMainにリダイレクト
        } else {
            request.setAttribute("errorMsg", "リアクションの追加に失敗しました");
            request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
        }
    }
}