package com.v12.project;

import java.util.ArrayList;

public class CategoryDTO {
	
	private int category_no;
	private String category_name;
	private ArrayList<PostDTO> post;
	
	public ArrayList<PostDTO> getPost() {
		return post;
	}
	public void setPost(ArrayList<PostDTO> post) {
		this.post = post;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	
}
