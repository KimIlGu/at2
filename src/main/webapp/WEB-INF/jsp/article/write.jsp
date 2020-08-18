<%@ page import="java.util.List"%>
<%@ page import="com.sbs.kig.at.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 작성" />

<%@ include file="../part/head.jspf"%>
<!-- 기존 방식 < 
%
List<Article> counts = (List<Article>)request.getAttribute("count");
List<Article> articles = (List<Article>)request.getAttribute("articles");
%
>
-->
<script>
	function ArticleWriteForm__submit(form) {
		form.title.value = form.title.value.trim();

		if (form.title.value.legnth == 0) {
			form.title.focus();
			alert('제목을 입력해주세요.')

			return;
		}

		form.body.value = form.body.value.trim();

		if (form.body.value.legnth == 0) {
			form.body.focus();
			alert('내용을 입력해주세요.')

			return;
		}

		form.submit();
	}
</script>

<form class="table-box con" action="doWrite" method="POST"
	onsubmit="ArticleWriteForm__submit(this); return false;">
	<input type="hidden" name="redirectUri" value="/usr/article/detail?id="/>
	<table>
		<tbody>
			<tr>
				<th>제목</th>
				<td>
					<div class="form-control-box">
						<input type="text" placeholder="제목을 입력해주세요." name="title"
							maxlength="100" />
					</div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<div class="form-control-box">
						<textarea placeholder="내용을 입력해주세요." name="body" maxlength="2000"></textarea>
					</div>
				</td>
			</tr>

			<tr>
				<th>작성</th>
				<td>
					<button class="btn btn-primary" type="submit">작성</button>
				</td>
			</tr>
		</tbody>
	</table>
</form>
<%@ include file="../part/foot.jspf"%>