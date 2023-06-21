package com.v12.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AjaxController {

	
	@Autowired
	private IAjaxService ajaxSvc;
	
	
	@RequestMapping(value = "IdChkCtrl.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String idChk(@ModelAttribute("user_id") String user_id) {
		
		
		String idChk = ajaxSvc.idChk(user_id);
		
		
		
		System.out.println(idChk);
		
		return idChk;
	}
	
//	@RequestMapping(value = "gameDeleteChk.do", produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public String gameDeleteChk(@ModelAttribute("game_no") int game_no) {
//
//	    String gameDeleteChk = ajaxSvc.gameDeleteChk(game_no);
//
//	    System.out.println(gameDeleteChk);
//
//	    return gameDeleteChk;
//	}
	
}
