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
	<h2>자유게시판 - 목록</h2>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.no}</td>
			<td><a href="detail?no=${board.no}">${board.title}</a></td>
			<td>${board.viewCnt}</td>
			<td>${board.regDate}</td>
		</tr>
		</c:forEach>
	</table>
	
	
	<a href="detail">상세</a>	


	<a href="write">등록</a>	
	
	
</body>
</html>




