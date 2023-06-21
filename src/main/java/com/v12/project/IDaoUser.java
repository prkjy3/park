package com.v12.project;

public interface IDaoUser {
	
	UserDTO selectUser(String user_id);
	
	void deleteUser(String user_id);
	
	void updateUser(UserDTO user);
	
	void insertUser(UserDTO user);
	

	
}
