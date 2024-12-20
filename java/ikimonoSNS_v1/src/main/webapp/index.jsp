<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いきものそーしゃる</title>
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="/ikimonoSNS_v1/css/style.css">
</head>
<body>
	<div class="container line-height-center">
    <h1>いきものそーしゃるへようこそ！</h1>
    
    <form action="Login" method="post">
    メールアドレス <input type="email" name="email" class="form" placeholder="メールアドレスを入力してください"><br>
    パスワード <input type="password" name="password" class="form" placeholder="パスワードを入力してください"><br>
    <input type="submit" value="ログイン" class="submit">
</form>
    </div>
</body>
</html>
