package com.cafe24.choiyooq1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.service.TagService;
import com.cafe24.choiyooq1.service.VisitService;

@Controller

public class TagController {
	
	@Autowired TagService tagService;
	
	@Autowired
	private VisitService visitservice;
	
	//태그리스트  불러오기
	@GetMapping("/center/tagList")
	public String tagList(Model model) {
		model.addAttribute("tagList", tagService.getTagList());
		return "/tag/tagList";
	}
	
	@GetMapping("/center/tagCompareList")
	public String tagCompareList() {
		return "tag/tagCompareList";
	}
	
	@GetMapping("/center/tagelderList")
	public String tagelderList(HttpSession session,Model model) {
		String centerCode = (String)session.getAttribute("SCENTERCODE");
    	List<Elder> list = visitservice.elderAllList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "/tag/tagExpenses";		
	}
	
}
