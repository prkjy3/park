package com.v12.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userSvc")
public class UserServiceImpl implements IUserService {
	
	
	@Autowired
	IDaoUserQEBC userQEBC;
	
	//johndoe
	@Autowired
	IDaoUser userMEBC;
	
	@Autowired
	IDaoPostMEBC postMEBC;
	
	
	private static final Logger logger =LoggerFactory.getLogger(UserController.class);
	
	@Override
	public String sltOne(String id ,HashMap<String, Object> hm)throws Exception {
		
		UserDTO user = userMEBC.selectUser(id);
		
		hm.put("USER", user);
		
		return "userInfo";
	}
	
	
	
	@Override
	@Transactional//유저정보 수정
	public String updateUser(UserDTO user,  HashMap<String, Object> hm)throws Exception {
		
		logger.info("유저정보 업데이트");
		
		
		UserDTO chkUser =userMEBC.selectUser(user.getUser_id());
		
		
		
		if(chkUser == null) {
			
			logger.info("아이디가 없음!");
			
			hm.put("ERR", "오류가 발생했습니다.");
			
			return "errPage";
		}
		if( !(chkUser.getPassword().equals(user.getPassword())))
		{	
			logger.info("사용자가  입력한 비번"+user.getPassword());
			logger.info("db에 저장된  비번"+chkUser.getPassword());
			logger.info("비밀번호 다름!");
			
			hm.put("ERR", "비밀번호가 다릅니다.");
			
			return "redirect:/getUserInfo";
			
		}
		
		userMEBC.updateUser(user);
		
		
		return "redirect:/getUserInfo";
	}

	@Override
	@Transactional
	public String deleteUser(UserDTO user, HashMap<String, Object> hm)throws Exception {

		UserDTO chkUser =userMEBC.selectUser(user.getUser_id());
		
		
		
		
		if(chkUser == null) {
			
			hm.put("ERR", "존재하지 않는  아이디입니다.");
			
			return "errPage";
		}
		if(!(chkUser.getPassword().equals(user.getPassword()) ))
				{
			
			hm.put(("ERR"), "비밀번호가 틀립니다.");
		
			return "deleteUserForm";
		}
		
		postMEBC.deletePostUser(user.getUser_id());
		
		userMEBC.deleteUser(user.getUser_id());
		
		
		return "deletionComplete";
	}
	


	@Override
	public String loginDo(UserDTO user, HashMap<String, Object> hm)throws Exception {
		String inId = user.getUser_id();
		String inPw = user.getPassword();
		
		
		UserDTO chk =  userMEBC.selectUser(inId);
		
		if(chk == null)
		{
			String msg = "존재하지 않는  아이디입니다.";
			hm.put("MSG",msg );
			return "redirect:/";
			
		}
		if(!(chk.getPassword().equals(inPw)) ) {
			
			String msg = "비밀번호가 틀렸습니다.";
			hm.put("MSG", msg);
			return "redirect:/";
			
		}
		logger.info(user.getUser_id());
		
		
		hm.put("USER",chk);
		
		
		
		
			return "redirect:/";
	}
	
	@Override
	@Transactional
	public String insertUser(UserDTO user, HashMap<String, Object> hm)throws Exception {	
		
			logger.info("유저정보 기입");
			logger.info(user.getCreated_at());
			logger.info(user.getEmail());
			logger.info(user.getUser_id());
			logger.info(user.getPassword());
			logger.info(user.getUser_introduce());
			
			 LocalDateTime now = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 String formattedDateTime = now.format(formatter);
			user.setCreated_at(formattedDateTime);
			user.setRole("user");
			
			UserDTO chkUser =userMEBC.selectUser(user.getUser_id());
			if(chkUser != null) {
				
				hm.put("ERR", "존재하는 id 입니다.");
				
				return "errPage";
			}
			
			
			userMEBC.insertUser(user);
				
			
			return "signUpComplete";
	}


	
	@Override
	public String getUserInfo(String user_id, HashMap<String, Object> hm) throws Exception{
		
		
		UserDTO userInfo = userMEBC.selectUser(user_id);
		
		if(userInfo == null) {
			hm.put("ERR", "존재하지 않는 id 입니다.");
			
			return "errPage";
			
		}
		
		userInfo.setPassword("");
		
		ArrayList<CommentDTO> commentList 	= userQEBC.selectUserComment(user_id);
		ArrayList<PostDTO>		postList	= userQEBC.selectUserPost(user_id);
		
		hm.put("USERINFO", userInfo);
		hm.put("COMMENT",commentList);
		hm.put("POST", postList);
		
		return "getUserInfo";
	}

	

	@Override
	public String logout(HttpSession session)throws Exception {
		
		
		
		return "redirect:/";
	}
	
	
	
	
}
