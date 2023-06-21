<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<body>
	<!-- 메뉴바 -->
	<!-- 로그인 -->
	
	<div class="user-container" align="center" >
		<table>
		<tr>
		<td>반갑습니다 ${role.user_id}님</td>
		</tr>
		</table>
	</div>
	<a href="getUserInfo">마이페이지</a><br>
	<h1>게임 정보</h1>
    <center>
        <h2>게임목록</h2><br>
        <div align="right">
            <a href="GameListDatas.do?cnt=9&page=1">등록순</a>
            <a href="GameListNoDatas.do?cnt=9&page=1">번호순</a>
            <a href="GameListNameDatas.do?cnt=9&page=1">이름순</a>
            <a href="GameListDatas.do?cnt=9&page=1">인기순</a>
        </div>
        <br>


		<!-- 게임정보 테이블 -->
		<form class="gameList" action="GameListDatas.do?cnt=9&page=1" method="post">
		  <table border=1>
		    <c:forEach var="game" items="${GAME_LIST}" varStatus="status">
		      <c:if test="${status.index % 3 == 0}">
		        <tr>
		      </c:if>
		      <td>
		        <table border=1>
		          <tr>
		            <td align="center">
		              <c:forEach var="gameimg" items="${GAME_IMAGE_LIST}" varStatus="status2">
		                <c:if test="${gameimg.game_No == game.game_No}">
		                  <img src="${gameimg.game_Img_Path}" width="100px" height="100px">
		                </c:if>
		              </c:forEach>
		            </td>
		          </tr>
		          <tr> 
		            <td align="center">${game.game_Name}</td>
		          </tr>
		          <tr>
		            <td align="center">
		              <a href="getGameInfo.do?game_No=${game.game_No}">상세보기</a>
		              <a href="Game.do?game_No=${game.game_No}">커뮤니티</a>
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
		  <br>
		  <c:if test="${role.role == 'lv0'}">
		    <a href="insertGameFrom">게임정보입력</a><br>
		        <a href="insertPostCategory">카테고리정보입력</a>
		    
		    
		  </c:if>
		</form>

</body>
</html>