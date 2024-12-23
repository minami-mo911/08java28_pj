package model;

import beans.Post;
import dao.PostDAO;

public class PostLogic {
    public boolean execute(Post post, int categoryId) {
    	
        boolean isInserted = false;

        // デバッグ用ログ
        System.out.println("投稿処理を開始します。");
        System.out.println("投稿データ: ユーザーID=" + post.getUserId() + ", キャプション=" + post.getCaption() + ", 画像URL=" + post.getImageUrl());

        try {
            PostDAO dao = new PostDAO();
            isInserted = dao.create(post, categoryId);

            if (isInserted) {
                System.out.println("投稿が正常にデータベースに挿入されました。");
            } else {
                System.out.println("投稿のデータベース挿入に失敗しました。");
            }
        } catch (Exception e) {
            System.out.println("投稿処理中に例外が発生しました: " + e.getMessage());
            e.printStackTrace();
        }

        return isInserted;
    	

    }
}
