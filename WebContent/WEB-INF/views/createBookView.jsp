<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h2>書籍の編集</h2>

	<form method="POST" action="${pageContext.request.contextPath}/book/add">
	
		<table>
			
			
			<tr>
				<td>書籍名</td>
				<td><input type="text" name="name" value="${book.name}" ></td>
			</tr>
			<tr>
				<td>出版社名</td>
				<td><input type="text" name="publisher" value="${book.publisher}"></td>
			</tr>
			<tr>
				<td>ページ数</td>
				<td><input type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="page" value="${book.page}"></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
       
      
       <a href="${pageContext.request.contextPath}/book">戻る</a>
      
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>