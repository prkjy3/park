package com.v12.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service("noticeSvc")
public class NoticeServiceImpl  implements INoticeService{

	@Autowired
	IDaoNoticeMEBC noticeMEBC;
	
	@Autowired
	IDaoNoticeQEBC noticeQEBC;
	
	@Autowired
	IDaoNoticeImageMEBC noticeImageMEBC;
	
	@Override//공지사항 상세보기 
	@Transactional
	public String getNotice(int game_no, int notice_no, int page, int cnt, HashMap<String, Object> hm) {	
		
		String url ="";
		
		
		NoticeDTO dto = noticeMEBC.selectNotice(game_no, notice_no);
		
		if(dto == null) {
			
				hm.put("ERR","존재하지 않는 자료입니다.");
				url = "errPage";
				return url;	
		}
		NoticeImageDTO image = noticeImageMEBC.selectImage(game_no, notice_no); 
		
		noticeMEBC.updateNoticeReadCount(game_no, notice_no);
		
		hm.put("NOTICE", dto);
		hm.put("NOTICEIMAGE", image);
		url= "getNotice";
		
		return url;
	}

	@Override
	@Transactional
	public String updateNotice(NoticeDTO dto, String page, MultipartFile image , HashMap<String, Object> hm)throws Exception {
		String url ="";
		
		NoticeDTO noticeChk   =noticeMEBC.selectNotice(dto.getGame_no(),dto.getNotice_no());
		if(noticeChk == null) {
			hm.put("ERR","존재하지 않는 자료입니다.");
			url = "errPage";
			return url;
		}
		
		NoticeImageDTO chkImg = noticeImageMEBC.selectImage(dto.getGame_no(), dto.getNotice_no());
		
		
		
		
		 if (image != null && !image.isEmpty()) {
			 	
			 if(chkImg != null ) {
				 
				 File file = new File("C:/2211PJY/workspace/ProjectV29/src/main/webapp/"+ chkImg.getNotice_img_path());
						 file.delete();
				 noticeImageMEBC.deleteNoticeImage(chkImg.getGame_no(), chkImg.getNotice_no(), chkImg.getNotice_img_no());	 
				 }			 
			 	
	        	int maxNoticeImageNo = noticeImageMEBC.getMaxNoticeImageNo();
	        	maxNoticeImageNo++;
	        	String noticeNo = String.valueOf(maxNoticeImageNo);
	            String fileName = image.getOriginalFilename();
	            String filePath = "C:/2211PJY/workspace/ProjectV29/src/main/webapp/resources/img/notice/"+ dto.getGame_no() + dto.getNotice_no() + fileName; // 이미지를 저장할 경로
	            String dataPath = "resources/img/notice/"+dto.getGame_no()+ dto.getNotice_no()+fileName;
	            // 이미지 파일을 저장
	            File dest = new File(filePath);
	            image.transferTo(dest);

	            // NoticeImage 객체를 생성
	            NoticeImageDTO noticeImage = new NoticeImageDTO();
	            noticeImage.setNotice_no(dto.getNotice_no());
	            noticeImage.setGame_no(dto.getGame_no());
	            noticeImage.setNotice_img_no(noticeNo);
	            noticeImage.setNotice_img_name(fileName);
	            noticeImage.setNotice_img_path(dataPath);

	            // NoticeImage 객체를 DB에 저장
	            noticeImageMEBC.insertNoticeImage(noticeImage);
		
		 }
		
		noticeChk.setReadCount(noticeChk.getReadCount()+1);
		noticeMEBC.updateNotice(dto);
		
		url = "redirect:/noticeList?game_no="+ dto.getGame_no()+"&cnt=10";
		
		return url;
	}
	
	@Override
	@Transactional
	public String deleteNotice(int game_no, int notice_no, String page, HashMap<String, Object> hm)throws Exception{
		
		
		String url ="";
		NoticeDTO noticeChk   =noticeMEBC.selectNotice(game_no,notice_no);
		if(noticeChk == null) {
			hm.put("ERR","존재하지 않는 자료입니다.");
			url = "errPage";
			return url;
			
		}
		NoticeImageDTO chkImg = noticeImageMEBC.selectImage(game_no, notice_no);
		
		if(chkImg != null ) {
			 
			 File file = new File("C:/2211PJY/workspace/ProjectV29/src/main/webapp/"+ chkImg.getNotice_img_path());
					 file.delete();
			 noticeImageMEBC.deleteNoticeImage(chkImg.getGame_no(), chkImg.getNotice_no(), chkImg.getNotice_img_no());	 
			 }
			
			
		
		noticeMEBC.deleteNotice(game_no, notice_no);
		
		url="redirect:/noticeList?game_no="+ game_no + "&cnt=10";
		return url;
	}

	@Override
	@Transactional
	public String updateGetNotice(int game_no, String page, int notice_no, HashMap<String, Object> hm) {
		String url ="";
		
		
		NoticeDTO dto = noticeMEBC.selectNotice(game_no, notice_no);
		
		if(dto == null) {
			
				hm.put("ERR","존재하지 않는 자료입니다.");
				url = "errPage";
				return url;	
		}
		NoticeImageDTO image = noticeImageMEBC.selectImage(game_no, notice_no); 
		
		noticeMEBC.updateNoticeReadCount(game_no, notice_no);
		
		hm.put("NOTICE", dto);
		hm.put("NOTICEIMAGE", image);
		
		url = "updateNoticeForm";
		
		return url;
	}

	




	@Override
	public String noticeList(String game_name, int game_no, int cnt, int page, HashMap<String, Object> hm) {
		String url ="";
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1 ;
		
		
		int count = noticeMEBC.getNoticeCount(game_no);
		
		ArrayList<NoticeDTO> list = noticeQEBC.noticeList(game_no,start ,PAGE_SIZE);
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 10;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		} else {
			startPage = (int)  (page/ PAGE_BLOCK)		* PAGE_BLOCK + 1;
		}
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		hm.put("NOTICE_LIST", list);
		hm.put("GAME_NO", game_no);
		hm.put("GAME_NAME",game_name);
		
		
		return url;
	}

	@Override
	@Transactional
	public String insertNotice(NoticeDTO notice, MultipartFile image ,HashMap<String,Object> hm) throws IOException {
        // 1. NoticeDTO 객체를 DB에 저장
		
		int no = noticeMEBC.selectMaxNo(notice.getGame_no());
		
		
		notice.setNotice_no(no+1);
		
		noticeMEBC.insertNotice(notice);

		
		// 2. 이미지 파일을 저장하고, NoticeImage 객체를 DB에 저장
        if (image != null && !image.isEmpty()) {
        	
        	int maxNoticeImageNo = noticeImageMEBC.getMaxNoticeImageNo();
        	maxNoticeImageNo++;
        	String noticeNo = String.valueOf(maxNoticeImageNo);
            String fileName = image.getOriginalFilename();
            
            String filePath = "C:/2211PJY/workspace/ProjectV29/src/main/webapp/resources/img/notice" + notice.getGame_no() + notice.getNotice_no() + fileName; // 이미지를 저장할 경로
            String dataPath = "resources/img/notice"+notice.getGame_no()+ notice.getNotice_no()+fileName;
            // 이미지 파일을 저장
            File dest = new File(filePath);
            image.transferTo(dest);

            // NoticeImage 객체를 생성
            NoticeImageDTO noticeImage = new NoticeImageDTO();
            noticeImage.setNotice_no(notice.getNotice_no());
            noticeImage.setGame_no(notice.getGame_no());
            noticeImage.setNotice_img_no(noticeNo);
            noticeImage.setNotice_img_name(fileName);
            noticeImage.setNotice_img_path(dataPath);

            // NoticeImage 객체를 DB에 저장
            noticeImageMEBC.insertNoticeImage(noticeImage);
        }else {
            // 이미지를 첨부하지 않은 경우, 이미지 파일을 저장하지 않음
            System.out.println("이미지가 첨부되지 않았습니다.");
        }
        
        
        
		return "redirect:/noticeList?game_no="+ notice.getGame_no() ;
    }



}
