<%@ page import="com.sbs.java.blog.util.Util"%>
<%@ page import="com.sbs.java.blog.util.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
List<Article> articles = (List<Article>)request.getAttribute("count");
List<Article> articles = (List<Article>)request.getAttribute("articles");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	
	<c:forEach items="${articles}" var="article">
		<div>
			${article.id}
			${article.regDate}
			${article.updateDate}
			${article.title}
			${article.body}
		</div>
	</c:forEach>	
	
</body>
</html>