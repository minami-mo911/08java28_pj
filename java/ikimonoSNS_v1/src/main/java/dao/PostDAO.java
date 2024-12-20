package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Post;
import util.DBUtil;

public class PostDAO {
	
    public List<Post> findAll(){
        List<Post> postList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT * FROM view_post_details";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("POST_ID");
                String userId = rs.getString("USER_ID");
                String userName = rs.getString("USER_NAME");
                String caption = rs.getString("CAPTION");
                String dateTime = rs.getString("POST_DATE");
                String imageUrl = rs.getString("PICTURE_URL");
                String category = rs.getString("CATEGORIES");
                int totalStamp = rs.getInt("TOTAL_STAMPS");

                Post post = new Post(id, userId, userName, caption,dateTime, imageUrl, category,totalStamp);
                postList.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return postList;
    }

    public boolean create(Post post, int categoryId) {
        Connection conn = null;
        try (Connection connection = DBUtil.getConnection()) {

            // トランザクション開始
            connection.setAutoCommit(false);

            // `post_item` テーブルにデータを挿入
            String sqlPost = "INSERT INTO post_item (user_id, caption, post_date) VALUES (?, ?, ?)";
            PreparedStatement pStmtPost = connection.prepareStatement(sqlPost, PreparedStatement.RETURN_GENERATED_KEYS);
            pStmtPost.setString(1, post.getUserId());
            pStmtPost.setString(2, post.getCaption());
            pStmtPost.setTimestamp(3, java.sql.Timestamp.valueOf(post.getDateTime()));
            int postResult = pStmtPost.executeUpdate();

            if (postResult != 1) {
                connection.rollback();
                return false;
            }

            // 挿入された `post_id` を取得
            ResultSet rs = pStmtPost.getGeneratedKeys();
            int postId = -1;
            if (rs.next()) {
                postId = rs.getInt(1);
            } else {
                connection.rollback();
                return false;
            }

            // `picture` テーブルにデータを挿入（`imageUrl` がある場合のみ）
            if (post.getImageUrl() != null && !post.getImageUrl().isEmpty()) {
                String sqlPicture = "INSERT INTO picture (user_id, post_id, picture_url) VALUES (?, ?, ?)";
                PreparedStatement pStmtPicture = connection.prepareStatement(sqlPicture);
                pStmtPicture.setString(1, post.getUserId());
                pStmtPicture.setInt(2, postId);
                pStmtPicture.setString(3, post.getImageUrl());
                int pictureResult = pStmtPicture.executeUpdate();

                if (pictureResult != 1) {
                    connection.rollback();
                    return false;
                }
            }

            // `category_master` テーブルへのデータ挿入
            String sqlCategory = "INSERT INTO category_master (post_id, category_id) VALUES (?, ?)";
            PreparedStatement pStmtCategory = connection.prepareStatement(sqlCategory);
            pStmtCategory.setInt(1, postId);
            pStmtCategory.setInt(2, categoryId);
            int categoryResult = pStmtCategory.executeUpdate();

            if (categoryResult != 1) {
                connection.rollback();
                return false;
            }

            // コミットして成功を返す
            connection.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

/*

    public boolean updateGood(int id) {
    	try (Connection connection = DBUtil.getConnection()) {
            String sql = "UPDATE TWEETS SET GOOD = GOOD + 1 WHERE ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.setInt(1, id);
            int result = pStmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    */
}
