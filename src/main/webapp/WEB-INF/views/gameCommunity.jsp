<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="/resources/js/checkRole.js"></script>
<script type="text/javascript">
function checkRolePost() {
	  let role = "${role.role}";
	  if (role == 'admin'||role == 'user') {
		  window.location.href = `insertPostForm?game_name=${game_name}&game_no=${game_no}`;
	  } else {
		  alert('로그인 후 이용해주세요');
		  
	  }
	}
	function checkRoleNotice() {
		  let role = "${role.role}";
		  if (role == 'admin') {
			  window.location.href = `insertNoticeForm?game_name=${game_name}&game_no=${game_no}`;
			  
		  } else {
			  alert('관리자만 작성 가능합니다.');
		  }
	}

</script>
  <meta charset="UTF-8">
  <title>${game_name} 커뮤니티</title>
  <link rel="stylesheet" type="text/css" href="resources/css/gameCommunity.css">


  
  
</head>

<body>
	<div>
		<div>
			<h1><a class="navbar-brand" href="main">메인 페이지</a></h1>
		</div>
	
  <h1><a></a></h1>${game_name} 커뮤니티에 오신걸 환영합니다!
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
<div>
	<a href="noticeList?game_no=${game_no}&game_name=${game_name}&cnt=10&page=1">공지사항</a>
	<c:forEach var="category" items="${CATEGORY_LIST}">
	<td colspan="6" align="center"><a href="getPostList?game_no=${game_no}&game_name=${game_name}&category_no=${category.category_no}&category_name=${category.category_name}">${category.category_name}</a></td>
	</c:forEach>
</div>
<div align="right">
	
	<a class="" href="#" onclick="checkRolePost()">게시글쓰기</a>
	
	
	<a href="#" onclick="checkRoleNotice()" > 공지사항 글쓰기</a>
	
</div>
<div align="center">
  <table border="1" class="notice">
    <thead>
      <tr id="title">
        <td colspan="5" align="center">공지사항</td>
      </tr>
      <tr class="subtitle">
        <td>제목</td>
        <td>작성일</td>
        <td>조회수</td>
      </tr>
    </thead>
    <c:if test="${empty NOTICE_LIST}">
      <tr>
        <td colspan="3">게시물이 없습니다.</td>
      </tr>
    </c:if>
    <c:forEach var="notice" items="${NOTICE_LIST}">
      <tbody>
        <tr>
          <td><a href="getNotice?gmae_name=${game_name}&game_no=${game_no}&notice_no=${notice.notice_no}">${notice.title}</a></td>
          <td>${notice.reg_date}</td>
          <td>${notice.readCount}</td>
        </tr>
      </tbody>
    </c:forEach>
  </table>

  <table  class="post">
    <c:forEach var="category" items="${CATEGORY_LIST}">
      <thead>
        <tr id="title">
          <td colspan="6" align="center"><a href="getPostList?game_no=${game_no}&game_name=${game_name}&category_no=${category.category_no}&category_name=${category.category_name}">${category.category_name}</a></td>
        </tr>
      
        <tr class="subtitle">
          <td>게시물 번호</td>
          <td>제목</td>
          <td>작성자</td>
          <td>작성일</td>
          <td>좋아요</td>
          <td>조회수</td>
        </tr>
      </thead>
      <c:forEach var="post" items="${category.post}">
        <tbody>
        	<c:if test="${empty post}">
     			 <tr>
       			 <td colspan="6">게시물이 없습니다.</td>
      			</tr>
   			 </c:if>
        
	          <tr>
	            <td>${post.post_no}</td>
	            <td><a href="getPost?game_no=${post.game_no}&post_no=${post.post_no}&game_name=${game_name}">${post.title}</a> </td>
	            <td>${post.user_id}</td>
	            <td>${post.reg_date}</td>
	            <td>${post.like_count}</td>
	            <td>${post.readCount}</td>
	          </tr>
	          
        </tbody>
      </c:forEach>
    </c:forEach>
  </table>
    <c:if test="${role.role == 'admin'}">
  <a href="PostCategoryListDatas.do?cnt=9&page=1">카테고리 리스트</a>
</c:if>
</div>

</body>
</html>
