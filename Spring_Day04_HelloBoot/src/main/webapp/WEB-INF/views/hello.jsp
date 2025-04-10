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
		<a href="/login">�α���������</a>
	</c:if>

	<c:if test="${not empty loginUser }">
		<span>${loginUser}�� �ݰ����ϴ�.</span>
		<a href="/logout">�α׾ƿ�</a>
	</c:if>

	<h2>Hello JSP</h2>
	<h3>${msg }</h3>
</body>
</html>