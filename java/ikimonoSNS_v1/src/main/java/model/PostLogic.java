package model;

import beans.Post;
import dao.PostDAO;

public class PostLogic {
    public boolean execute(Post post, int categoryId) {
        PostDAO dao = new PostDAO();
        return dao.create(post, categoryId);
    }
}
