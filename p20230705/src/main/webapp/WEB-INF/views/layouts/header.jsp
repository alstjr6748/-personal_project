<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/menu.css">
</head>
<body>
	<nav id="topMenu">
		<ul>
			<li><a class="menuLink" href="main.do">Home</a></li>
			<li><a class="menuLink" href="#">Book</a></li>
			<li><a class="menuLink" href="#">Content</a></li>
			<c:if test="${author eq 'ADMIN' }">
				<li><a class="menuLink" href="#">Member</a></li>
			</c:if>
			<c:if test="${empty id }">
				<li><a class="menuLink" href="#">Join</a></li>
			</c:if>
			<c:if test="${empty id }">
				<li><a class="menuLink" href="#">Login</a></li>
			</c:if>
			<c:if test="${not empty id }">
				<li><a class="menuLink" href="#">MyPage</a></li>
				<li><a class="menuLink" href="#">Logout</a></li>
				<li>${name }님 접속중</li>
			</c:if>
		</ul>
	</nav>
</body>
</html>