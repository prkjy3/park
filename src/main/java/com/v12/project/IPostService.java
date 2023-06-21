package com.v12.project;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IPostService {
	
	public String postList(int game_no,int category_no,int cnt,int page,HashMap<String,Object> hm)throws Exception;
	
	// 게시물 상세보기
	public String getPost(int game_no , int post_no, HashMap<String,Object> hm ,String user_id,int page,int cnt)throws Exception;
		
	// 게시글 작성
	public String insertPost(PostDTO dto,MultipartFile images, HashMap<String,Object> hm)throws Exception;
	
	// 게시글 수정
	public String updatePost(PostDTO dto,MultipartFile image,HashMap<String,Object> hm)throws Exception;
	
	// 게시글 삭제
	public String deletePost(int game_no , int post_no,String user_id ,HashMap<String,Object> hm)throws Exception;
	
	// 댓글 수정
	public String updatePostComment(CommentDTO dto,HashMap<String,Object> hm)throws Exception;
	
	// 댓글 삭제
	public String deletePostComment(int game_no, int post_no ,int comment_no, HashMap<String,Object> hm)throws Exception;
	
	// 댓글 작성
	public String insertPostComment(CommentDTO dto , HashMap<String,Object> hm)throws Exception;
	
	//커뮤니티 진입
	public String accessCommunity(int game_no ,int cnt ,int page ,HashMap<String,Object>hm)throws Exception;
	
	//게시글 작성 화면 가기
	public String insertPostForm(int game_no,HashMap<String,Object> hm)throws Exception;
	
	//게시글 업데이트 화면 가기
	public String updatePostForm(String user_id, int game_no,int post_no,int category_no,HashMap<String,Object> hm)throws Exception;
	
}
