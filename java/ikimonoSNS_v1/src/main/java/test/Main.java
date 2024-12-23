package test;

import beans.Post;
import model.PostLogic;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(java.time.LocalDateTime.now().toString());
		
		
		
		
		Post post = new Post();
		post.setUserId("nekoneko222");
		post.setCaption("ネコがこたつでまるくなっている！");
		post.setImageUrl("uploads/cat_1.jpg");
		
		
		PostLogic logic = new PostLogic();
		boolean success = logic.execute(post, 2);
		
		if (success == true) {
            // 投稿処理が失敗した場合のエラー対応
            System.out.println("成功しました");
        } else if (success == false){
        	System.out.println("失敗しました");
        }
		

	}

}
