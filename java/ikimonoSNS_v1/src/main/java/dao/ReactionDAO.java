package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Reaction;

public class ReactionDAO {
    private final String JDBC_URL = "jdbc:h2:~/desktop/workspace/08java28_pj/DB/ikimono";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    public boolean addReaction(Reaction reaction) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO REACTIONS(TASK_ID, USER_NAME, EMOJI) VALUES(?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, reaction.getTaskId());
            pStmt.setString(2, reaction.getUserName());
            pStmt.setString(3, reaction.getEmoji());
            int result = pStmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}