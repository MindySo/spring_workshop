<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>새 메뉴 등록</h2>
	<form method="post" 
		  enctype="multipart/form-data"
		  action="update">
		  <input type="hidden" name="no" value=${food.no }>
		<div>
			<label>메뉴명</label>
			<input type="text" name="title" value="${food.menu}"/>
		</div>
		<div>
			<label>가격</label>
			<input type="text" name="content" value="${food.cost}"/>
		</div>
		<div>
			<label>카테고리</label>
			 <label>
		      	<input type="radio" name="category" value="한식" ${food.category == '한식' ? 'checked' : ''}>

		     	 한식
		    </label><br>
		
		    <label>
				<input type="radio" name="category" value="일품" ${food.category == '일품' ? 'checked' : ''}>
		      	일품
		    </label><br>
		</div>
		<div>
			<label>설명</label>
			<input type="text" name="title" value="${board.content}"/>
		</div>
		
		<button>등록</button>
		<button><a href="list">취소</a></button>
	</form>
</body>
</html>

















