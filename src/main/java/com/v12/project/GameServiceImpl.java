package com.v12.project;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("gameSvc")
public class GameServiceImpl implements IGameService{
	
	@Autowired
	private IDaoGameMEBC gameMEBC;
	
	@Autowired
	private IDaoGameQEBC gameQEBC;
	
	@Autowired
	private IDaoGameImageMEBC gameImageMEBC;
	
	
	private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
	

	@Override//게임리스트를 등록순으로 다건조회(게임이미지 포함)
	public String gameList(int cnt, int page, HashMap<String, Object> hm) {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		logger.info("GameList called =====");
		
		int count = gameMEBC.getCount();
		
		List<GameDTO> gamelist = gameQEBC.gameList(start, PAGE_SIZE);
		
		List<GameImageDTO> gameimagelist = gameQEBC.gameImageList(start, PAGE_SIZE);
		
//		for(int i = 0; i < gameimagelist.size(); i++){
//			GameImageDTO  dto1= gameimagelist.get(i);
//			System.out.println(dto1.getGame_img_name());
//			
//			
//		}
		
		if(gamelist == null) {
			
			hm.put("MSG", "조회결과가 없습니다.");
			return "main";
		}
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 9;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		}else {
			startPage = (int) (page / PAGE_BLOCK) * PAGE_BLOCK + 1;
		}
		
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		hm.put("GAME_LIST", gamelist);
		hm.put("GAME_IMAGE_LIST", gameimagelist);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		return "main";
		
	}
	
	@Override//게임리스트를 번호순으로 다건조회(게임이미지 포함)
	public String gameListNo(int cnt, int page, HashMap<String, Object> hm) {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		logger.info("GameList called =====");
		
		int count = gameMEBC.getCount();
		
		List<GameDTO> gamelist = gameQEBC.gameListNo(start, PAGE_SIZE);
		
		List<GameImageDTO> gameimagelist = gameQEBC.gameImageList(start, PAGE_SIZE);
		
		if(gamelist == null) {
			
			hm.put("MSG", "조회결과가 없습니다.");
			return "main";
		}
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 9;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		}else {
			startPage = (int) (page / PAGE_BLOCK) * PAGE_BLOCK + 1;
		}
		
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		hm.put("GAME_LIST", gamelist);
		hm.put("GAME_IMAGE_LIST", gameimagelist);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		return "main";
		
	}
	
	@Override//게임리스트를 이름순으로 다건조회(게임이미지 포함)
	public String gameListName(int cnt, int page, HashMap<String, Object> hm) {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		logger.info("GameListName called =====");
		
		int count = gameMEBC.getCount();
		
		List<GameDTO> gamelist = gameQEBC.gameListName(start, PAGE_SIZE);
		
		List<GameImageDTO> gameimagelist = gameQEBC.gameImageList(start, PAGE_SIZE);
		
		if(gamelist == null) {
			
			hm.put("MSG", "조회결과가 없습니다.");
			return "main";
		}
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 9;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		}else {
			startPage = (int) (page / PAGE_BLOCK) * PAGE_BLOCK + 1;
		}
		
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		hm.put("GAME_LIST", gamelist);
		hm.put("GAME_IMAGE_LIST", gameimagelist);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		return "main";
		
	}
	

	@Override//게임정보를 단건조회
	public String getGameInfo(int game_no, String page, HashMap<String, Object> hm) {
		
		logger.info("getGameInfo called =====");
		GameDTO gamedto = gameMEBC.selectGame(game_no);
		GameImageDTO gameimage = gameImageMEBC.selectGameImage(game_no);
		
		hm.put("DTO", gamedto);
		hm.put("GAMEIMAGE", gameimage);
		hm.put("PAGE", page);
		
		return "getGameInfo";
		
	}
	
	@Override//수정할 게임정보를 단건조회
	public String updategetGameInfo(int game_no, String page, HashMap<String, Object> hm) {
		
		logger.info("updategetGameInfo called =====");
		GameDTO gamedto = gameMEBC.selectGame(game_no);
		GameImageDTO gameimage = gameImageMEBC.selectGameImage(game_no);

		hm.put("DTO", gamedto);
		hm.put("GAMEIMAGE", gameimage);
		hm.put("PAGE", page);
		
		return "updategetGameInfo";
		
	}

    @Override
    @Transactional//게임정보를 입력(가장 큰 게임번호 + 1)
    public String insertGame(GameDTO gamedto, MultipartFile image, HashMap<String, Object> hm) throws IOException {
        int gamemaxno = gameMEBC.getGameMaxNo();
        gamedto.setGame_no(gamemaxno + 1);
        gameMEBC.selectGame(gamedto.getGame_no());
        
        if(gameMEBC.selectGame(gamedto.getGame_no()) != null) {
            hm.put("ERR", "이미 있는 게임코드입니다.");
            return "errPage";
        }

        gameMEBC.insertGame(gamedto);

        hm.put("GAMEMAXNO", gamemaxno);
        
		// 2. 이미지 파일을 저장하고, GameImage 객체를 DB에 저장
        if (image != null && !(image.isEmpty())) {
        	
        	int maxgameImageNo = gameImageMEBC.getMaxGameImageNo();
        	maxgameImageNo++;
            String fileName = image.getOriginalFilename();
            String filePath = "C:/2211PJY/workspace/ProjectV29/src/main/webapp/resources/img/gamelogo" + gamedto.getGame_no() + gamedto.getGame_name() + fileName;
            String dataPath = "resources/img/game"+gamedto.getGame_no()+fileName;
            // 이미지를 저장할 경로
            
            // 이미지 파일을 저장
//            InputStream in = image.getInputStream();
//            OutputStream out = new FileOutputStream(fileName);
            File dest = new File(filePath);
            image.transferTo(dest);

            // GameImage 객체를 생성
            GameImageDTO gameImage = new GameImageDTO();
            gameImage.setGame_no(gamedto.getGame_no());
            gameImage.setGame_img_no(maxgameImageNo);
            gameImage.setGame_img_name(fileName);
            gameImage.setGame_img_path(dataPath);

            // GameImage 객체를 DB에 저장
            gameImageMEBC.insertGameImage(gameImage);
        }

        return "redirect:GameListDatas.do?cnt=9";
    }

    @Override//가장 큰 게임번호를 가져옴
    public int getGameMaxNo() {
        return gameMEBC.getGameMaxNo();
    }
	

	
    @Override
    @Transactional // 단건조회된 게임정보를 수정
    public String updateGame(GameDTO gamedto, MultipartFile image, HashMap<String, Object> hm) throws IOException {

        String url = null;

        int game_no = gamedto.getGame_no();

        logger.info("updateGame called =====");

        GameDTO gamedto2 = gameMEBC.selectGame(game_no);

        if (gamedto2 == null) {
            hm.put("ERR", "존재하지 않는 회원코드입니다.");
            url = "errPage";
            return url;
        }

        gameMEBC.updateGame(gamedto);

        // 이미지 파일을 업데이트
        if (image != null && !(image.isEmpty())) {//여기서 안됨
        	gameImageMEBC.deleteGameImage(game_no);
        	
            int maxgameImageNo = gameImageMEBC.getMaxGameImageNo();
            maxgameImageNo++;
            String fileName = image.getOriginalFilename();//image가 null이라서안됨
            String filePath = "C:/2211LJB/workspace/ProjectV25/src/main/webapp/resources/img/gamelogo/" + gamedto.getGame_no() + gamedto.getGame_name() + fileName;
            // 이미지를 저장할 경로

            // 이미지 파일을 저장
            File dest = new File(filePath);
            image.transferTo(dest);

            GameImageDTO gameImage = gameImageMEBC.selectGameImage(game_no);
            if (gameImage == null) {
                // GameImage 객체를 생성
                gameImage = new GameImageDTO();
                gameImage.setGame_no(gamedto.getGame_no());
                gameImage.setGame_img_no(maxgameImageNo);
                gameImage.setGame_img_name(fileName);
                gameImage.setGame_img_path(filePath);

                // GameImage 객체를 DB에 저장
                gameImageMEBC.insertGameImage(gameImage);
            } else {
                // GameImage 객체를 수정
                gameImage.setGame_img_no(maxgameImageNo);
                gameImage.setGame_img_name(fileName);
                gameImage.setGame_img_path(filePath);

                // GameImage 객체를 DB에 저장
                gameImageMEBC.updateGameImage(gameImage);
            }
        }

        return "redirect:GameListDatas.do?cnt=9";
    }

	@Override
	@Transactional//단건조회된 게임정보를 삭제
	public String deleteGame(int game_no, HashMap<String, Object> hm) {
		
		String url = null;
		
		logger.info("deleteGame called =====");
		
		GameDTO gamedto = gameMEBC.selectGame(game_no);
		
		if(gamedto == null) {
			hm.put("ERR", "존재하지 않는 회원코드입니다.");
			url = "errPage";
			return url;
			
		}
		
		GameImageDTO gameimagedto = gameImageMEBC.selectGameImage(game_no);
		
		if(gameimagedto != null) {
			
			gameImageMEBC.deleteGameImage(game_no);
			
		}
		
		gameMEBC.deleteGame(game_no);

		
		return "redirect:GameListDatas.do?cnt=9";
		
	}

	@Override
	public String searchGameList(int cnt, int page, String game_name, HashMap<String, Object> hm) {
		
		final int PAGE_SIZE = cnt;
		
		int start = (page - 1) * PAGE_SIZE + 1;
		
		logger.info("GameList called =====");
		
		int count = gameMEBC.getCount();
		
		List<GameDTO> gamelist = gameQEBC.gameListNameSearch(start, PAGE_SIZE, game_name);
		
		List<GameImageDTO> gameimagelist = gameQEBC.gameImageList(start, PAGE_SIZE);
		
		for(int i = 0; i < gameimagelist.size(); i++){
			GameImageDTO  dto1= gameimagelist.get(i);
			System.out.println(dto1.getGame_img_name());
			
			
		}
		
		if(gamelist == null) {
			
			hm.put("MSG", "조회결과가 없습니다.");
			return "main";
		}
		
		int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
		
		final int PAGE_BLOCK = 9;
		int startPage = 1;
		
		if(page % PAGE_BLOCK == 0) {
			startPage = ((int) (page / PAGE_BLOCK) - 1) * PAGE_BLOCK + 1;
		}else {
			startPage = (int) (page / PAGE_BLOCK) * PAGE_BLOCK + 1;
		}
		
		int endPage = startPage + PAGE_BLOCK - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		hm.put("GAME_LIST", gamelist);
		hm.put("GAME_IMAGE_LIST", gameimagelist);
		hm.put("PageCount", pageCount);
		hm.put("StartPage", startPage);
		hm.put("EndPage", endPage);
		hm.put("PAGE", page);
		
		return "main";
		
	}
	
	
	
}
