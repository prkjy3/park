package com.v12.project;

import java.util.List;

public interface IDaoGameQEBC {
	
	//게임테이블의 내용을 등록순으로 다건조회
	List<GameDTO> gameList(int start, int pagesize);
	
	//게임테이블의 내용을 게임번호순으로 다건조회
	List<GameDTO> gameListNo(int start, int pagesize);

	//게임테이블의 내용을 게임이름순으로 다건 조회
	List<GameDTO> gameListName(int start, int pagesize);
	
	//게임이미지 테이블의 내용을 게임번호순의 다건조회
	List<GameImageDTO> gameImageList(int start, int pagesize);
	
	
	List<GameDTO> gameListNameSearch(int start, int pagesize, String game_name);
	
	int chkPost(int game_no);

}
