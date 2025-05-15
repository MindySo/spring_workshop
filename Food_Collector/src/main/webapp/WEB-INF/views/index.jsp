<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>메인 페이지 입니다.</h2>
	<ul>

		밥 검색
		<form method="get" action="${pageContext.request.contextPath}/food">
			<input type="text" name="name" />
			<button>검색</button>
		</form>

		<a href="${pageContext.request.contextPath}/food/allergic">알러지정보</a>
	</ul>
</body>
</html>












