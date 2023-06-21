<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->


<script src="js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="./css/style.css">

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function() {
	  $('form').on('submit', function() {
	    var category_name = $('#category_no option:selected').data('categoryname');
	    $(this).append('<input type="hidden" name="category_name" value="' + category_name + '">');
	  });
	});


</script>

<style>

body {

  padding-top: 70px;

  padding-bottom: 30px;

}
</style>

</head>

<body>
    <div class="container">

    	
        <h1>게시글 작성 작성</h1>
		 <form method="post" action="insertPost?game_name=${game_name}" enctype="multipart/form-data">
		  <label for="title">제목</label>
		  <input type="text" name="title" id="title" required>
		  <input type="hidden" name="game_no" id="game_no" value="${GAME_NO}">
		  <label for="category_no">카테고리</label>
		  <select name="category_no" id="category_no">
		    <c:forEach var="category" items="${CATEGORY_LIST}">
		    
		      <option value="${category.category_no}" data-categoryname="${category.category_name}">
		        ${category.category_name}
		      </option>
		    </c:forEach>
		  </select>
		  <label for="post_content">내용</label>
		  <textarea name="post_content" id="post_content" rows="10" required></textarea>
		  <label for="image">이미지</label>
		  <input type="file" name="image" id="image" multiple>
		  <input type="submit" value="작성">
		</form>

    </div>
</body>

</html>