<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>書籍の編集</h2>


      
	<c:if test="${not empty book}">
         <form method="POST" action="${pageContext.request.contextPath}/book/edit">
         <input type="hidden" name="id" value="${book.id}" />
		<table>
			<th>
			<td></td>
			<td></td>
			<td></td>
			</th>
			<tr>
				<td>書籍名</td>
				<td><input type="text" name="name" value="${book.name}"></td>
			</tr>
			<tr>
				<td>出版社名</td>
				<td><input type="text" name="publisher" value="${book.publisher}"></td>
			</tr>
			<tr>
				<td>ページ数</td>
				<td><input type="text" name="page" value="${book.page}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	 </c:if>
	<a href="../book">戻る</a>
</body>
</html>