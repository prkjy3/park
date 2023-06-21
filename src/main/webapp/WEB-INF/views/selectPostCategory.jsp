<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>${DTO.category_name} 정보</title>
<style type="text/css">
p{
	background:black;
}
</style>
	<style>
		.navbar-brand {
			font-size: 1.5rem;
		}
		.navbar-login {
			width: 25%;
		}
		p{
			background:black;
		}
	</style>
</head>
<body>

		<h1><a href="main">게임커뮤니티</a></h1>

	<p>
<br>
<br>
<br>
</p>
	<!-- 메뉴바 -->
	<!-- 로그인 -->
		<c:if test="${role != null}">
    <!-- 사용자 아이디와 마이페이지 버튼을 보여줍니다. -->
   		    반갑습니다 ${roel.user_id }님<br>
   <a href="/mypage">${role.user_id}</a>
   <a href="getUserInfo?user_id=${role.user_id}">회원 정보</a>
   <a href="logout.do">로그아웃</a>
</c:if>
<c:if test="${role == null}">
    <!-- 로그인 폼과 로그인 버튼을 보여줍니다. -->
    <form action="login.do" method="post">
        <input type="text" name="user_id" placeholder="아이디" name="user_id"><br>
        <input type="password" name="password" placeholder="비밀번호" name="password">
        <input type="submit" value="로그인">
        <a class="nav-link" href="registerForm">회원가입</a>
    </form>
</c:if>

<!-- 카테고리 정보 테이블 -->
<center>
<form action="updatePostCategory.do" method="post">
<table border=0  width="600">
<tr>
<td colspan=2 align="center" ><h1>${DTO.category_name} 정보</h1></td>
</tr>
<tr>
<td>카테고리번호</td>
<td>
<input value ="${DTO.category_no}" name="category_no" type="text" style="ime-mode:inactive;  width:300px;"/>
</td>
</tr>
<tr>
<td>카데고리이름</td>
<td>
<input value ="${DTO.category_name}" name="category_name" type="text" style="ime-mode:inactive; width:300px;"/>
</td>

<tr>
<td colspan=2 align="center" >
<input type ="submit"  value="수정"/>
<input type="button" value="삭제" onClick="location.href='deletePostCategory.do?category_no=${DTO.category_no}'">
<input type ="reset" value="다시작성"/>	
<input type="button" value="카테고리조회" onClick="location.href='PostCategoryListDatas.do?cnt=9&page=1'">
</td>
</tr>
</table>
</form>
</center>			
</body>
</html>