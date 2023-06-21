package com.v12.project;

public interface IDaoCommentMEBC {
	
	void insertComment(CommentDTO comment);
	
	void updateComment(CommentDTO comment);
	
	void deleteComment(int game_no, int post_no, int comment_no);
	
	CommentDTO selectComment(int game_no , int post_no , int comment_no );
	
	int selectMaxNo(int game_no, int post_no);
	
	int count(int game_no, int post_no);
	
	void updateCommentStep(int ref_no,int re_step ,int game_no , int post_no );
	

	
}
