package com.v12.project;

import java.util.ArrayList;

public interface IDaoPostQEBC {
	
	
	// 게시판 리스트 볼 때 쓰는것
	ArrayList<PostDTO> postList(int game_no, int category_no, int start, int cnt);
	
	
	// 검색할 때 쓰는것
	ArrayList<PostDTO> searchList(String user_id, int game_no, int category_no, String title);
	
	
	// 공지사항 메인에 띄울 때 쓸 것
	ArrayList<NoticeDTO> noticeList(int game_no, int start , int PAGE_SIZE);
	
	
	// 카테고리 리스트 확인
	ArrayList<CategoryDTO> categoryList();
	
	// 댓글 리스트 들고오기
	ArrayList<CommentDTO> postComment(int game_no, int post_no , int page,int cnt);
	
	
}
