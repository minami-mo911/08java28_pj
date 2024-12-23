package dao;

import java.sql.*;
import beans.Users;
import util.DBUtil;

public class UsersDAO {

    public Users findUserByEmail(String email) throws Exception {
        String sql = "SELECT user_id, user_name, mail_address, password FROM users WHERE mail_address = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String userId = rs.getString("user_id");
                    String userName = rs.getString("user_name");
                    String mailAddress = rs.getString("mail_address");
                    String password = rs.getString("password");

                    return new Users(userId, userName, mailAddress, password);
                }
            }
        }
        return null;  // ユーザーが見つからなかった場合
    }
}