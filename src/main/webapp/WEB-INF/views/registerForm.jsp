<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 화면</title>
<!-- jQuery Framework 참조하기

<script src="resources/js/jquery-3.4.1.min.js"></script>
 -->

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 사용자 스크립트 블록 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .info-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
        background-color: #F7F7F7;
        border: 1px solid #DDD;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px #CCC;
    }

    .button-container {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .button-container a {
        display: block;
        margin: 0 10px;
        padding: 10px 20px;
        background-color: #007BFF;
        color: #FFF;
        text-decoration: none;
        border-radius: 5px;
    }

    .button-container a:hover {
        background-color: #0069D9;
    }

    .logo {
        position: absolute;
        top: 20px;
        left: 20px;
        font-size: 24px;
        font-weight: bold;
    }
</style>
<script>
$(function() {
	
	/** id속성이 "checkid"인 요소에 대한 "click"이벤트 정의    */
	$("#checkid").click(function() {
		//사용자 입력값 얻어오기
		var input_value = $("input[name='user_id']").val();
		
		//입력여부 검사
		if(!input_value){
			alert("아이디를 입력하세요.");
			$("input[name='user_id']").focus();
			return false;
		}
		
		var url = "IdChkCtrl.do?t=" +Math.random();//URL 은 같으면 같은데이터를 보내기 때문에 해싱을 피하기위해서
		//get 방식 ajax 연동
		$.getJSON(url,{
			"user_id" : input_value
			// 이 문장에 의해 코드 값이 넘어간다 url로 주소를 준 값으로
		} , function(json) {
			//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며
			//JSON이므로 별도의 추출과정 없이 점(.)으로 데이터 계층을 연결하여 사용할 수 있다.
			var result_text = json.result;
			
			//alert(result_text);
			
			// "true" 혹은 "false" 라는 문자열이므로, eval 함수를 사용하여 boolean 값으로 변경
			var result = eval(result_text);
			
			//실무에서는 ajax용 컨트롤러와 스탠다드용 컨트롤러는 따로 나둔다. 이렇게 실무에서사용한다.
			
			//결과 출력
			if(result){
				$(".console").html("<span style='color:blue'>사용할 수 있는 아이디 입니다.</span>");
			}else{
				$(".console").html("<span style='color:red'>사용할 수 없는 아이디 입니다.</span>");
			}
		});
	});
});

</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">메인 페이지</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
		</div>
	</nav>


<center>
  <h1>회원가입</h1>
     <form action="register.do" method="post" >
        <table>
            <tr>
                <td>아이디:</td>
                <td><input type="text" id="user_id" name="user_id" maxlength="20" required>
                <button type="button" id ="checkid" onclick="checkId()">중복확인</button><br>
                <div class="console"></div>
                </td>
            </tr>
            <tr>
                <td>비밀번호:</td>
                <td><input type="password" name="password" maxlength="20" required></td>
            </tr>
            <tr>
                <td>이름:</td>
                <td><input type="text" name="name" maxlength="20" required></td>
            </tr>
            <tr>
                <td>이메일:</td>
                <td><input type="email" name="email" maxlength="50" required></td>
            </tr>
            <tr>
                <td>유저소개:</td>
                <td><textarea name="user_introduce" maxlength="100" required></textarea></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="가입"></td>
            </tr>
        </table>
    </form>
    </center>
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JkYWmIbVIpt6BEl4GQ4C6K06H6I7d6X9+nSgMWtp3NXlL/q3KcKR8Snw6VyVoR/1" crossorigin="anonymous"></script>
</body>
</html>