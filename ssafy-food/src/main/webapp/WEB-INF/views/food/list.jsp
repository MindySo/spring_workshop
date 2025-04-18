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
	<h2>싸피 점심 메뉴</h2>
	<button><a href="write">새 메뉴 추가</a></button>
	<table>
		<tr>
			<th>메뉴명</th>
			<th>가격</th>
			<th>설명</th>
			<th>관리</th>
		</tr>
		<c:forEach var="food" items="${list}">
		<tr>
			<td>${food.menu}</td>
			<td>${food.cost}</td>
			<td>${food.content}</td>
			<td>
				<button><a href="detail?no=${food.no}">상세보기</a></button>
				<button><a href="update?no=${food.no}">수정</a></button>
				<button><a href="delete?no=${food.no}">삭제</a></button>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>




