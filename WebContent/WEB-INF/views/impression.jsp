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
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h2>感想の一覧</h2>
	<h3>${bookname}</h3>

	<a
		href="${pageContext.request.contextPath}/impression/add?bookid=${bookid}">追加</a>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<TH>ID</TH>
			<TH>コメント</TH>
			<TH>修正</TH>
		</tr>
		<c:forEach items="${impressionList}" var="impression">
			<tr>
				<td>${impression.id}</td>
				<td>${impression.name}</td>
				<td><a href="impression/edit?id=${impression.id}">修正</a> <a
					href="deleteImpression?id=${impression.id}">削除</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="book">戻る</a>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>