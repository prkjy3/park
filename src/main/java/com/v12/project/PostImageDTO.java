package com.v12.project;

public class PostImageDTO {
	
	private int game_no ;
	private int post_no ;
	private int post_img_no;
	private String post_img_name;
	private String post_img_path;
	
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
	public int getPost_img_no() {
		return post_img_no;
	}
	public void setPost_img_no(int post_img_no) {
		this.post_img_no = post_img_no;
	}
	public String getPost_img_name() {
		return post_img_name;
	}
	public void setPost_img_name(String post_img_name) {
		this.post_img_name = post_img_name;
	}
	public String getPost_img_path() {
		return post_img_path;
	}
	public void setPost_img_path(String post_img_path) {
		this.post_img_path = post_img_path;
	}
	
}
