package com.v12.project;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ajaxSvc")
public class AjaxServiceImpl implements IAjaxService{
	
	@Autowired
	private IDaoUser userMEBC;
	
	@Autowired
	private IDaoPostMEBC postMEBC;

	@Autowired
	private IDaoPostLikeMEBC likeMEBC;
	


	
	@Override
	public String idChk(String user_id) {
		
		UserDTO idChk = userMEBC.selectUser(user_id);
		
		boolean result = false;
		if( idChk == null )
		{
			result = true;
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result",result);
		
		String jsonOut = jsonObj.toString();
		System.out.println("===" + jsonOut);
		
		return jsonOut;
		//return(svc.mbrOne(code));
	}
	
	@Override
	@Transactional
	public String postLike(PostLikeDTO likeDto, HashMap<String, Object> hm)throws Exception
	{
		
		PostLikeDTO likeChk = likeMEBC.selectPostLike(likeDto.getUser_id(),likeDto.getGame_no(),likeDto.getPost_no());
		
		boolean result = false;
		
		if(likeChk == null)
		{
			
			likeMEBC.insertPostLike(likeDto);
			
			
			result = true;
		}else {
			likeMEBC.deletePostLike(likeDto);
			
		}
		
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result",result);
		
		String jsonOut = jsonObj.toString();
		System.out.println("===" + jsonOut);
		
		
		return jsonOut;
	}
//	@Override
//	public String deletePostLike(PostLikeDTO likeDto, HashMap<String, Object> hm) throws Exception{
//	
//		return "";
//	}
	
	
}
