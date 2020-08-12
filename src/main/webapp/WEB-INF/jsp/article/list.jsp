<%@ page import="java.util.List"%>
<%@ page import="com.sbs.kig.at.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!-- 기존 방식 < 
%
List<Article> counts = (List<Article>)request.getAttribute("count");
List<Article> articles = (List<Article>)request.getAttribute("articles");
%
>
-->

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
			${article.id} <br />
			${article.regDate} <br />
			${article.updateDate} <br />
			${article.title} <br />
			${article.body} <br />
		</div>
	</c:forEach>	
	
</body>
</html>