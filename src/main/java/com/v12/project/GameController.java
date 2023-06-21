package com.v12.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GameController {

	@Autowired
	private IGameService gameSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(GameController.class);


	@RequestMapping("GameListDatas.do")//게임리스트를 등록순으로 다건조회(게임이미지 포함)
	public String gameList(@RequestParam(value="cnt", required=false, defaultValue="9") int cnt, String page, Model model) {
		logger.info("GameListDatas.do called =====");

	
		int	nPage = 1;
		if(page=="") {
			
			page="1";
					
		}else if(page== null) {
			page="1";
		}
		
		nPage = Integer.parseInt(page);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.gameList(cnt, nPage, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("GameListNoDatas.do")//게임리스트를 번호순으로 다건조회(게임이미지 포함)
	public String gameListNo(int cnt ,String page, Model model) {
		logger.info("GameListNoDatas.do called =====");

	
		int	nPage = 1;
		if(page=="") {
			
			page="1";
					
		}else if(page== null) {
			page="1";
		}
		
		nPage = Integer.parseInt(page);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.gameListNo(cnt, nPage, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("GameListNameDatas.do")//게임리스트를 이름순으로 다건조회(게임이미지 포함)
	public String gameListName(int cnt, String page, Model model) {
		logger.info("GameListNameDatas.do called =====");
	
		int	nPage = 1;
		if(page=="") {
			
			page="1";
					
		}else if(page== null) {
			page="1";
		}
		
		nPage = Integer.parseInt(page);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.gameListName(cnt, nPage, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("searchGameListDatas.do")//게임리스트를 이름순으로 다건조회(게임이미지 포함)
	public String searchGameList(int cnt, String page, String game_name, Model model) {
		logger.info("searchGameListDatas.do called =====");
	
		int	nPage = 1;
		if(page=="") {
			
			page="1";
					
		}else if(page== null) {
			page="1";
		}
		
		nPage = Integer.parseInt(page);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.searchGameList(cnt, nPage, game_name, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("getGameInfo.do")//게임정보를 단건조회
	public String getGameInfo(@ModelAttribute("game_No") String game_No, 
			@ModelAttribute("page") String page , Model model) {
		logger.info("getGameInfo.do called =====");
		
		int game_no = 0;
		game_no = Integer.parseInt(game_No);
		

		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.getGameInfo(game_no,  page, hm);
		
		model.addAttribute("DTO", hm.get("DTO"));
		model.addAttribute("GAMEIMAGE", hm.get("GAMEIMAGE"));
		model.addAttribute("PAGE", hm.get("PAGE"));
		return url;
	}
	
	@RequestMapping("updategetGameInfo.do")//수정할 게임정보를 단건조회
	public String updategetGameInfo(@ModelAttribute("game_No") String game_No, 
			@ModelAttribute("page") String page , Model model) {
		logger.info("updategetGameInfo.do called =====");
		
		int game_no = 0;
		game_no = Integer.parseInt(game_No);

		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.updategetGameInfo(game_no,  page, hm);
		
		model.addAttribute("DTO", hm.get("DTO"));
		model.addAttribute("GAMEIMAGE", hm.get("GAMEIMAGE"));
		model.addAttribute("PAGE", hm.get("PAGE"));
		return url;
	}
	
	@RequestMapping("insertGameFrom")//게임정보를 입력하는 페이지로 이동(가장 큰 게임번호 + 1)
	public String insertGameFrom(Model model) {
	    logger.info("insertGameFrom called =====");

	    int gamemaxno = gameSvc.getGameMaxNo();
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    hm.put("GAMEMAXNO", gamemaxno);

	    model.addAllAttributes(hm);

	    return "insertGame";
	}

	@RequestMapping("insertGame.do")//게임정보를 입력(가장 큰 게임번호 + 1)
	public String insertGame(GameDTO gamedto, MultipartFile image, Model model) throws IOException {
	    logger.info("insertGame.do called =====");

	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    String url = gameSvc.insertGame(gamedto, image, hm);

	    model.addAllAttributes(hm);

	    return url;
	}
	
	@RequestMapping("updateGame.do")//단건조회된 게임정보를 수정
	public String updateGame(GameDTO gamedto,  MultipartFile image, Model model) throws IOException{
		logger.info("updateGame.do called =====");
	
		HashMap<String, Object> hm = new HashMap<String, Object>();
				
		String url = gameSvc.updateGame(gamedto, image, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
	@RequestMapping("deleteGame.do")//단건조회된 게임정보를 삭제
	public String deleteGame(@ModelAttribute("game_No") String game_No,  Model model) {
		
		int game_no = 0;
		game_no = Integer.parseInt(game_No);
		

		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		String url = gameSvc.deleteGame(game_no, hm);
		
		model.addAllAttributes(hm);
		
		return url;
	}
	
}
