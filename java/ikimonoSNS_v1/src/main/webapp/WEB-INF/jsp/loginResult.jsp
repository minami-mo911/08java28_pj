<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.Users" %>


<%
    // セッションスコープからユーザー情報を取得
    Users loginUser = (Users)session.getAttribute("loginUser");
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
    <div class="container line-height-center">
        <h1>いきものそーしゃる</h1>

        <% if(loginUser != null){ %>
            <p>ようこそ、<%= loginUser.getUserName() %>さん</p>
            <a href="Main" class="submit">見てみる</a>
        <% } else { %>
            <p>ログインできませんでした</p>
            <a href="index.jsp" class="submit">TOPへ</a>
        <% } %>
    </div>
</body>
</html>
