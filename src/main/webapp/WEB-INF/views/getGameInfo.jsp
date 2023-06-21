<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>${DTO.game_name} 정보</title>
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


<!-- 게임 정보 테이블 -->
<center>
<h1>${DTO.game_name} 정보</h1>
<form action="update" method="post">
<table border=0  width="600">
<tr>
<td>게임이름</td>
<td>${DTO.game_name}</td>
</tr>
<tr>
<td>게임장르 </td>
<td>${DTO.game_genre}</td>
</tr>
<tr>
<td>출시일</td>
<td> ${DTO.release_date}</td>
</tr>
<tr>
<td>게임URL</td>
<td> <a href="${DTO.game_url}" >${DTO.game_name} 홈페이지</a></td>
</tr>
<tr>
<td colspan=2>게임소개</td>
</tr>
<tr><td colspan=2>${DTO.infomation}</td>
</tr>
<tr>
<td align="center">
<c:if test="${role.role == 'admin'}">
<a href="updategetGameInfo.do?game_No=${DTO.game_no}" >수정하기</a>
</c:if>
</td>
<td align="center">

<!-- <a href="insertFavorite.do?user_Id=${role.user_id}&game_no=${DTO.game_no}" >즐겨찾기</a> -->

</td>
</tr>
</table>
</form>
</center>			
</body>
</html>