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
		${loginUser.id}�� ȯ���մϴ�.
		<a href="/logout">�α׾ƿ�</a>
	</c:if>

	<c:if test="${empty loginUser }">
		<a href="/login">�α���</a>
		<a href="/signIn">ȸ������</a>
	</c:if>
</body>
</html>