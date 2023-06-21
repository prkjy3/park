package com.v12.project;

public interface IDaoGameMEBC {
	
	//게임 정보를 단건조회
	GameDTO selectGame(int game_no);
	
	//프라이머리키 가장큰 게임번호를 가지고 옴
	int getGameMaxNo();

	//게임정보를 입력
	void insertGame(GameDTO gamedto);
	
	//단건조회된 게임정보를 수정
	void updateGame(GameDTO gamedto);	
	
	//단건조회된 게임정보를 삭제
	void deleteGame(int game_no);
	
	//게임테이블의 내용개수를 가져옴
	int getCount();
	

}
