package com.v12.project;

public interface IDaoPostMEBC {
	
	
	// 게시글 입력  , 게시글 댓글 입력 근데 두개 같은 dao 로 둬도 될듯?--
	void insertPost(PostDTO post);
	
	//게시글 수정 
	void updatePost(PostDTO post);
	
	// 게시판 번호 수중 가장 큰 값 찾아오기( 번호 자동으로 불러올려고 함) 한마디로 말하면 board_no 증가시킬려고 사용하는것
	int selectMaxNo(int game_no, int post_no);
	
	//게시글 조회시 조회 수 증가--
	void updatePostReadCount(int game_no , int post_no);
	
	// 해당 포스트 삭제( 그럼 그에 관한 댓글도 삭제됨)--
	void deletePost(int game_no, int post_no);
	
	
	
	// 게시글 찾기(상세조회나 업데이트, 삭제, 할때 사용할 sql 문)
	PostDTO selectPost(int game_no, int post_no);
	
	// 게시글 개수 찾기
	int countPost(int game_no, int category_no);
	
	
	void deletePostUser(String user_id);
	
	
}
