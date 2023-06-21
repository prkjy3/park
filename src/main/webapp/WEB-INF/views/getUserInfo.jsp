<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>유저 정보</title>
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
    </style>
</head>
<body>

    <div style="text-align:center; padding-top: 50px;">
        <h1>유저 정보</h1>
        <br>
        <p>id: ${USERINFO.user_id}</p>
        <p>이메일: ${USERINFO.email}</p>
        <p>유저소개글</p>
        <p>${USERINFO.user_introduce}</p>
        
        <div class="button-container">
        </div>
        <c:if test="${USERINFO.user_id == role.user_id}">
      
    <a href="updateUserForm?user_id=${role.user_id}">회원정보 수정</a>
    <a href="deleteUserForm">회원탈퇴</a>
</c:if>
    </div>

</body>
</html>
