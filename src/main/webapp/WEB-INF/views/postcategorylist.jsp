<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<title>카테고리 조회</title>
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
<center>
<form class=postcategorylist action="PostCategoryListDatas.do?cnt=9&page=1" method="post">
  <table border=0>
  <tr>
  <td align="center" colspan=3>
  <h1>카테고리 목록</h1>
  </td>
  </tr>

    <c:forEach var="postcategorylist" items="${POST_CATEGORY_LIST}" varStatus="status">
      <c:if test="${status.index % 3 == 0}">
        <tr>
      </c:if>
      <td>
        <table border=0>
      
          <tr> 
            <td align="center">카테고리 번호</td>
          </tr>
                    <tr> 
            <td align="center">${postcategorylist.category_no}</td>
          </tr>
                    <tr> 
            <td align="center">카테고리 이름</td>
          </tr>
           <tr> 
            <td align="center">${postcategorylist.category_name}</td>
          </tr>
          <tr>
            <td align="center">
              <a href="selectPostCategory.do?category_no=${postcategorylist.category_no}">상세보기</a>
            </td>
          </tr>
        </table>
      </td>
      <c:if test="${(status.index + 1) % 3 == 0 || status.last}">
      </c:if>
    </c:forEach>
  </table>
    <c:forEach var="pageNum" begin="${StartPage}" end="${EndPage}">
    <a href="PostCategoryListDatas.do?cnt=9&page=${pageNum}">${pageNum}</a>
  </c:forEach>
  </form>
 <form action="insertPostCategory.do" method="post">
<table border=0  width="600">
<tr>
<td colspan=2 align="center" ><h1>카테고리 입력</h1></td>
</tr>
<tr>
<td>카테고리 번호</td>
<td colspan=2>
<input name="category_no" type="text" style="ime-mode:inactive ; width:300px; "/>
</td>
</tr>


<tr>
<td>카테고리명</td>
<td><input name="category_name" type="text" style="ime-mode:inactive; width:300px;"/></td>
</tr>

<tr>
<td colspan=2 align="center">
<input type="submit" value="카테고리등록">
<input type="reset" value="다시작성">
<input type="button" value="카테고리조회" onClick="location.href='PostCategoryListDatas.do?cnt=9&page=1'">
</td>
</tr>
</table>
</form>
</center>

</body>
</html>
