package com.v12.project;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public interface IUserService {	
	
	String sltOne(String user_id, HashMap<String,Object> hm)throws Exception;
	
	String insertUser(UserDTO user, HashMap<String,Object> hm)throws Exception;
	
	String updateUser(UserDTO user, HashMap<String,Object> hm)throws Exception;
	
	String deleteUser(UserDTO user , HashMap<String,Object> hm)throws Exception;
	
	String getUserInfo(String user_id , HashMap<String,Object> hm)throws Exception;
	
	String loginDo(UserDTO user, HashMap<String,Object> hm )throws Exception;
	
	String logout(HttpSession session)throws Exception;
	
	
}
