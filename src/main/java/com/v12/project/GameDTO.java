package com.v12.project;

public class GameDTO {
	private int game_no;
	private String game_name;
	private String game_genre;
	private String release_date;
	private String game_url;
	private String infomation;
	
	public int getGame_no() {
		return game_no;
	}
	public void setGame_no(int game_no) {
		this.game_no = game_no;
	}
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public String getGame_genre() {
		return game_genre;
	}
	public void setGame_genre(String game_genre) {
		this.game_genre = game_genre;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getGame_url() {
		return game_url;
	}
	public void setGame_url(String game_url) {
		this.game_url = game_url;
	}
	public String getInfomation() {
		return infomation;
	}
	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}
	
	@Override
	public String toString() {
		return "GameDTO [game_no=" + game_no + ", game_name=" + game_name + ", game_genre=" + game_genre
				+ ", release_date=" + release_date + ", game_url=" + game_url + ", infomation=" + infomation + "]";
	}
	
}
