package model;

import java.sql.Connection;

import beans.Users;
import dao.UsersDAO;
import util.DBUtil;

public class LoginLogic {
	public Users execute(String email, String password) {
		
		try (Connection connection = DBUtil.getConnection()) {
            UsersDAO usersDAO = new UsersDAO(connection);
            Users user = usersDAO.findUserByEmail(email);
            
            // メールアドレスが一致し、パスワードも正しい場合
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // ログイン失敗時
    }
}