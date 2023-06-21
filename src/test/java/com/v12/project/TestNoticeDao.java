package com.v12.project;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TestNoticeDao {

	@Autowired
	IDaoNoticeMEBC noticeDao;
	
	@Autowired
	IDaoNoticeQEBC noticeQEBC;
	
//	@Test
//	public void noticeTest() {
//		
//		
//		NoticeDTO dto = noticeDao.selectNotice(2,1);
//		
//		
//		
//		
//		
//		System.out.println(dto.toString());
//		
//		assertNotNull(dto);
//		
//		
//	}
//	@Test
//	@Transactional
//	public void noticeUpdateTest() {
//		
//		
//		NoticeDTO dto = noticeDao.selectNotice(2,1);
//		
//		
//		dto.setContent("수정된 코멘트입니다.");
//		dto.setTitle("수정된 타이틀입니다.");
//		
//		noticeDao.updateNotice(dto);
//		
//		NoticeDTO dtoChk = noticeDao.selectNotice(2,1);
//		
//		
//		assertEquals(dtoChk.getContent(),"수정된 코멘트입니다.");
//			
//	}
//	@Test
//	@Transactional
//	public void noticeUpdateReadCountTest() {
//		
//		
//		NoticeDTO dto = noticeDao.selectNotice(2,1);
//		
//		int a = dto.getReadCount();
//		noticeDao.updateNoticeReadCount(2,1);
//		NoticeDTO dto1 = noticeDao.selectNotice(2,1);
////		NoticeDTO dtoChk = noticeDao.selectNotice(2,1);
////		
////		
////		assertEquals(dtoChk.getContent(),"수정된 코멘트입니다.");
//		assertEquals(dto1.getReadCount(), a+1);
//			
//	}
//	@Test
//	@Transactional
//	public void noticeInsertTest() {
//		
//		NoticeDTO dto = new NoticeDTO();
//		
//		dto.setContent("입력테스트 내용입니다.");
//		dto.setGame_No(11);
//		dto.setNotice_No(11);
//		dto.setTitle("입력테스트입니다");
//
//
//		noticeDao.insertNotice(dto);
//		
//		
//		assertNotNull(noticeDao.selectNotice(11, 11));
//		
//	}
//	@Test
//	@Transactional
//	public void deleteNoticeTest() {
//		
//		NoticeDTO dto = noticeDao.selectNotice(1, 1);
//		
//		assertNotNull(dto);
//		
//		
//		noticeDao.deleteNotice(1,1);
//		
//		
//		NoticeDTO dto1 = noticeDao.selectNotice(1, 1);
//		
//		assertNull(dto1);
//		
//		
//		
//	}
	@Test
	public void noticeListTest() {
		
		int game_no = 1;
		
		int cnt = 5;
		
		int page = 1;
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1 ;
		
		ArrayList<NoticeDTO> list = noticeQEBC.noticeList(game_no,start ,PAGE_SIZE);
		
		
		
	}
}
