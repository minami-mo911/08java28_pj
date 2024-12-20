package model;

import java.util.List;

import beans.Post;
import dao.PostDAO;

public class GetPostListLogic {
	public List<Post> execute() {
		PostDAO dao = new PostDAO();
		List<Post> postList = dao.findAll();
		
		return postList;
	}
}
