package com.v12.project;

public class FavoritesDTO {
	
	private String user_Id;
	
	private int game_No;

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getGame_No() {
		return game_No;
	}

	public void setGame_No(int game_No) {
		this.game_No = game_No;
	}

	@Override
	public String toString() {
		return "FavoriteDTO [user_Id=" + user_Id + ", game_No=" + game_No + "]";
	}

}
