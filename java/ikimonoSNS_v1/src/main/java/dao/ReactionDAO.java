package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Reaction;
import util.DBUtil;

public class ReactionDAO {

    public boolean addReaction(Reaction reaction) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {  // DBUtilを使用
            String sql = "INSERT INTO REACTIONS(TASK_ID, USER_NAME, EMOJI) VALUES(?, ?, ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, reaction.getTaskId());
                pStmt.setString(2, reaction.getUserName());
                pStmt.setString(3, reaction.getEmoji());
                int result = pStmt.executeUpdate();
                return result == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}