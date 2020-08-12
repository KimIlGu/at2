<%@ page import="java.util.List"%>
<%@ page import="com.sbs.kig.at.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<c:set var="pageTitle" value="게시물 리스트" />

<%@ include file="../part/head.jspf" %>
<!-- 기존 방식 < 
%
List<Article> counts = (List<Article>)request.getAttribute("count");
List<Article> articles = (List<Article>)request.getAttribute("articles");
%
>
-->
<div class="table-box con">
	<table>
		<colgroup>
			<col width="100"/>
			<col width="200"/>
		</colgroup>
	
		<thead>
			<tr>
				<td>번호</td>
				<td>작성일</td>
				<td>제목</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles}" var="article">
				<tr>
					<td>${article.id}</td>
					<td>${article.regDate}</td>
					<td><a href="detail?id=${article.id}">${article.title}</a></td>
				</tr>
		</c:forEach>
		</tbody>
	</table>	
</div>
	
<%@ include file="../part/foot.jspf" %>