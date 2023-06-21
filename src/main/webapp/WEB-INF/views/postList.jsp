<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${game_name} ${category_name}</title>
</head>
<body>


<DIV align="CENTER">

<div align="left"><a href="main">메인</a><br>
<a href="gameCommunity?game_name=${game_name}&game_no=${game_no}">${game_name} 바로가기</a><br>
</div>	<h2>${game_name} ${category_name}</h2>
<h3>${game_name}</h3>
<br><hr><br>
			<table border=1 >
				<thead>
					<tr>		
          				<td>제목</td>
         				<td>작성자</td>
         				<td>작성일</td>
         				<td>좋아요</td>
         				<td>조회수</td>
					</tr>
				</thead>
				<c:forEach var="post" items="${PostList}">
					<tbody>
						<tr>
							<td><a href="getPost?game_no=${post.game_no}&post_no=${post.post_no}&game_name=${game_name}">${post.title}</a> </td>
							<td>${post.user_id}</td>
							<td>${post.reg_date}</td>
							<td>${post.like_count}</td>
							<td>${post.readCount}</td>
							</tr>
					</tbody>
				</c:forEach>
			</table>
	
</DIV>
	<div align="left">
	<c:if test="${role != null }">
	<a href="insertPostForm?game_name=${game_name}&game_no=${game_no}">게시글쓰기</a>	
	</c:if>
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
	
	
	
	
</body>
</html>