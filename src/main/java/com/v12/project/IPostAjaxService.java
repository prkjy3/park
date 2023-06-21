package com.v12.project;

import java.util.HashMap;

public interface IPostAjaxService {
	
	public String insertPostLike(PostLikeDTO likeDto, HashMap<String, Object> hm)throws Exception;
//{
//		
//		PostLikeDTO chk = postLikeMEBC.selectPostLike(likeDto.getGame_no(), likeDto.getBoard_no(), likeDto.getUser_id());
//		if(chk !=  null) {
//			
//			String msg= "이";
//			
//		}
//		
//		
//		postLikeMEBC.insertPostLike(likeDto);
//		
//		
//		return null;
//	}

	
	public String deletePostLike(PostLikeDTO likeDto, HashMap<String, Object> hm) throws Exception;
//{
//		
//		postLikeMEBC.deletePostLike(likeDto);
//		
//		return null;
//	}
}
