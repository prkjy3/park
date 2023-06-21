package com.v12.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface INoticeService {
	
	public String getNotice(int game_no,int notice_no,int page,int cnt, HashMap<String,Object> hm)throws Exception;
	
	public String updateNotice(NoticeDTO dto, String page, MultipartFile image , HashMap<String,Object> hm)throws Exception ;
	
	public String deleteNotice(int game_no,int notice_no, String page, HashMap<String,Object> hm )throws Exception;
	
	public String updateGetNotice(int game_no,String page, int notice_no,HashMap<String,Object> hm)throws Exception;
	
	// 글을 작성하는 메소드
	String insertNotice(NoticeDTO notice, MultipartFile images ,HashMap<String,Object> hm) throws IOException;
	
	public String noticeList(String game_name,int game_no, int cnt, int page, HashMap<String,Object> hm)throws Exception;
	
		
}
