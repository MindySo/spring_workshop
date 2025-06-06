<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div>
	<!-- 1. 로그인 X -->
	<c:if test="${empty loginUser}">
		<a href="/login" class="btn btn-outline-warning">로그인</a>	
		<a href="/signup" class="btn btn-outline-primary">회원가입</a>	
	</c:if>
	<!-- 2. 로그인 O -->
	<c:if test="${not empty loginUser}">
		${loginUser }님 반갑습니다.
		<a href="/logout" class="btn btn-outline-danger">로그아웃</a>	
		<c:if test="${'admin' eq loginUser }">
			<a href="/users">관리페이지</a>
		</c:if>
	</c:if>
</div>