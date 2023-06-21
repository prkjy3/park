package com.v12.project;

public interface IDaoPostLikeMEBC {
	// 게시글 좋아요 기능
	void insertPostLike(PostLikeDTO dto);
	
	// 게시글 좋아요 싫어요 삭제 
	void deletePostLike(PostLikeDTO dto);
	
	// 게시글 좋아요  확인
	PostLikeDTO selectPostLike( String user_id,int game_no , int post_no );
	
	// 게시글 좋아요 수 가져오기
	int selectCount(int game_no, int post_no);
	
	
	}
