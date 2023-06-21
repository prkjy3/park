package com.v12.project;

public interface IDaoPostCategory {
	
	// 카테고리 이름 찾기
	PostCategoryDTO selectCategory();
	
	// 카테고리 추가 
	void insertPostCategory(PostCategoryDTO dto);
	
	// 카테고리 업데이트
	void updateCategory(PostCategoryDTO dto);
	
	// 카테고리 삭제
	void deleteCategory(int category_no);
	
	// 카테고리 갯수 불러오기( 해당 커뮤니티에 들어갈때 그 커뮤니티 메인 화면에 띄울 것들 불러올 때 사용하는것)
	int CategoryCount();
	
}
