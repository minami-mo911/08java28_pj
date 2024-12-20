package servlet;

import java.io.IOException;

import beans.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		Users loginUser = loginLogic.execute(email, password);
		
		//ログイン成功時の処理
		if (loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
            dispatcher.forward(request, response);
            
        } else {
            response.sendRedirect("index.jsp?error=invalid");
            
        }
    }
}