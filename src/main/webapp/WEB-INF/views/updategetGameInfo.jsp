<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>${DTO.game_name} 정보</title>
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
$(function() {
  /** id속성이 "checkpost"인 요소에 대한 "click"이벤트 정의    */
  $("#checkpost").click(function() {
    //사용자 입력값 얻어오기
    var input_value = ${DTO.game_no};
    var url = "gameDeleteChk.do?t=" + Math.random(); //URL 은 같으면 같은데이터를 보내기 때문에 해싱을 피하기위해서

    //get 방식 ajax 연동
    $.getJSON(url, {
      "game_no": input_value
      // 이 문장에 의해 코드 값이 넘어간다 url로 주소를 준 값으로
    }, function(json) {
      //결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며
      //JSON이므로 별도의 추출과정 없이 점(.)으로 데이터 계층을 연결하여 사용할 수 있다.
      var result_text = json.result;

      // "true" 혹은 "false" 라는 문자열이므로, eval 함수를 사용하여 boolean 값으로 변경
      var result = eval(result_text);

      //실무에서는 ajax용 컨트롤러와 스탠다드용 컨트롤러는 따로 나둔다. 이렇게 실무에서 사용한다.

      //결과 출력
      if (result) {
        alert("게시물 삭제 후 삭제해주세요");
      } else {
        if (confirm("삭제하시겠습니까?")) {
          // 확인 버튼 클릭 시 삭제 페이지로 이동
          location.href = 'deleteGame.do?game_no=${DTO.game_no}&game_image_no=${GAMEIMAGE.game_img_no}';
        }
      }
    });
  });
});
</script>
	
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

	

<center>
<form action="updateGame.do" method="post">
<table border=0  width="600">
<tr>
<td colspan=2 align="center" ><h1>${DTO.game_name} 정보</h1></td>
</tr>
<tr>
<td colspan=2>
<input value ="${DTO.game_no}" name="game_no" type="hidden" style="ime-mode:inactive;  width:300px;" readonly/>
</td>
</tr>
<tr>
<td>게임이름</td>
<td>
<input value ="${DTO.game_name}" name="game_name" type="text" style="ime-mode:inactive; width:300px;"/>
</td>
</tr>
<tr>
<td>게임장르 </td>
<td><input value ="${DTO.game_genre}" name="game_genre" type="text" style="ime-mode:inactive; width:300px;"/></td>
</tr>
<tr>
<td>출시일</td>
<td><input value ="${DTO.release_date}" name="release_date" type="text" style="ime-mode:inactive; width:300px;"/></td>
</tr>
<tr>
<td>게임URL</td>
<td><input value ="${DTO.game_url}" name="game_url" type="text" style="ime-mode:inactive; width:300px;"/></td>
</tr>
<tr>
<td colspan=2>게임소개</td>
</tr>
<tr><td colspan=2>
<textarea id = "game_introduce" name="infomation" style="ime-mode:inactive; width:600px; height:60px;">${DTO.infomation}</textarea>

</td>
</tr>
<tr>
<td colspan=2>
			 <label for="image">로고이미지</label>
            <input type="file" name="image" id="image">
</td>
</tr>
<tr>
<td colspan=2 align="center" >
<input type ="submit"  value="수정"/>
<input type="button" value="확인삭제" id="checkpost" >
<input type="button" value="삭제" onClick="location.href='deleteGame.do?game_No=${DTO.game_no}&game_Image_No=${GAMEIMAGE.game_img_no}'">
<input type ="reset" value="다시작성"/>	
<input type="button" value="게임조회" onClick="location.href='GameListDatas.do?cnt=9&page=1'">
</td>
</tr>
</table>
</form>
</center>			
</body>
</html>