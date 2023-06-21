package com.v12.project;

public interface IGameMEBC {
	
	GameDTO selectGame(int game_No);
	
	int getGameMaxNo();

	void insertGame(GameDTO gamedto);
	
	void updateGame(GameDTO gamedto);	
	
	void deleteGame(int game_No);
	
	int getCount();
	

}
