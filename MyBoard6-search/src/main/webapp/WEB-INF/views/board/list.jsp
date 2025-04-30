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
	<form method="get" action="list">
		<select name="searchKey">
			<option value="">항목선택</option>
			
			<option value="title" <c:if test="${param.searchKey == 'title'}"> selected</c:if>>제목</option>
			<option value="content" <c:if test="${param.searchKey == 'content'}"> selected</c:if>>내용</option>
			<option value="both" <c:if test="${param.searchKey == 'both'}"> selected</c:if>>제목+내용</option>
		</select>
		<input type="text" name="searchWord" value="${param.searchWord}" />
		<button>검색</button>
	</form>
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
			<td><a href="detail?no=${board.no}&searchKey=${param.searchKey}&searchWord=${param.searchWord}">${board.title}</a></td>
			<td>${board.viewCnt}</td>
			<td>${board.regDate}</td>
		</tr>
		</c:forEach>
	</table>
	
	밥 검색
	<form method="get" action="${pageContext.request.contextPath}/food">
		<input type="text" name="name" />
		<button>검색</button>
	</form>
	
	<a href="detail">상세</a>	


	<a href="write">등록</a>	
	
	
</body>
</html>




