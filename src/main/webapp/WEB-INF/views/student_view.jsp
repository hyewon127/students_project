<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>학생 정보 및 성적 관리 시스템</h1>
<p>학생 정보와 성적을 입력하세요! </p>

<!-- input value를 post으로 보냄 -->
<form method = "post" action="students">
	<!-- 성적 입력 받는 html 생성 -->
	이름<input type = "text" name = "name"><br>
	학번<input type = "text" name = "student_number"><br>
	국어 점수<input type = "text" name = "korean"><br>
	영어 점수<input type = "text" name = "english"><br>
	수학 점수<input type = "text" name = "math"><br>
	
	<button type="submit">submit</button>
</form>
<hr>

<c:if test="${not empty msg}">
    <h3>처리 결과: ${msg}</h3>
</c:if>

	
</body>
</html>