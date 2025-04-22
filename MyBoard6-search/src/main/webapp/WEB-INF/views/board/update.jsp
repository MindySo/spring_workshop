<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자유게시판 - 수정</h2>
	
	<form method="post" enctype="multipart/form-data" action="update">
	<div>
			<label>제목</label>
			<input type="text" name="title" value="${board.title }"/>
		</div>
		<div>
			<label>내용</label>
			<input type="text" name="content" value="${board.content }"/>
		</div>
		<div>
			<input type="hidden" name="no" value="${board.no }"/>
		</div>
		<button type="submit">수정</button>
	</form>	
	
</body>
</html>




