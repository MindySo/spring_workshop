<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>hellopage</title>
</head>
<body>
	<h1>Hello Hello</h1>
	
	<c:if test="${not empty loginUser }">
		${loginUser.id}님 환영합니다.
		<a href="/logout">로그아웃</a>
	</c:if>

	<c:if test="${empty loginUser }">
		<a href="/login">로그인</a>
		<a href="/signIn">회원가입</a>
	</c:if>
</body>
</html>