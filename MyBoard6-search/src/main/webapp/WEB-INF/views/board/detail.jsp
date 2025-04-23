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
        .reaction {
            min-height: 50px;
            padding: 20px;
            border-bottom: 1px solid #ddd;
            text-align: center;
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
            color: black;
            background-color: #f7f7f7;
            text-decoration: none;
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
        .btn.active {
		  font-weight: bold;
		  background-color: #c9c9c9;
		  border: 3px solid #a8a8a8;
		}
		.btn.disabled {
		  pointer-events: none;
		  opacity: 0.5;
		  cursor: not-allowed;
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

		<%-- <div class="reaction">
		
			<c:choose>
				<c:when test="${empty sessionScope.loginUser}">
	            	<p>리액션하려면 로그인하세요.</p>
	            </c:when>
	            <c:otherwise>
	            	<div class="reaction-buttons">

					  <!-- ✅ 추천 버튼 -->
					  <a
					    href="<c:choose>
					             <c:when test="${empty boardReaction}">
					                 newReact?no=${board.no}&reaction=true
					             </c:when>
					             <c:when test="${boardReaction.reaction}">
					                 deleteReact?no=${board.no}
					             </c:when>
					             <c:otherwise>
					                 updateReact?no=${board.no}&reaction=true
					             </c:otherwise>
					          </c:choose>"
					    class="btn <c:if test='${boardReaction.reaction}'>active</c:if>">
					    👍 추천
					  </a>
					
					  <!-- ✅ 비추천 버튼 -->
					  <a
					    href="<c:choose>
					             <c:when test="${empty boardReaction}">
					                 newReact?no=${board.no}&reaction=false
					             </c:when>
					             <c:when test='${not boardReaction.reaction}'>
					                 deleteReact?no=${board.no}
					             </c:when>
					             <c:otherwise>
					                 updateReact?no=${board.no}&reaction=false
					             </c:otherwise>
					          </c:choose>"
					    class="btn <c:if test='${not empty boardReaction and not boardReaction.reaction}'>active</c:if>">
					    👎 비추천
					  </a>
					
					</div>
	            </c:otherwise>
			</c:choose>
            
		</div> --%>
		
		
		
		<div class="reaction">
		    <c:choose>
		        <c:when test="${empty sessionScope.loginUser}">
		            <p>리액션하려면 로그인하세요.</p>
		            <div class="reaction-buttons">
		                <!-- 추천 버튼 (비활성화) -->
		                <a class="btn disabled" style="pointer-events: none; opacity: 0.5;">
		                    👍 추천 ${board.likes }
		                </a>
		                <!-- 비추천 버튼 (비활성화) -->
		                <a class="btn disabled" style="pointer-events: none; opacity: 0.5;">
		                    👎 비추천 ${board.dislikes }
		                </a>
		            </div>
		        </c:when>
		        <c:otherwise>
		            <div class="reaction-buttons">
		                <!-- 추천 버튼 -->
		                <a
		                    href="<c:choose>
		                               <c:when test='${empty boardReaction}'>
		                                   newReact?no=${board.no}&reaction=true
		                               </c:when>
		                               <c:when test='${boardReaction.reaction}'>
		                                   deleteReact?no=${board.no}
		                               </c:when>
		                               <c:otherwise>
		                                   updateReact?no=${board.no}&reaction=true
		                               </c:otherwise>
		                          </c:choose>"
		                    class="btn <c:if test='${boardReaction.reaction}'>active</c:if>">
		                    👍 추천 ${board.likes }
		                </a>
		
		                <!-- 비추천 버튼 -->
		                <a
		                    href="<c:choose>
		                               <c:when test='${empty boardReaction}'>
		                                   newReact?no=${board.no}&reaction=false
		                               </c:when>
		                               <c:when test='${not boardReaction.reaction}'>
		                                   deleteReact?no=${board.no}
		                               </c:when>
		                               <c:otherwise>
		                                   updateReact?no=${board.no}&reaction=false
		                               </c:otherwise>
		                          </c:choose>"
		                    class="btn <c:if test='${not empty boardReaction and not boardReaction.reaction}'>active</c:if>">
		                    👎 비추천 ${board.dislikes }
		                </a>
		            </div>
		        </c:otherwise>
		    </c:choose>
		</div>





		<div class="attachments">
            <h4>첨부파일</h4>
            <c:forEach var="boardFile" items="${board.boardFiles}">
            	<div class="attachment-item">
            		<a href="다운로드링크">${boardFile.originalName}</a>
            		<span>(${boardFile.fileSize})</span>
            	</div>
            </c:forEach>
            <c:if test="${empty board.boardFiles}">
            	<p>첨부된 파일이 없습니다.</p>
            </c:if>
        </div>

        <div class="buttons">
        	<a href="update?no=${board.no}">수정</a>
			<a href="delete?no=${board.no}">삭제</a>
			<a href="list?searchKey=${param.searchKey}&searchWord=${param.searchWord}">목록</a>
        </div>
    </div>
</body>
</html>





