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


<title>게시글 작성</title>


<style>

body {

  padding-top: 70px;

  padding-bottom: 30px;

}
</style>

</head>

<body>
    <div class="container">
    	
        <h1>게시글 작성</h1>
        
        <form method="post" action="insertPost.do" enctype="multipart/form-data">
        
		  <label for="title">제목</label>
		  <input type="text" name="title" id="title" required>
		  <input type="hidden" name="game_no" id="game_no" value="${game_no}" required>

		  <label for="category_no">카테고리</label>
		  <select name="category_no" id="category_no" required>
		    <c:forEach var="category" items="${CATEGORY_LIST}">
		      <option value="${category.category_no}">
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