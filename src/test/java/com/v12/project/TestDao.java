package com.v12.project;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TestDao {

	
	
	@Autowired
	IDaoUser dao;
	
	@Autowired
	IDaoCommentMEBC daoC;
	
	@Autowired
	IDaoPostMEBC daoP;
	
	@Autowired
	IDaoPostQEBC postQEBC;
	
	@Autowired
	IDaoPostLikeMEBC daoPostLike;
	
	@Test
	public void selectUsertest() {
		UserDTO dto1 = new UserDTO();
		dto1.setUser_id("johndoe");
		
		UserDTO dto = dao.selectUser(dto1.getUser_id());
		
		
		assertEquals("johndoe", dto.getUser_id());
	}
	@Test
	@Transactional
	public void insertUsertest() {
		
		UserDTO dto = new UserDTO();
		
		dto.setCreated_at("2023-11-11");
		dto.setEmail("1111@naver.com");
		dto.setName("11");
		dto.setPassword("2222");
		dto.setUser_id("2222");
		dto.setRole("admin");
		dto.setUser_introduce("안녕하세요 반갑습니다.");
		
		dao.insertUser(dto);
		
		UserDTO dto1 = dao.selectUser("1111");
		assertEquals("1111", dto1.getUser_id());
		
		
		
		
	}
	@Test
	
	public void insertCommentTest() {
		
		int game_no =  1;
		int page = 1;
		int cnt = 5;
		
		HashMap<String ,Object> hm = new HashMap<String, Object>();
		
		ArrayList<CategoryDTO> list = postQEBC.categoryList();
		
		ArrayList<NoticeDTO> noticeList = postQEBC.noticeList(game_no, page, cnt);
		
		String name; 
		if(list != null) {
		
		
			for(int i = 0 ; i <list.size() ; i++ ) {
				ArrayList<PostDTO> postList  = null;
				name = "";
				CategoryDTO ctgry = list.get(i);
				
				name = ctgry.getCategory_name();
				postList =postQEBC.postList(game_no,ctgry.getCategory_no(),1,cnt);
			
			
				hm.put(name,list);
			
				}
			}else {
				
				String msg="카테고리가 없습니다";
				
				hm.put("MSG", msg);
			}
		
		
		
		
	}
	
	
}
