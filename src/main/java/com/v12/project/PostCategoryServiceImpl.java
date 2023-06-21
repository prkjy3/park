package com.v12.project;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postcategorySvc")
public class PostCategoryServiceImpl implements IPostCategoryService{
	
	@Autowired
	private IDaoPostCategoryMEBC postcategoryMEBC;
	
	private static final Logger logger = LoggerFactory.getLogger(PostCategoryServiceImpl.class);
	

	@Override//게시판 카테고리를 단건조회
	public String selectPostCategory(int category_no, String page, HashMap<String, Object> hm) {
		
		logger.info("selectPostCategory called =====");
		PostCategoryDTO postcategorydto = postcategoryMEBC.selectPostCategory(category_no);
		
		hm.put("DTO", postcategorydto);
		hm.put("PAGE", page);
		
		return "selectPostCategory";
		
	}

	@Override
	@Transactional//게시판 카테고리를 입력
	public String insertPostCategory(PostCategoryDTO postcategorydto, HashMap<String, Object> hm) {
		
		int category_no = postcategorydto.getCategory_no();
		
		logger.info("insertPostCategory called =====");
		
		if(postcategoryMEBC.selectPostCategory(category_no) != null) {
			hm.put("ERR", "이미 있는 회원코드입니다.");
			
			return "errPage";
			
		}
		
		postcategoryMEBC.insertPostCategory(postcategorydto);
		
		return "redirect:PostCategoryListDatas.do?cnt=9&page=1";

		
	}

	@Override
	@Transactional//게시판 카테고리를 수정
	public String updatePostCategory(PostCategoryDTO postcategorydto, HashMap<String, Object> hm) {
		
		String url = null;
		
		int category_No = postcategorydto.getCategory_no();
		
		logger.info("updatePostCategory called =====");
		
		PostCategoryDTO postcategorydto2 = postcategoryMEBC.selectPostCategory(category_No);
//		if(postcategorydto2 == null) {
//			hm.put("ERR", "존재하지 않는 회원코드입니다.");
//			url = "errPage";
//			return url;
//		}
		
		postcategoryMEBC.updatePostCategory(postcategorydto);
//		System.out.println(3/0);
		
		return "redirect:PostCategoryListDatas.do?cnt=9&page=1";

		
	}

	@Override
	@Transactional//게시판 카테고리를 삭제
	public String deletePostCategory(int category_no, HashMap<String, Object> hm) {
		
		String url = null;
		
		logger.info("deleteUser called =====");
		
		PostCategoryDTO postcategorydto = postcategoryMEBC.selectPostCategory(category_no);
		
		if(postcategorydto == null) {
			hm.put("ERR", "존재하지 않는 회원코드입니다.");
			url = "errPage";
			return url;
			
		}
		
		postcategoryMEBC.deletePostCategory(category_no);

		return "redirect:PostCategoryListDatas.do?cnt=9&page=1";

		
	}
	
	@Override//게임리스트를 등록순으로 다건조회(게임이미지 포함)
	public String selectPostCategoryList(int cnt, int page, HashMap<String, Object> hm) {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		logger.info("GameList called =====");
		
		int count = postcategoryMEBC.getCount();
		
		List<PostCategoryDTO> postcategorylist = postcategoryMEBC.selectPostCategoryList(start, PAGE_SIZE);
		
//		for(int i = 0; i < gameimagelist.size(); i++){
//			GameImageDTO  dto1= gameimagelist.get(i);
//			System.out.println(dto1.getGame_img_name());
//			
//			
//		}
		
		if(postcategorylist == null) {
			
			hm.put("MSG", "조회결과가 없습니다.");
			return "main";
		}
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 9;
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
		
		hm.put("POST_CATEGORY_LIST", postcategorylist);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		return "postcategorylist";
		
	}
	
	
}
