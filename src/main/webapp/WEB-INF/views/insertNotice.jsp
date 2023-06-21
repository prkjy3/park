<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<link rel="stylesheet" href="./css/style.css">

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>



<style>

body {

  padding-top: 70px;

  padding-bottom: 30px;

}
</style>

</head>

<body>
    <div class="container">
        <h1>공지사항 작성</h1>
        <form method="post" action="insertNotice.do" enctype="multipart/form-data">

            <label for="title">제목</label>
            <input type="text" name="title" id="title" required>
			<input type="hidden" name="game_no" id="game_no" value ="${game_no}">
            <label for="content">내용</label>
            <textarea name="content" id="content" rows="10" required></textarea>

            <label for="images">이미지</label>
            <input type="file" name="images" id="images" multiple>

            <input type="submit" value="작성">
        </form>
    </div>
</body>

</html>