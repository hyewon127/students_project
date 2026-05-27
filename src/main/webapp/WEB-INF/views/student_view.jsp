<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생 성적 등록</title>
    <style>
        body { font-family: 'Pretendard', -apple-system, sans-serif; background-color: #f4f7f6; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .form-container { background: #ffffff; padding: 40px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.1); width: 100%; max-width: 400px; }
        h3 { color: #2d3436; text-align: center; margin-bottom: 30px; font-weight: 700; }
        input { width: 100%; padding: 12px; margin: 8px 0; border: 2px solid #e1e8ed; border-radius: 8px; box-sizing: border-box; font-size: 15px; transition: 0.3s; }
        input:focus { border-color: #0984e3; outline: none; }
        .btn-submit { width: 100%; padding: 14px; background-color: #0984e3; color: white; border: none; border-radius: 8px; font-size: 16px; font-weight: 600; cursor: pointer; margin-top: 15px; transition: 0.3s; }
        .btn-submit:hover { background-color: #74b9ff; }
        .footer-link { text-align: center; margin-top: 20px; font-size: 14px; color: #636e72; }
        .footer-link a { color: #0984e3; text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>

<div class="form-container">
    <h3>학생 성적 등록</h3>
    <form method="post" action="students" accept-charset="UTF-8">
        <input type="text" name="name" placeholder="학생 이름" required>
        <input type="text" name="studentNumber" placeholder="학번 (예: 2026001)" required>
        <input type="number" name="korean" placeholder="국어 점수" required>
        <input type="number" name="english" placeholder="영어 점수" required>
        <input type="number" name="math" placeholder="수학 점수" required>
        <input type="number" name="science" placeholder="과학 점수" required>
        <button type="submit" class="btn-submit">데이터 등록하기</button>
    </form>
    <div class="footer-link">
        <a href="manage">전체 학생 목록 보기</a>
    </div>
</div>

</body>
</html>