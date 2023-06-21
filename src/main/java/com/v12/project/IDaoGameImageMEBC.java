package com.v12.project;

public interface IDaoGameImageMEBC {
    // 이미지 등록
    void insertGameImage(GameImageDTO gameImageDTO);

    // 이미지 조회
    GameImageDTO selectGameImage(int game_no);
    
    void updateGameImage(GameImageDTO gameImageDTO);

    // 이미지 삭제
    void deleteGameImage(int game_no);

    // 이미지 번호 확인
    int getMaxGameImageNo();
    
//    GameImageDTO selectImage();
       
}
