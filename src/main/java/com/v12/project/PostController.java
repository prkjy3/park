package com.v12.project;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
		
	@Autowired
	IPostService postSvc;
	
	private static final Logger logger =LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping("insertPost")
	public String insertPostDo(@ModelAttribute("PostDTO") PostDTO dto,
			 @RequestParam("image") MultipartFile image,
            Model model
            ,HttpSession session,String game_name
			) throws IOException , Exception{
		
		UserDTO user = (UserDTO) session.getAttribute("role");
		String user_id = user.getUser_id();
		
		dto.setUser_id(user_id);
		
		logger.info("뭐가문제일까");
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String url =   postSvc.insertPost(dto, image, hm);
	    	
	    	model.addAllAttributes(hm);
	    	model.addAttribute("category_no",dto.getCategory_no());
	    	model.addAttribute("category_name",dto.getCategory_name());
	    	model.addAttribute("game_name",game_name);
	    	return url;
		
    }
	
	
	
	// 게시글 등록하기 버튼을 눌럿을 시
//	@RequestMapping(method = RequestMethod.POST, value="insertPost.do")
//	public String insertPost(PostDTO dto,
//  @RequestParam("images") MultipartFile images,
//	    @RequestParam(value="cnt", required=false, defaultValue="1") int page,
//	    Model model, HttpSession session)throws Exception{
//		
//		
//        
//    	HashMap<String,Object> hm = new HashMap<String,Object>();
//    	
//    	
//    	 
//        
//        
//    	
//    	
//    	
//        return url;
//    }
	@RequestMapping("getPost")
	public String getPost(@ModelAttribute("game_no") int game_no,
			@RequestParam(value="page" ,required=false,defaultValue= "1" ) int page,
			@RequestParam(value="cnt" ,required=false,defaultValue= "20" ) int cnt,
			String category_name,
			@ModelAttribute("post_no") int post_no, 
			@ModelAttribute("game_name")String game_name,
			Model model,
			HttpSession session)throws Exception {
		
		System.out.println(game_no);
		System.out.println(post_no);
		System.out.println(page);
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		String url = "";
		String user_id;
		
		
		if( (UserDTO) session.getAttribute("role") == null) {
			 user_id="";
			
		}else {
			UserDTO userChk = (UserDTO) session.getAttribute("role");
			user_id = userChk.getUser_id();
					
			
		}
			
		
			logger.info("게시물 가져오기 서비스 시작");
		url = postSvc.getPost(game_no, post_no, hm, user_id, page,cnt);
		
		model.addAllAttributes(hm);
		model.addAttribute("game_name",game_name);
		model.addAttribute("category_name",category_name);
		
		
		return url;
	}

	@RequestMapping("updatePost")
	public String updatePost(PostDTO postDto, MultipartFile image,
			Model model)throws Exception{
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String url="";
		
		try {
			url = postSvc.updatePost(postDto,image, hm);
			
		}catch(Exception e) {
			
		}
		model.addAllAttributes(hm);
		
		
		return url;
	}
	@RequestMapping("gameCommunity")
	public String accessCommunity(
			@RequestParam(value="cnt" ,required=false,defaultValue= "1" ) int page,
			@RequestParam(value="cnt" ,required=false,defaultValue= "10" ) int cnt,
			@ModelAttribute("game_name") String game_name, 
			@ModelAttribute("game_no") int game_no ,Model model)throws Exception {
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String url = "";
		try {
		
			postSvc.accessCommunity(game_no,cnt,page,hm);
			
		}catch(Exception e) {
			
		}
		
		model.addAttribute("game_name",game_name);
		model.addAllAttributes(hm);
		return "gameCommunity";
	}
	@RequestMapping("insertPostForm")
	public String insertPostForm(int game_no,String game_name, Model model)throws Exception{
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String url = postSvc.insertPostForm(game_no, hm);
		
		model.addAllAttributes(hm);
		model.addAttribute("game_name",game_name);
		
		return url;
	}
	@RequestMapping("updatePostForm")
	public String getPost(int game_no, int post_no, Model model, 
			String game_name,
			HttpSession session,
			int category_no,
			@RequestParam(value="page" ,required=false,defaultValue= "1" ) int page )throws Exception {
		
		UserDTO dto = (UserDTO) session.getAttribute("role");
		String user_id = dto.getUser_id();
		
		String url = "";
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		try {
		
			url = postSvc.updatePostForm(user_id,game_no,post_no,category_no,hm);
		
		}catch(Exception e) {
			
			url ="errPage";
			
			model.addAttribute("ERR","에러가 발생했습니다.");
		}
		model.addAttribute("game_name",game_name);
		model.addAllAttributes(hm);
		
		return url;
	}
	@RequestMapping("insertComment.do")
	public String insertComment(String game_name,
			CommentDTO dto,	
			Model model, HttpSession session)throws Exception {
		
		UserDTO user = (UserDTO) session.getAttribute("role");
		String user_id = user.getUser_id();
		
		dto.setUser_id(user_id);
		
		HashMap<String,Object> hm = new HashMap<String,Object>();		
		
		String url = postSvc.insertPostComment(dto, hm);
		
		
		model.addAllAttributes(hm);
		model.addAttribute("game_name",game_name);
		
		return url;
	}
	
//	http://localhost:8085/ProjectV12/getPostList?game_no=1&game_name=Legend of Azeroth&category_no=1&category_name=자유게시판
	@RequestMapping("getPostList")
	
	public String getPostList(int game_no, String game_name,
			int category_no, String category_name,
			@RequestParam(value="page" ,required=false,defaultValue= "1" ) int page,
			@RequestParam(value="cnt" ,required=false,defaultValue= "10" ) int cnt,
			Model model
			)throws Exception{
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String url =postSvc.postList(game_no, category_no, cnt, page, hm);
		
		model.addAllAttributes(hm);
		model.addAttribute("game_no",game_no);
		model.addAttribute("game_name",game_name);
		model.addAttribute("category_name", category_name);
		
		
		return url;
	}
	@RequestMapping("deletePost")
	public String deletePost(int game_no , int post_no ,String game_name, HttpSession session, Model model)throws Exception {
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		UserDTO user =(UserDTO) session.getAttribute("role");
		
		String user_id = user.getUser_id();
		
		System.out.println("유저 id는"+ user_id);
		
		String url = postSvc.deletePost(game_no, post_no,user_id ,hm);
		
		model.addAllAttributes(hm);
		
		model.addAttribute("game_name",game_name);
		
		return url;
	}
}
