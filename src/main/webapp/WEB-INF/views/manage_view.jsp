<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생 목록 및 관리</title>
    <style>
        .container { max-width: 800px; margin: auto; padding: 20px; font-family: sans-serif; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: center; }
        th { background-color: #f8f9fa; }
        .btn-small { padding: 6px 12px; cursor: pointer; border-radius: 4px; border: 1px solid #ccc; background: #fff; }
        .btn-action { padding: 10px 20px; cursor: pointer; border-radius: 6px; border: none; background: #eee; }
        .btn-action:hover { background: #ddd; }
    </style>
</head>
<body>
<div class="container">
    <h1>학생 목록 및 관리</h1>
    
    <table>
        <tr>
            <th>학번</th>
            <th>이름</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="s" items="${list}">
            <tr>
                <form action="update" method="post" style="display:contents;">
                    <td>
                        <input type="text" name="studentNumber" value="${s.student_number}" readonly style="border:none; text-align:center; background:transparent;">
                    </td>
                    <td><input type="text" name="name" value="${s.name}"></td>
                    <td><button type="submit" class="btn-small">수정</button></td>
                </form>
                
                <td>
                    <form action="delete" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                        <input type="hidden" name="studentNumber" value="${s.student_number}">
                        <button type="submit" class="btn-small">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div style="text-align: center; margin-top: 30px;">
        <button class="btn-action" onclick="location.href='students'">정보 입력하기</button>
    </div>

    <c:if test="${not empty delete}">
        <p style="text-align:center; color: #d63031; margin-top: 20px;">삭제 결과: ${delete}</p>
    </c:if>
    <c:if test="${not empty update}">
        <p style="text-align:center; color: #0984e3; margin-top: 20px;">수정 결과: ${update}</p>
    </c:if>
</div>
</body>
</html>