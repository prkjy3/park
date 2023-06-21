package com.v12.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NoticeController {
	
	
	@Autowired
	INoticeService noticeSvc;
	
	@RequestMapping(value="noticeList" ,produces = "text/html; charset=utf-8")
	public String noticeList(@ModelAttribute("game_no") int game_no ,
				@ModelAttribute("game_name") String game_name ,
				@RequestParam(value="cnt" ,required=false,defaultValue= "10" ) int cnt,
				@RequestParam(value="page" ,required=false,defaultValue="1" )int page,
				Model model
			) {
		
		System.out.println("게임이름은 " + game_name);
		HashMap<String, Object> hm = new HashMap<String,Object>();
		try {
		noticeSvc.noticeList(game_name, game_no, cnt, page, hm);
				
		}catch(Exception e) {
			
		}
		
		model.addAttribute("game_name",game_name);
		model.addAllAttributes(hm);
		return "noticeList";
		}
	@RequestMapping(value="getNotice")
	public String getNotice(@ModelAttribute("game_no") int game_no,
			@ModelAttribute("game_name") String game_name ,
			@ModelAttribute("notice_no") int notice_no,
			@RequestParam(value="cnt" ,required=false,defaultValue= "10" ) int cnt,
			@RequestParam(value="page" ,required=false,defaultValue="1" )int page
			,Model model)throws Exception {
		String url ="";
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
			
			
		 url = noticeSvc.getNotice(game_no, notice_no, page, cnt, hm);
		
		
		
			
		
			model.addAttribute("game_name",game_name);
		model.addAllAttributes(hm);
		
		return url;
	}
	@RequestMapping("insertNoticeForm")
	public String insertNotice(
			@ModelAttribute("game_name") String game_name ,
			@ModelAttribute("game_no") int game_no ,
			Model model) {
		
		model.addAttribute("game_no",game_no);
		model.addAttribute("game_name",game_name);
		
		return "insertNotice";
	}
	
	
	@RequestMapping(method = RequestMethod.POST ,value="insertNotice.do")
	public String insertNoticeDo(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO,
            @RequestParam("images") MultipartFile image
            ,Model model) throws IOException {
        
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
	    	String url = noticeSvc.insertNotice(noticeDTO, image,hm);
	    	
	    	model.addAllAttributes(hm);
	    	
	    	return url;
		
    }
	@RequestMapping("deleteNotice.do")
	public String deleteNoticeDo(Model model,
			@ModelAttribute("notice_no") int notice_no,
			@ModelAttribute("game_no") int game_no,
			@RequestParam(value="page" ,required=false,defaultValue="1" )String page
			
			)throws Exception {
		HashMap<String,Object> hm = new HashMap<String, Object> ();
		String url = "";
		
		
		 url = noticeSvc.deleteNotice(game_no, notice_no, page, hm);
		 
		 model.addAttribute(hm);
		 
		 
		return url;
	}
	@RequestMapping("updateNoticeForm")
	public String updateNoticeForm(String game_name, int 
			game_no, int notice_no,
			@RequestParam(value="page" ,required=false,defaultValue="1" )String page
			,Model model
			)throws Exception {
		HashMap<String,Object> hm = new HashMap<String, Object> ();
		String url = "";
		
		 url = noticeSvc.updateGetNotice(game_no, page, notice_no, hm);
		
		 
		
		model.addAllAttributes(hm);

		model.addAttribute("game_name",game_name); 
		
		return url;
	}
	@RequestMapping(method = RequestMethod.POST ,value="updateNotice.do" )
	public String updateNoticeDo(String game_name, 
			NoticeDTO dto,
			@RequestParam(value="page" ,required=false,defaultValue="1" )String page,
			@RequestParam("images") MultipartFile image,
			Model model )throws Exception {
		HashMap<String,Object> hm = new HashMap<String, Object> ();
		String url = "";
		
		 url = noticeSvc.updateNotice(dto, page,image, hm);		
		 
		model.addAttribute(hm);

		model.addAttribute("game_name",game_name); 
		
		return url;
	}	
		
}
