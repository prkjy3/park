<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<div align="center">
	<br>
	<h2>유저 탈퇴</h2>
	<p>정말로 탈퇴하시겠습니까?<br>
	${role.user_id}님?</p><br>
	<form action="deleteUser.do" method="POST">
		<input type="hidden" name="user_id" value="${role.user_id}">
		비밀번호확인<input type="password" name="password" > <!-- 유저 아이디를 여기에 입력 -->
		<input type="submit" value="탈퇴하기">
	</form>
	</div>
</body>
</html>