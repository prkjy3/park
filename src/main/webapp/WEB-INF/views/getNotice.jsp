<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항보기</title>
</head>
<body>
		<div class="container-fluid">
			<h1><a class="navbar-brand" href="main">메인 페이지</a></h1>
		
			
		</div>
	<hr>
		<c:if test="${role != null}">
    <!-- 사용자 아이디와 마이페이지 버튼을 보여줍니다. -->
   		    반갑습니다 ${role.user_id }님<br>
   <a href="getUserInfo?user_id=${role.user_id}">회원 정보</a>
   <a href="logout.do">로그아웃</a>
</c:if>
<c:if test="${role == null}">
    <!-- 로그인 폼과 로그인 버튼을 보여줍니다. -->
    <form action="login.do" method="post">
        <input type="text" name="user_id" placeholder="아이디" name="user_id">
        <input type="password" name="password" placeholder="비밀번호" name="password">
        <input type="submit" value="로그인">
        <a class="nav-link" href="registerForm">회원가입</a>
    </form>
</c:if>
	
<div align="center">
	<table>
	<tr>
	<td  width="300px">${NOTICE.title}</td>
	</tr>
	<tr>
	
	<td width="100" align="left" >${NOTICE.readCount}</td>
	<td width="100" align="right">${NOTICE.reg_date}</td>
	
   	<tr>
      <td><img src=""></td>
	</tr>	
	<tr align="center">
	<td>${NOTICE.content}</td>	
	
	</tr>
	<tr>
	<c:if test="${NOTICEIMAGE != null }">
	
	<td><img src="${NOTICEIMAGE.notice_img_path }"></td>
	</c:if>
	</tr>
</table>


</div>
<c:if test="${role.role == 'admin'}">
<div>	
	
	<a href="updateNoticeForm?game_no=${NOTICE.game_no}&notice_no=${NOTICE.notice_no}&game_name=${GAME_NAME}">공지사항 수정</a>
	<a href="deleteNotice.do?game_no=${NOTICE.game_no}&notice_no=${NOTICE.notice_no}">공지사항 삭제</a>
</div>

</c:if>			
</body>
</html>