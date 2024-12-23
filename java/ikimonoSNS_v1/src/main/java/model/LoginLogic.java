package model;

import beans.Users;
import dao.UsersDAO;

public class LoginLogic {
    public Users execute(String email, String password) {

        try {
            // UsersDAOのインスタンス作成時にconnectionを渡す必要がなくなる
            UsersDAO usersDAO = new UsersDAO(); 
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