<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자유게시판 - 등록</h2>
	<form method="post" 
		  enctype="multipart/form-data"
		  action="write">
		<div>
			<label>제목</label>
			<input type="text" name="title" />
		</div>
		<div>
			<label>내용</label>
			<input type="text" name="content" />
		</div>
		<div>
			<label>첨부파일</label>
			<input type="file" name="attach" multiple />
		</div>
		
		<button>등록</button>
	</form>
</body>
</html>

















