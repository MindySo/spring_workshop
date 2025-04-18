<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>메인 페이지 입니다.</h2>
	<ul>
		
		<li><a href="<c:url value="/board/list" />">자유게시판</a></li>
		<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:otherwise>		
		</c:choose>
	</ul>
</body>
</html>












