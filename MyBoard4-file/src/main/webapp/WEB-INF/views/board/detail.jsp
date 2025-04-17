<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자유게시판 - 상세보기</title>
    <style>
        .board-detail {
            width: 800px;
            margin: 20px auto;
        }
        .board-header {
            border-top: 2px solid #333;
            border-bottom: 1px solid #ddd;
            padding: 15px;
        }
        .title {
            font-size: 1.5em;
            margin-bottom: 10px;
        }
        .info {
            color: #666;
            font-size: 0.9em;
        }
        .content {
            min-height: 200px;
            padding: 20px;
            border-bottom: 1px solid #ddd;
        }
        .attachments {
            padding: 15px;
            background-color: #f8f8f8;
            border-bottom: 1px solid #ddd;
        }
        .attachment-item {
            margin: 5px 0;
        }
        .attachment-item a {
            color: #0066cc;
            text-decoration: none;
        }
        .attachment-item a:hover {
            text-decoration: underline;
        }
        .buttons {
            margin-top: 20px;
            text-align: right;
        }
        .btn {
            padding: 8px 15px;
            margin-left: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-list {
            background-color: #f8f8f8;
            color: #333;
        }
        .btn-edit {
            background-color: #4CAF50;
            color: white;
        }
        .btn-delete {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
    <div class="board-detail">
        <h2>자유게시판 - 상세보기</h2>
        
        <div class="board-header">
            <div class="title">${board.title}</div>
            <div class="info">
                <span>${board.regDate}</span> | 
                <span>조회 ${board.viewCnt}</span>
            </div>
        </div>

        <div class="content">
            ${board.content}
        </div>

        <div class="attachments">
            <h4>첨부파일</h4>
            <c:forEach var="boardFile" items="${board.boardFiles }">
            	<div class="attachment-item">
            		<a href="다운로드링크">${boardFile.originalName }</a>
            		<span>(${boardFile.fileSize })</span>
            	</div>
            	<c:if test="${empty board.boardFiles }">
            		<p>첨부된 파일이 없습니다.</p>
            	</c:if>
            </c:forEach>
        </div>

        <div class="buttons">
        	<a href="update?no=${board.no}">수정</a>
			<a href="delete?no=${board.no}">삭제</a>
			<a href="list">목록</a>
        </div>
    </div>
</body>
</html>