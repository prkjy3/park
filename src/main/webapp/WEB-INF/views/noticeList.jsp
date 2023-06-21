<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<html> 
<head>
<meta charset="UTF-8">
	<title>공지사항 리스트</title>
</head>
<body>
<div align="left"><a href="main">메인</a><br>
<a href="gameCommunity?game_name=${GAME_NAME}&game_no=${game_no}">${GAME_NAME} 바로가기</a><br>
</div>




<h3>${GAME_NAME}</h3>
	<h2>공지사항</h2>
<br><hr><br>


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




<DIV align="CENTER">
			<table border=1 >
				<thead >
					<tr>
						<td>제목</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
				</thead>
				<c:forEach var="notice" items="${NOTICE_LIST}">
					<tbody>
						<tr>
							<td><a href="getNotice?game_no=${GAME_NO}&notice_no=${notice.notice_no}&game_name=${game_name}">${notice.title}</a></td>
							<td>${notice.reg_date}</td>
							<td>${notice.readCount}</td>
							</tr>
					</tbody>
				</c:forEach>
			</table>
	
</DIV>
	<div align="left">
	<a href="insertNoticeForm?game_no=${GAME_NO}&game_name=${GAME_NAME}">공지사항글쓰기</a>	
	</div>

	<c:if test= "${StartPage > 10 }">
		<a href="getCstDatas.do?cnt=5&page=${StartPage - 10 }">[이전]</a>
	</c:if>
	
	<c:forEach var ="i" begin="1" end="${EndPage }">
	
	<a href="getCstDatas.do?cnt=5&page=${i}">${i}</a>
	
	</c:forEach>
	
	<c:if test= "${EndPage < PageCount}">
		<a href= "getCstDatas.do?cnt=5&page=${StartPage + 10 }">[다음]</a>
	</c:if>
	
	
	<a href="main">메인</a>
</body>
</html>