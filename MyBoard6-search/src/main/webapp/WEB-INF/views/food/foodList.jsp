<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>음식 목록 + 영양 정보</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #eee;
        }
        .nutri-table {
            margin: 10px 0 30px 0;
            width: 90%;
        }
    </style>
</head>
<body>
    <h2>음식 목록</h2>
    
    <c:forEach var="food" items="${foods}">
        <h3>${food.fdNm} (${food.fdCode})</h3>
        <table>
            <tr>
                <th>대분류</th>
                <td>${food.upperFdGroupNm}</td>
                <th>중분류</th>
                <td>${food.fdGroupNm}</td>
            </tr>
            <tr>
                <th>중량(g)</th>
                <td>${food.fdWgh}</td>
                <th>재료 수</th>
                <td>${food.foodCnt}</td>
            </tr>
            <tr>
                <th>이미지</th>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${not empty food.imageAddress}">
                            <img src="${food.imageAddress}" alt="이미지" width="100" />
                        </c:when>
                        <c:otherwise>
                            없음
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>

        <!-- ✅ 영양 정보 테이블 -->
        <c:if test="${not empty food.foodNutri}">
            <table class="nutri-table">
                <thead>
                    <tr>
                        <th>에너지(kcal)</th>
                        <th>단백질(g)</th>
                        <th>지방(g)</th>
                        <th>탄수화물(g)</th>
                        <th>나트륨(mg)</th>
                        <th>칼륨(mg)</th>
                        <th>칼슘(mg)</th>
                        <th>철(mg)</th>
                        <th>비타민C(mg)</th>
                        <th>비타민B1(mg)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="nutri" items="${food.foodNutri}">
                        <tr>
                            <td>${nutri.energy_Qy}</td>
                            <td>${nutri.prot_Qy}</td>
                            <td>${nutri.ntrfs_Qy}</td>
                            <td>${nutri.carbohydrate_Qy}</td>
                            <td>${nutri.na_Qy}</td>
                            <td>${nutri.ptss_Qy}</td>
                            <td>${nutri.clci_Qy}</td>
                            <td>${nutri.irn_Qy}</td>
                            <td>${nutri.vtmn_C_Qy}</td>
                            <td>${nutri.vtmn_B1_Qy}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <hr/>
    </c:forEach>
</body>
</html>
