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