package com.v12.project;

public interface IDaoPostImageMEBC {
	
	void insertPostImage(PostImageDTO dto);
	
    // 이미지 삭제
    void deletePostImage(int game_no, int post_no,int post_img_no);

    // 이미지 번호 확인
    int getMaxPostImageNo();
   
    // 이미지 찾기
    PostImageDTO selectPostImage(int game_no ,int post_no);
    
    
	

}
