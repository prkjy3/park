package com.v12.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



@Service("postSvc")
public class PostServiceImpl implements IPostService{

	@Autowired
	IDaoPostMEBC postMEBC;
	
	@Autowired
	IDaoCommentMEBC commentMEBC;
	
	@Autowired
	IDaoPostQEBC postQEBC;
	
	@Autowired
	IDaoPostLikeMEBC postLikeMEBC;
	


	
	@Autowired
	IDaoPostImageMEBC postImageMEBC;
	
	
	private static final Logger logger =LoggerFactory.getLogger(UserController.class);
	
	@Override
	public String postList(int game_no,int category_no,int cnt,int page,HashMap<String, Object> hm)throws Exception {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		int count = postMEBC.countPost(game_no, category_no);
		
		ArrayList<PostDTO> list = postQEBC.postList(game_no, category_no, page, cnt);
		
		if(list == null) {
			
			String msg = "등록된 게시물이 없습니다.";
			
			hm.put("MSG",msg);
			return "postList";
		}
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 10;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		}else {
			startPage = (int) (page / PAGE_BLOCK) * PAGE_BLOCK + 1;
		}
		
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		hm.put("PostList", list);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		return "postList";
	}



	@Override
	@Transactional
	public String insertPost(PostDTO dto, MultipartFile image, HashMap<String, Object> hm)throws Exception {
		
		String url = "";
		
		int no = postMEBC.selectMaxNo(dto.getGame_no(), dto.getPost_no());
		no++;
		PostDTO chkPost = postMEBC.selectPost(dto.getGame_no(), no);
		
		if(chkPost != null) {
			
			String msg = "오류가 발생했습니다.";
			
			hm.put("ERR", msg);
			
			url = "errPage";
			
			return url;
			
		}
		
		dto.setPost_no(no);
		postMEBC.insertPost(dto);
		
		
		 if (image != null && !(image.isEmpty())) {
		
		int maxPostImageNo = postImageMEBC.getMaxPostImageNo();
		
		maxPostImageNo++;
		
		String fileName = image.getOriginalFilename();
		
        
        
		
		String filePath = "C:/2211PJY/workspace/ProjectV29/src/main/webapp/resources/img"+"post"+dto.getGame_no()+ dto.getPost_no()+fileName;
		
		String dataPath = "resources/img/post"+dto.getGame_no()+ dto.getPost_no()+fileName;
		
		
		File dest = new File(filePath);
		image.transferTo(dest);
		
		PostImageDTO postImage = new PostImageDTO();
		postImage.setPost_no (dto.getPost_no());
		postImage.setGame_no(dto.getGame_no());
		postImage.setPost_img_no(maxPostImageNo);
		postImage.setPost_img_name(fileName);
		postImage.setPost_img_path(dataPath);
		
		postImageMEBC.insertPostImage(postImage);

	
		}
		 
		dto.setPost_no(no);
		
		
		
		
		
		url ="redirect:/getPostList?game_no="+dto.getGame_no();
		
		return url;
	}

	@Override
	@Transactional
	public String updatePost(PostDTO dto,MultipartFile image, HashMap<String, Object> hm) throws Exception{
		
		PostDTO chk = postMEBC.selectPost(dto.getGame_no(), dto.getPost_no());
		
		if(chk == null) {
			
			String msg="해당 자료가 없습니다.";
			
			return "errPage";
			
		}
		
		postMEBC.updatePost(dto);
		
		 if (image != null && !(image.isEmpty())) {
				
				int maxPostImageNo = postImageMEBC.getMaxPostImageNo();
				
				maxPostImageNo++;
				
				String fileName = image.getOriginalFilename();
				
		        
		        
				
				String filePath = "C:/2211PJY/workspace/ProjectV29/src/main/webapp/resources/img/post"+dto.getGame_no()+ dto.getPost_no()+fileName;
				
				String dataPath = "resources/img/post"+dto.getGame_no()+ dto.getPost_no()+fileName;
				
				
				File dest = new File(filePath);
				image.transferTo(dest);
				
				PostImageDTO postImage = new PostImageDTO();
				postImage.setPost_no (dto.getPost_no());
				postImage.setGame_no(dto.getGame_no());
				postImage.setPost_img_no(maxPostImageNo);
				postImage.setPost_img_name(fileName);
				postImage.setPost_img_path(dataPath);
				
				postImageMEBC.insertPostImage(postImage);

			
				}
		
		
		
		
		
		return "redirect:/getPost?game_no=" + dto.getGame_no() + "&post_no="+dto.getPost_no();
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public String deletePost(int game_no, int post_no,String user_id ,HashMap<String, Object> hm)throws Exception {
		
		
		
		PostDTO dto = postMEBC.selectPost(game_no, post_no);
		
		PostImageDTO img = postImageMEBC.selectPostImage(game_no, post_no);
		
		System.out.println(dto.getUser_id());
		
		if(dto == null) {
			System.out.println("이게 실행되면 안됨");
			String msg="해당 자료가 없습니다.";
			hm.put("ERR", msg);
			return "errPage";
		}
		if( !(user_id.equals(dto.getUser_id())) ) {
			System.out.println("이게 실행되면 안됨2");
			String msg="아이디가 다릅니다.";
			hm.put("ERR", msg);
			return "errPage";
		}
		else {
			if(img != null)
			postImageMEBC.deletePostImage(game_no, post_no , img.getPost_img_no());
			
			
			postMEBC.deletePost(game_no, post_no);
		}

		
		return "redirect:/getPostList?game_no="+ dto.getGame_no() ;
	}

	@Override
	@Transactional
	public String updatePostComment(CommentDTO dto, HashMap<String, Object> hm)throws Exception {
			
		if(commentMEBC.selectComment(dto.getGame_no(), dto.getPost_no(), dto.getComment_no()) == null) {
			String msg="해당 자료가 없습니다.";
			hm.put("ERR", msg);
			
			return "errPage";
		}
		commentMEBC.updateComment(dto);
		
		
		
		return "redirect:/getPost?gmae_no="+dto.getGame_no()+"&post_no="+dto.getPost_no() ;
	}

	@Override
	@Transactional
	public String deletePostComment(int game_no, int post_no,int comment_no, HashMap<String, Object> hm)throws Exception {
		
		PostDTO dto = postMEBC.selectPost(game_no, post_no);
		
		String url = "";
		if(dto == null) {
		
			String msg = "해당 자료가 없습니다.";
			
			hm.put("ERR",msg );
			return "errPage";
		}
		
		commentMEBC.deleteComment(game_no, post_no, comment_no);
		
		hm.put("MSG", "댓글이 삭제되었습니다.");
		
		return "redirect:/getPost?game_no=" + dto.getGame_no() + "&post_no="+dto.getPost_no()+"&category_no=" ;
	}

	@Override
	@Transactional
	public String insertPostComment(CommentDTO dto, HashMap<String, Object> hm)throws Exception {
		
		String url = "";
		
		int no = commentMEBC.selectMaxNo(dto.getGame_no(), dto.getPost_no());
		
		if(commentMEBC.selectComment(dto.getGame_no(), dto.getPost_no(), no+1) != null) {
			
			String msg = "오류가 발생했습니다.";
			
			hm.put("ERR",msg);
			return "errPage";	
		}	
		
		
		
		int ref_no = dto.getRef_no();
		int re_step = dto.getRe_step();
		int re_level = dto.getRe_level();
		if(ref_no==0) {
			dto.setRef_no(no+1);
			dto.setComment_no(no+1);
			dto.setRe_step(0);
			dto.setRe_level(0);
		}else {
			
			
			
			commentMEBC.updateCommentStep(dto.getRef_no(),dto.getRe_step() ,dto.getGame_no(), dto.getPost_no());
			dto.setComment_no(no+1);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);	
		}
		
		
		
		commentMEBC.insertComment(dto);
		
		
		return "redirect:/getPost?game_no=" + dto.getGame_no() + "&post_no="+dto.getPost_no();
	}



	@Override
	public String accessCommunity(int game_no, int cnt,int page, HashMap<String, Object> hm) {
		String url="CommunityHome";
		
		ArrayList<CategoryDTO> list = postQEBC.categoryList();
		
		ArrayList<NoticeDTO> noticeList = postQEBC.noticeList(game_no, page, cnt);
		
		String name; 
		if(list != null) {
		
		
			for(int i = 0 ; i <list.size() ; i++ ) {
				ArrayList<PostDTO> postList  = null;
				name = "";
				int category_no = list.get(i).getCategory_no();
				
				;
				postList =postQEBC.postList(game_no,category_no,1,cnt);
				list.get(i).setPost(postList);
				System.out.println(name);
			
			
			
				}
			}else {
				
				String msg="카테고리가 없습니다";
				
				hm.put("MSG", msg);
			}
		hm.put("CATEGORY_LIST", list);
		hm.put("NOTICE_LIST", noticeList);
		
		return url;
	}

	@Override
	public String insertPostForm(int game_no, HashMap<String, Object> hm) {
		
		ArrayList<CategoryDTO> list = postQEBC.categoryList();
		
		
		
		
		hm.put("CATEGORY_LIST", list);
		hm.put("GAME_NO", game_no);
		
		return "insertPostForm";
	}



	@Override
	public String updatePostForm(String user_id, int game_no, int post_no,int category_no, HashMap<String, Object> hm) {
		
		
		
		PostDTO dto  =  postMEBC.selectPost(game_no, post_no);
		
	
		
		
		if(dto == null) {
			
			String msg = "해당 자료가 없습니다.";
			
			hm.put("ERR", msg);
			
			return "errPage";
			
		}
		
		
		
		
		if(!(dto.getUser_id().equals(user_id))) {
			
		hm.put("ERR", "해당 게시글 작성자가 아닙니다.");
		
		return "errPage";
		
		}
		PostImageDTO image = postImageMEBC.selectPostImage(game_no, post_no);
	
		
		
		
		
		hm.put("POST", dto);		
		hm.put("IMAGE", image);
		
		
		return "updatePostForm";
	}



	@Override
	@Transactional
	public String getPost(int game_no, int post_no, HashMap<String, Object> hm, String user_id, int page,int cnt) {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		int count = commentMEBC.count(game_no, post_no);
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 10;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		}else {
			startPage = (int) (page / PAGE_BLOCK) * PAGE_BLOCK + 1;
		}
		
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		
		
		hm.put("CommentCount", count);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		
		
		PostDTO dto  =  postMEBC.selectPost(game_no, post_no);
		PostImageDTO image= postImageMEBC.selectPostImage(game_no, post_no);
		
		
		
		if(dto == null) {
			
			String msg = "해당 자료가 없습니다.";
			
			hm.put("ERR", msg);
			
			 				
			return "errPage";
			
		}
		postMEBC.updatePostReadCount(game_no, post_no);
		
		
		logger.info("getPost call===================");
		
		PostLikeDTO likeDto = postLikeMEBC.selectPostLike(user_id,game_no, post_no);
		
		ArrayList<CommentDTO> list = postQEBC.postComment(game_no, post_no, page, cnt);
		
		
		hm.put("POST", dto);	
		hm.put("LIKE", likeDto);	
		hm.put("COMMENT", list);
		hm.put("IMAGE", image);
		
		return "getPost";
	}

	
	
}
