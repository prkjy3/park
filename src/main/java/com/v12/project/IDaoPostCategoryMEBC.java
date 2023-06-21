package com.v12.project;

import java.util.List;

public interface IDaoPostCategoryMEBC {
	
	//게시판 카테고리를 단건조회
	PostCategoryDTO selectPostCategory(int category_no);
	
	//게시판 카테고리를 입력
	void insertPostCategory(PostCategoryDTO postcategorydto);
	
	//게시판 카테고리를 수정
	void updatePostCategory(PostCategoryDTO postcategorydto);
	
	//게시판 카테고리를 삭제
	void deletePostCategory(int category_no);
	
	List<PostCategoryDTO> selectPostCategoryList(int start, int pagesize);
	
	int getCount();


}
