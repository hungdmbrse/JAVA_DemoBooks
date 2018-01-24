<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h2>書籍の一覧</h2>

	<a href="${pageContext.request.contextPath}/book/add">追加</a>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<TH>ID</TH>
			<TH>書籍名</TH>
			<TH>出版社名</TH>
			<TH>ページ数</TH>
			<TH>修正</TH>
		</tr>
		<c:forEach items="${bookList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.name}</td>
				<td>${book.publisher}</td>
				<td>${book.page}</td>
				<td><a href="book/edit?id=${book.id}">修正</a> <a
					href="deleteBook?id=${book.id}">削除</a> <a
					href="impression?bookid=${book.id}">感想の一覧</a></td>
			</tr>
		</c:forEach>
	</table>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

