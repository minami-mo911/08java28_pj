package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Users;

public class UsersDAO {
    private Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }
    
    // メールアドレスでユーザー情報を検索する
    public Users findUserByEmail(String email) throws Exception {
        String sql = "SELECT user_id user_name, mail_address, password FROM users WHERE mail_address = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	String userId = rs.getString("user_id");
                String userName = rs.getString("user_name");
                String mailAddress = rs.getString("mail_address");
                String password = rs.getString("password");
                
                return new Users(userId, userName, mailAddress, password);
            }
        }
        
        return null;  // ユーザーが見つからなかった場合
    }
}