package com.v12.project;

public class GameImageDTO {
	private int game_no;
	private int game_img_no;
	private String game_img_name;
	private String game_img_path;
	
	public int getGame_no() {
		return game_no;
	}
	public void setGame_no(int game_no) {
		this.game_no = game_no;
	}
	public int getGame_img_no() {
		return game_img_no;
	}
	public void setGame_img_no(int game_img_no) {
		this.game_img_no = game_img_no;
	}
	public String getGame_img_name() {
		return game_img_name;
	}
	public void setGame_img_name(String game_img_name) {
		this.game_img_name = game_img_name;
	}
	public String getGame_img_path() {
		return game_img_path;
	}
	public void setGame_img_path(String game_img_path) {
		this.game_img_path = game_img_path;
	}
	
	@Override
	public String toString() {
		return "GameImageDTO [game_no=" + game_no + ", game_img_no=" + game_img_no + ", game_img_name=" + game_img_name
				+ ", game_img_path=" + game_img_path + "]";
	}
	
	

}
