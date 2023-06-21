package com.v12.project;

import java.util.HashMap;

public interface IAjaxService {
	
//	String gameDeleteChk(int game_no);
	
	//아이디 중복
	String idChk(String user_id);
	// 좋아요 등록 삭제 
	public String postLike(PostLikeDTO likeDto, HashMap<String, Object> hm)throws Exception;
	// 좋아요 삭제
//	public String deletePostLike(PostLikeDTO likeDto, HashMap<String, Object> hm) throws Exception;

}
