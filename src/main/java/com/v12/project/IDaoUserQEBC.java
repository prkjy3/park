package com.v12.project;

import java.util.ArrayList;

public interface IDaoUserQEBC {
	
	ArrayList<PostDTO> selectUserPost(String user_id);
	
	ArrayList<CommentDTO> selectUserComment(String user_id);
	
	
}
