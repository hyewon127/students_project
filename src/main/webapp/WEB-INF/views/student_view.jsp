<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학사 관리 시스템</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; padding: 20px; }
        .container { width: 100%; max-width: 450px; background: #ffffff; padding: 30px; border-radius: 12px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #2c3e50; margin-bottom: 20px; }
        h3 { color: #34495e; border-bottom: 2px solid #eee; padding-bottom: 5px; margin-top: 25px; }
        form { display: flex; flex-direction: column; gap: 12px; margin-bottom: 20px; }
        input { padding: 12px; border: 1px solid #ced4da; border-radius: 6px; font-size: 14px; }
        button { padding: 12px; border: none; border-radius: 6px; cursor: pointer; font-weight: bold; transition: 0.3s; }
        .btn-submit { background-color: #007bff; color: white; }
        .btn-submit:hover { background-color: #0056b3; }
        .btn-delete { background-color: #dc3545; color: white; }
        .btn-delete:hover { background-color: #a71d2a; }
        .result-box { padding: 15px; background: #e9ecef; border-radius: 6px; border-left: 5px solid #28a745; margin-top: 10px; font-size: 0.9em; }
    </style>
</head>
<body>

<div class="container">
    <h1>학사 관리 시스템</h1>

    <h3>학생 정보 등록</h3>
    <form method="post" action="students">
        <input type="text" name="name" placeholder="이름" required>
        <input type="text" name="student_number" placeholder="학번" required>
        <input type="text" name="korean" placeholder="국어 점수" required>
        <input type="text" name="english" placeholder="영어 점수" required>
        <input type="text" name="math" placeholder="수학 점수" required>
        <input type="text" name="science" placeholder="과학 점수" required>
        <button type="submit" class="btn-submit">데이터 등록</button>
    </form>

    <h3>학생 정보 삭제</h3>
    <form method="post" action="delete">
        <input type="text" name="Student_number" placeholder="삭제할 학번 입력" required>
        <button type="submit" class="btn-delete">삭제하기</button>
    </form>

    <h3>학생 성적 조회</h3>
    <form method="post" action="select">
        <input type="text" name="Student_number" placeholder="조회할 학번 입력" required>
        <button type="submit" class="btn-submit" style="background-color: #28a745;">조회하기</button>
    </form>

    <c:if test="${not empty msg}">
        <div class="result-box">등록 결과: ${msg}</div>
    </c:if>

    <c:if test="${not empty delete}">
        <div class="result-box" style="border-left-color: #dc3545;">삭제 결과: ${delete}</div>
    </c:if>

    <c:if test="${not empty select}">
        <div class="result-box" style="border-left-color: #ffc107;">
            <c:choose>
                <c:when test="${select.status == 'success'}">
                    <strong>[조회 결과]</strong><br>
                    이름: ${select.name} (${select.student_number})<br>
                    국어: ${select.korean}점 | 영어: ${select.english}점 | 수학: ${select.math}점 | 과학: ${select.science}점<br>
                    평균: ${select.average}점, 등급: ${select.grade}
                </c:when>
                <c:otherwise>
                    <p style="color: red;">${select.message}</p>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</div>

</body>
</html>