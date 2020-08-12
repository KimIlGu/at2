<%@ page import="java.util.List"%>
<%@ page import="com.sbs.kig.at.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세내용" />

<%@ include file="../part/head.jspf"%>
<!-- 기존 방식 < 
%
List<Article> counts = (List<Article>)request.getAttribute("count");
List<Article> articles = (List<Article>)request.getAttribute("articles");
%
>
-->
<div class="table-box con">
	<table>
		<tbody>
			<tr>
				<th>번호</th>
				<td>${article.id}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${article.regDate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${article.body}</td>
			</tr>
		</tbody>
	</table>
</div>

<%@ include file="../part/foot.jspf"%>