<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.Users, beans.Post, beans.Category, model.CategoryLogic, java.util.List" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    Users loginUser = (Users)session.getAttribute("loginUser");
    List<Post> postList = (List<Post>)application.getAttribute("postList");
    String errorMsg = (String)request.getAttribute("errorMsg");
    
 	// カテゴリー取得ロジック
    CategoryLogic categoryLogic = new CategoryLogic();
    List<Category> categories = categoryLogic.getCategories();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>いきものそーしゃる</title>
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
    <link rel="stylesheet" href="/ikimonoSNS_v1/css/style.css">
</head>
<body>
    <!-- 上部のコンテナ: ユーザー情報と更新ボタン -->
    <div class="container upper-container">
        <h1>あなた</h1>
        <p> ${loginUser.userName} さん： Now Login</p>
        <p>
            <a href="Main" class="submit">更新する</a>　　
            <a href="Logout" class="submit">ログアウト</a>
        </p>
    </div>

    <!-- 下部のコンテナ: 投稿フォームとタイムライン -->
    <div class="container lower-container">
        <h1>タイムライン</h1>
        
    <form action="Post" method="post" enctype="multipart/form-data" class="post-form">
	    
	    	
	    <div class="form-group">
	        <input type="text" name="caption" class="textarea" placeholder="今日のいきものはどう？">
	    </div>
	    
	    <div class="form-group">
	        <label for="categorySelect">カテゴリーを選択:</label>
	        
	        <select id="categorySelect" name="categoryId" required>
	            <c:forEach var="category" items="${categories}">
	                <option value="${category.id}">${category.name}</option>
	            </c:forEach>
	        </select>
	    </div>
	    
	    <div class="form-group">
	        <label for="imageUpload" class="file-label">画像をアップロード:</label>
	        <input type="file" id="imageUpload" name="image" accept="image/*" class="file-input">
	    </div>
	    <input type="hidden" name="action" value="post">
	    <div class="form-group">
	        <input type="submit" value="投稿する" class="submit">
	    </div>
	</form>
        
        <c:if test="${not empty errorMsg}">
            <p><c:out value="${errorMsg}" /></p>
        </c:if>

        <!-- 投稿の一覧表示 -->
        
        <c:forEach var="post" items="${postList}">
            <div class="post1ken">
                <p>投稿ID： ${post.id}</p>
                <p>ユーザーID： ${post.userId}</p>
                <p>ユーザー名： ${post.userName} さん</p>
                <p>キャプション： ${post.caption}</p>
                <p>投稿日時: ${post.dateTime}</p>
                <c:if test="${not empty post.imageUrl}">
                    <img src="${pageContext.request.contextPath}/${post.imageUrl}" alt="post picture" style="max-width: 100%; border-radius: 8px;">
                </c:if>
                <p>カテゴリ： ${post.category}</p>
                <p>リアクション数： ${post.totalStamp}</p>
                
                
           

                <!-- リアクションボタン -->
                <!--
                <form action="ReactionServlet" method="post" class="reaction-form">
                    <input type="hidden" name="postId" value="${post.id}" />
                    <button type="submit" name="emoji" value="👍" class="reaction-btn">👍</button>
                    <button type="submit" name="emoji" value="❤️" class="reaction-btn">❤️</button>
                    <button type="submit" name="emoji" value="😂" class="reaction-btn">😂</button>
                </form>
                
                -->
            </div>
            <hr>
        </c:forEach> 
    </div>
</body>
</html>
