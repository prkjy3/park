package com.v12.project;

public interface IDaoNoticeMEBC {
	
	public NoticeDTO selectNotice(int game_no , int notice_no);
	
	public int selectMaxNo(int game_no);
	
	public void updateNotice(NoticeDTO noticeDto);
	
	public void updateNoticeReadCount(int game_no, int notice_no);
	
	public void deleteNotice(int game_no , int notice_no);
	
	public void insertNotice(NoticeDTO noticeDto);
	
	public int getNoticeCount(int game_no);
	
}
