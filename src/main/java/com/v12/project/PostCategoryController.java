package com.v12.project;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostCategoryController {

	@Autowired
	private IPostCategoryService postcategorySvc;
	
	private static final Logger logger = LoggerFactory.getLogger(PostCategoryController.class);

	
	@RequestMapping("selectPostCategory.do")//게시판 카테고리를 단건조회
	public String selectPostCategory(@ModelAttribute("category_no") int category_no, 
						@ModelAttribute("page") String page, Model model)throws Exception {
		logger.info("selectPostCategory.do called =====");

		HashMap<String, Object> hm = new HashMap<String,Object>();
		String url = postcategorySvc.selectPostCategory(category_no, page, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("insertPostCategory")//게시판 카테고리를 입력하는 페이지로 이동
	public String insertPostCategoryForm() {
		logger.info("insertPostCategoryForm called =====");
		
		return "insertPostCategory";
	}
	
	@RequestMapping("insertPostCategory.do")//게시판 카테고리를 입력
	public String insertPostCategory(PostCategoryDTO postcategorydto, Model model) throws Exception{
		logger.info("insertPostCategory.do called =====");

		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = postcategorySvc.insertPostCategory(postcategorydto, hm);
		
		model.addAllAttributes(hm);

		return url;
	}

	@RequestMapping("updatePostCategory.do")//게시판 카테고리를 수정
	public String updatePostCategory(PostCategoryDTO postcategorydto, Model model)throws Exception {
		logger.info("updatePostCategory.do called =====");

		HashMap<String, Object> hm = new HashMap<String,Object>();
				
		String url = postcategorySvc.updatePostCategory(postcategorydto, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping(value="deletePostCategory.do")//게시판 카테고리를 삭제
	public String deletePostCategory(@ModelAttribute("category_no") int category_no, Model model)throws Exception {
		logger.info("deletePostCategory.do called =====");
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = postcategorySvc.deletePostCategory(category_no, hm);

		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("PostCategoryListDatas.do")//게임리스트를 등록순으로 다건조회(게임이미지 포함)
	public String selectPostCategoryList(@RequestParam(value="cnt", required=false, defaultValue="9") int cnt,
			String page, Model model)throws Exception {
		logger.info("PostCategoryListDatas.do called =====");

	
		int	nPage = 1;
		if(page=="") {
			
			page="1";
					
		}else if(page== null) {
			page="1";
		}
		
		nPage = Integer.parseInt(page);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = postcategorySvc.selectPostCategoryList(cnt, nPage, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
}
