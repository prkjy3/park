<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 정보 수정</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .info-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
        background-color: #F7F7F7;
        border: 1px solid #DDD;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px #CCC;
    }

    .button-container {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .button-container a {
        display: block;
        margin: 0 10px;
        padding: 10px 20px;
        background-color: #007BFF;
        color: #FFF;
        text-decoration: none;
        border-radius: 5px;
    }

    .button-container a:hover {
        background-color: #0069D9;
    }

    .logo {
        position: absolute;
        top: 20px;
        left: 20px;
        font-size: 24px;
        font-weight: bold;
    }
</style>
</head>
<body>
    <div class="logo">
        <a href="main.jsp">메인화면</a>
    </div>
    <div class="info-container">
        <h1>유저 정보 수정</h1>
		   	<form action="updateUser.do" method="post">
			    <table>
			        <tr>
			            <td>아이디</td>
			            <td>
			            	${USERINFO.user_id} 님
			            	<input id = "user_id"name ="user_id" value="${USERINFO.user_id}" type="hidden">
			            </td>
			        </tr>
			        <tr>
			            <td>이메일</td>
			            <td>
			                <input id = "email" type="email" value="${USERINFO.email}" name ="email">
			            </td>
			        </tr>
			        <tr>
			            <td>자기소개</td>
			            <td>
			                <textarea id = "user_introduce" name="user_introduce" maxlength="100" required>${USERINFO.user_introduce}</textarea>
			            </td>
			        </tr>
			        <tr>
			            <td>비밀번호 확인</td>
			            <td>
			                <input id = "password" type="password" name="password">
			            </td>
			        </tr>
			        <tr>
			            <td>
			                <input type="submit" value="수정하기"></td>
			                <td>
			                <input type="reset" value="다시작성">
			            </td>
			        </tr>
			    </table>
			</form>

        <div class="button-container">
            <a href="main">메인화면</a>
        </div>
    </div>
</body>
</html>
