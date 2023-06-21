package com.v12.project;

import java.util.HashMap;

public interface IPostCategoryService {
	
	String selectPostCategory(int category_no, String page, HashMap<String, Object> hm)throws Exception;
	
	String insertPostCategory(PostCategoryDTO postcategorydto, HashMap<String, Object> hm)throws Exception;
	
	String updatePostCategory(PostCategoryDTO postcategorydto, HashMap<String, Object> hm)throws Exception;

	String deletePostCategory(int category_no, HashMap<String, Object> hm)throws Exception;

	String selectPostCategoryList(int cnt, int page, HashMap<String, Object> hm)throws Exception;


}
