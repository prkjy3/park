package com.v12.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IGameService {
		
	String gameList(int cnt, int page, HashMap<String, Object> hm);
	
	String gameListNo(int cnt, int page, HashMap<String, Object> hm);
	
	String gameListName(int cnt, int page, HashMap<String, Object> hm);
	
	String getGameInfo(int game_no, String page, HashMap<String, Object> hm);

	String updategetGameInfo(int game_no, String page, HashMap<String, Object> hm);
	
	String insertGame(GameDTO gameinfo, MultipartFile image, HashMap<String, Object> hm) throws IOException;
	
	String updateGame(GameDTO gamedto, MultipartFile image, HashMap<String, Object> hm) throws IOException;

	String deleteGame(int game_no, HashMap<String, Object> hm);
	
	String searchGameList(int cnt, int page, String game_name, HashMap<String, Object> hm);

	int getGameMaxNo();
	
//	String searchFavorite(String user_id, String page, HashMap<String, Object> hm);
//
//	String insertFavorite(FavoritesDTO favoritesdto, HashMap<String, Object> hm);
//
//	String deleteFavorite(int game_no, String user_id, HashMap<String, Object> hm);



}
