<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Main</title>
</head>
<body>

	<c:if test="${empty loginUser }">
		<a href="/login">로그인페이지</a>
	</c:if>

	<c:if test="${not empty loginUser }">
		<span>${loginUser}님 반갑습니다.</span>
		<a href="/logout">로그아웃</a>
	</c:if>

	<h2>Hello JSP</h2>
	<h3>${msg }</h3>
</body>
</html>