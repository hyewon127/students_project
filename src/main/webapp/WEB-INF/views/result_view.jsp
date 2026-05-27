<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>등록 결과</title>
    <style>
        body { font-family: 'Pretendard', sans-serif; background: #f4f7f6; display: flex; justify-content: center; padding-top: 50px; }
        .result-box { background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 400px; text-align: center; }
        table { width: 100%; margin: 20px 0; border-collapse: collapse; }
        th, td { padding: 10px; border-bottom: 1px solid #eee; }
        .btn { display: block; padding: 12px; background: #0984e3; color: white; text-decoration: none; border-radius: 8px; margin-top: 20px; }
    </style>
</head>
<body>
<div class="result-box">
    <h2>등록 완료!</h2>
    <p style="color: #636e72;">${msg}</p>
    
    <table>
        <tr><th>과목</th><th>점수</th></tr>
        <tr><td>국어</td><td>${student.korean}</td></tr>
        <tr><td>영어</td><td>${student.english}</td></tr>
        <tr><td>수학</td><td>${student.math}</td></tr>
        <tr><td>과학</td><td>${student.science}</td></tr>
        <tr>
            <td colspan="2" style="background: #f8f9fa;">
                <strong>평균 점수:</strong> <fmt:formatNumber value="${student.average}" pattern="0.00" />점<br>
                <strong>성적 등급:</strong> ${student.grade}
            </td>
        </tr>
    </table>
    
    <a href="manage" class="btn">학생 목록 관리 페이지로 이동</a>
</div>
</body>
</html>