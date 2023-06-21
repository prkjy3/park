package com.v12.project;

import java.util.List;

public interface IDaoNoticeImageMEBC {
    // 이미지 등록
    void insertNoticeImage(NoticeImageDTO noticeImageDTO);

    // 이미지 조회
//    List<NoticeImageDTO> selectNoticeImageList(int game_no, int notice_no);

    // 이미지 삭제
    void deleteNoticeImage(int game_no, int notice_no, String game_img_no);

    // 이미지 번호 확인
    int getMaxNoticeImageNo();
    
    NoticeImageDTO selectImage(int game_no, int notice_no);
       
}

