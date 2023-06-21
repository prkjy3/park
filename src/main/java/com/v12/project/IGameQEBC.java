package com.v12.project;

import java.util.List;

public interface IGameQEBC {
	
	List<GameDTO> GameList(int start, int pagesize);
	
	List<GameDTO> findGame(int start, int pagesize);
	
	List<GameDTO> GameListNo(int start, int pagesize);

	List<GameDTO> GameListName(int start, int pagesize);
	
	List<GameImageDTO> GameImageList(int start, int pagesize);

}
