package com.v12.project;
  
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

	@Autowired
	private IUserService userSvc;
	
	@Autowired
	private IGameService gameSvc;
	
	
	
	
	
	private static final Logger logger =LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping( value = "/")
	public String home(@RequestParam(value="cnt" ,required=false,defaultValue= "10" ) int cnt,
			@RequestParam(value="page" ,required=false,defaultValue="1" )int page
			,Model model) {
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		
		gameSvc.gameList(cnt, page, hm);
		
		
		model.addAllAttributes(hm);
		
		return "main";
	}
	@RequestMapping( value = "main")
	public String main() {
		
		return "redirect:/";
	}
	
	@RequestMapping( value = "login")
	public String loginMain() {
		logger.info("home called==========");
		return "main";
	}
	


	@RequestMapping("login.do")//로그인
	public String loginDo(HttpSession session ,UserDTO user,Model model)throws Exception {
		
		
		
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = userSvc.loginDo(user, hm);
		
		UserDTO dto = (UserDTO)hm.get("USER");
		
		if(dto != null) {
			session.setAttribute("role",dto);	
		}
		model.addAllAttributes(hm);
		
		
		return url;
	}
	
	@RequestMapping("registerForm")//회원가입 화면으로 이동
	public String registerUser() {
		
		
		return "registerForm";
	}
	
	
	@RequestMapping("register.do")//회원가입 버튼을 눌르기 
	public String registerDo(UserDTO user ,  Model model)throws Exception {
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		
		
		String url = userSvc.insertUser(user, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	@RequestMapping("getUserInfo")//유저정보페이지로 이동
	public String getUserInfo(HttpSession session , Model model)throws Exception {
		
		UserDTO dto =(UserDTO) session.getAttribute("role");
		
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();

		
		String url = userSvc.getUserInfo(dto.getUser_id(),hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	@RequestMapping("updateUserForm")//회원번호 수정 화면으로 이동  
	public String updateUserForm(@ModelAttribute("user_id") String user_id ,
			Model model)throws Exception{
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		
		
		userSvc.getUserInfo(user_id, hm);
		
		model.addAllAttributes(hm);
		
		return "updateUserForm";
	}
	@RequestMapping("updateUser.do")// 회원정보 수정
	public String updateUserDo(UserDTO user,  Model model)throws Exception{
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String url = userSvc.updateUser(user,hm);
		
		return url;
	}
	@RequestMapping("logout.do")
	public String logoutDo(HttpSession session)throws Exception {
		
		
		String url  =userSvc.logout(session);
		
		session.invalidate();
		
		return url;
	}
	@RequestMapping("deleteUserForm")
	public String deleteUserForm() {
		
		
		return "deleteUserForm";
	}
	@RequestMapping("deleteUser.do")
	public String deleteUserDo(UserDTO user, Model model ,HttpSession session) throws Exception{
		String url ="";
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		url = userSvc.deleteUser(user, hm);
		
		model.addAllAttributes(hm);
		
		session.invalidate();
		
		return url;
	}
}
