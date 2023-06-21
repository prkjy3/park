package com.v12.project;

public class PostDTO {
	
	private int game_no ;
	private int post_no;
	private int category_no;
	private String user_id;
	private String title;
	private String reg_date;
	private int readCount;
	private String post_content;
	private String mod_date;
    private int like_count;
	private String category_name;
	
	
	
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getGame_no() {
		return game_no;
	}
	public void setGame_no(int game_no) {
		this.game_no = game_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public Integer getLike_count() {
		return like_count;
	}
	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}

	
	
	

	
}
