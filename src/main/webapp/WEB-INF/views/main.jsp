<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> 
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>메인 페이지</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
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
	<script>
function searchGames() {
  var game_name = document.getElementById("searchGamename").value;
  location.href = "searchGameListDatas.do?cnt=9&page=1&game_name=" + game_name;
}
</script>
</head>
<body>

		<h1><a href="main">게임커뮤니티</a></h1>

	<p>
<br>
<br>
<br>
</p>
	<c:if test="${role != null}">
    <!-- 사용자 아이디와 마이페이지 버튼을 보여줍니다. -->
   		    반갑습니다 ${roel.user_id }님<br>
   <a href="/mypage">${role.user_id}</a><br>
   <a href="getUserInfo?user_id=${role.user_id}">회원 정보</a>
   <a href="logout.do">로그아웃</a>
</c:if>
<c:if test="${role == null}">
    <!-- 로그인 폼과 로그인 버튼을 보여줍니다. -->
    <form action="login.do" method="post">
        <input type="text" name="user_id" placeholder="아이디" name="user_id"><br>
        <input type="password" name="password" placeholder="비밀번호" name="password">
        <input type="submit" value="로그인">
        <c:if test="${ERR  != null}">
        <div>
        <h6>${ERR}</h6>
        </div>
        </c:if>
        <a class="nav-link" href="registerForm">회원가입</a>
    </form>
</c:if>
	<div align="center">


<!-- 게임목록 테이블 -->
<form class="gameList" action="GameListDatas.do?cnt=9&page=1" method="post">
  <table border=0>
  <tr>
  <td>
  <h1>게임 목록</h1>
  </td>
  </tr>
  <tr>
  <td colspan=3>
<div align="right">
  <input id="searchGamename" name="game_name" placeholder="게임이름검색" type="text" style="ime-mode:inactive;"/>
  <input type="button" value="검색" onclick="searchGames()"/>
</div>
  </td>
  </tr>
  <tr>
  <td colspan=3>
          <div align="right">
            <a href="GameListDatas.do?cnt=9&page=1">등록순</a>
            <a href="GameListNameDatas.do?cnt=9&page=1">이름순</a>
        </div>
  </td>
  </tr>
    <c:forEach var="game" items="${GAME_LIST}" varStatus="status">
      <c:if test="${status.index % 3 == 0}">
        <tr>
      </c:if>
      <td>
        <table border=0>
          <tr>
            <td align="center" width="300px">
              <c:forEach var="gameimg" items="${GAME_IMAGE_LIST}" varStatus="status2">
                <c:if test="${gameimg.game_no == game.game_no}">
                  <img src="${gameimg.game_img_path}" width="100px" height="100px">
                </c:if>
                
              </c:forEach>
            </td>
          </tr>
          <tr> 
            <td align="center">${game.game_name}</td>
          </tr>
          <tr>
            <td align="center">
              <a href="getGameInfo.do?game_No=${game.game_no}">상세보기</a>
              <a href="gameCommunity?game_name=${game.game_name}&game_no=${game.game_no}">커뮤니티</a>
            </td>
          </tr>
        </table>
      </td>
      <c:if test="${(status.index + 1) % 3 == 0 || status.last}">
      </c:if>
    </c:forEach>
  </table>
    <c:forEach var="pageNum" begin="${StartPage}" end="${EndPage}">
    <a href="GameListDatas.do?cnt=9&page=${pageNum}">${pageNum}</a>
  </c:forEach>
  </form>
		<c:if test="${role.role == 'admin'}">
		<a href="insertGameFrom">게임정보입력</a>
		</c:if>
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JkYWmIbVIpt6BEl4GQ4C6K06H6I7d6X9+nSgMWtp3NXlL/q3KcKR8Snw6VyVoR/1" crossorigin="anonymous"></script>
</body>
</html>