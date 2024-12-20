package model;

import java.util.List;

import beans.Category;
import dao.CategoryDAO;

public class CategoryLogic {
    public List<Category> getCategories() {
        CategoryDAO dao = new CategoryDAO();
        return dao.findAll();
    }
}