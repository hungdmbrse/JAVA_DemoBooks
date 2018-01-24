<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>書籍の編集</h2>

	<form action="com.demobookstore/BookEdit">
		<table>
			<th>
			<td></td>
			<td></td>
			</th>
			<tr>
				<td>書籍名</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>出版社名</td>
				<td><input type="text" name="bookname"></td>
			</tr>
			<tr>
				<td>ページ数</td>
				<td><input type="text" name="bookpage"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	<a href="book.jsp">戻る</a>
</body>
</html>