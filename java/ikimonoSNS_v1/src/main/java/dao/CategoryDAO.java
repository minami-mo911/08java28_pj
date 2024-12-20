package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import util.DBUtil;

public class CategoryDAO {
	
	public List<Category> findAll() {
		
        List<Category> categoryList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT category_id, category_name FROM category_source";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                categoryList.add(new Category(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return categoryList;
    }
}