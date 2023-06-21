<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 게시글</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
$(function() {
	  $(".reply-btn").click(function() {
	    var user_id = "${role.user_id}";
	    var comment_no = $(this).closest(".comment").find(".comment-no").text();
	    
	    if(user_id == ""){
	      alert("로그인 후 이용해 주세요");
	      return false;
	    }
	    
    var re_level = $(this).closest(".comment").hasClass("child-comment") ? parseInt($(this).closest(".comment").find(".re-level").text()) + 1 : 0;
    // 댓글 레벨 계산
	  
    
    var formHtml = '<form method="post" action="insertComment.do?game_name=${game_name}">' +
      '<input type="hidden" name="ref_no" value="' + comment_no + '">' +
	  '<input type="text" name="re_level" value="' + re_level + '">' +
	  '<textarea name="content" id="content" maxlength="1000" rows="3"></textarea>' +
	  '<input name="post_no" type="hidden" value="${POST.post_no}">' + 
	  '<input name="game_no" type="hidden" value="${POST.game_no}">' +
	  '<input type="hidden" name="user_id" value="' + user_id + '">' +
	  '<input type="submit" value="답글달기">' +
	  '</form>';
	    
	    $(this).next(".console").html(formHtml);
	  });
	});

</script>
<script>
function checkUserId() {
  var userId = "${role.user_id}";
  if (userId == "") {
    alert("로그인 후 이용해 주세요.");
    return false;
  }
  return true;
}
</script>

<style>
		.container {
  margin-bottom: 50px; /* 여백 크기 조정 */
}		
			body {
		  padding-bottom: 200px; /* 하단 여백 크기 조정 */
		}	

		body {
			font-family: Arial, Helvetica, sans-serif;
			background-color: #f2f2f2;
		}
		
		h1 {
			margin-top: 50px;
			margin-bottom: 20px;
			text-align: center;
			font-size: 36px;
		}
		
		hr {
			border: none;
			height: 2px;
			background-color: #333;
			margin-top: 20px;
			margin-bottom: 20px;
		}
		
		table {
			margin: 20px auto;
			border-collapse: collapse;
			box-shadow: 0 0 20px rgba(0,0,0,0.1);
			background-color: #fff;
		}
		
		th, td {
			padding: 10px;
			border: 1px solid #ccc;
			text-align: left;
		}
		
		td:first-child {
			font-weight: bold;
			width: 100px;
		}
		
		.post-content {
			margin: 20px auto;
			width: 80%;
			padding: 20px;
			border: 1px solid #ccc;
			background-color: #fff;
			line-height: 1.6;
		}
		
		.post-content img {
			max-width: 100%;
		}
	</style>
</head>
<body>



	<h1>${game_name} - ${POST.title}</h1>
	<hr>
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
        <input type="text" name="user_id" placeholder="아이디" name="user_id">
        <input type="password" name="password" placeholder="비밀번호" name="password">
        <input type="submit" value="로그인">
        <a class="nav-link" href="registerForm">회원가입</a>
    </form>
</c:if>	
		<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
			<table style="width: 45%; text-align: right;">
			  <tr>
			    <td align="right">작성자: ${POST.user_id} | 작성일자: ${POST.reg_date} | 조회수: ${POST.readCount}</td>
			  
			  </tr>
			  <tr>
			  	<c:if test="${POST.user_id==role.user_id}">
			  		<td><button onclick="location.href='upatePostForm?game_no=${POST.game_no}&post_no=${POST.post_no}&game_name=${game_name}&category_no=${POST.category_no}'">수정하기</button>
			  		<button onclick="location.href='deletePost?game_no=${POST.game_no}&post_no=${POST.post_no}&game_name=${game_name}'">삭제하기</button></td>
			  	</c:if>
			  </tr>
			</table>
		</div>
	<div class="post-content" style="height: 1000px; width: 1000px; margin: 0 auto; text-align: left;">
	<c:if test="${IMAGE != null }">
	
	<img src="${IMAGE.post_img_path}" width="400px" height="400px"><br>
	
	</c:if>
	${POST.post_content}</div>
	<div align="center" style="margin-top: 20px;">
	<h3>댓글</h3>
	<hr>
	<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
  <table style="width: 400%; text-align: right;">
    <tr>
      <td align="center">작성자 | 작성일자</td>
    </tr>
  </table>

</div>
</div>
	
	

<div style="height: 200px; width: 600px; margin: 100 auto; text-align: center;">
  <c:if test="${COMMENT == null}">
    <div style="padding: 20px;">댓글 내용이 없습니다.</div>
  </c:if>
  
  <c:forEach var="comment" items="${COMMENT}">
    <div style="margin: 10px 0;">
      <div class="comment">
        <span class="user_id">${comment.user_id}</span>|
        <span class="reg_date">${comment.reg_date}</span><br>
        <span class="content">${comment.content}</span>
        <span class="content">${comment.re_level}</span>
        <button class="edit-btn" onclick="editComment(${comment.comment_no})">수정</button>
        <button class="reply-btn" onclick="replyComment(${comment.comment_no})">답글 달기</button>
        
        <div class="console"></div>
      </div>
    </div>
  </c:forEach>
	<div>
		<form method="post" action="insertComment.do?game_name=${game_name}" onsubmit="return checkUserId()">
		  <textarea name="content" id="content" rows="10" required></textarea>
		  <input name="re_step" type="hidden" value="0">
		  <input name="re_level" type="hidden" value="0">
		  <input name="post_no" type="hidden" value="${POST.post_no}">
		  <input name="game_no" type="hidden" value="${POST.game_no}">
		  <input type="submit" value="댓글작성">
		</form>
	</div>
  <div style="margin-top: 20px; text-align: center;">
    <c:forEach var="pageNum" begin="${StartPage}" end="${EndPage}">
      <a href="getPost?game_no=${POST.game_no}&post_no=${POST.post_no}&page=${pageNum}&cnt=20&game_name=${game_name}">${pageNum}</a>
    </c:forEach>
  </div>
</div>

	
	
	
	<div class="container" >
  <!-- 페이지 내용 -->
</div>
	
	
</body>
</html>