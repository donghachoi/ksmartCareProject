package com.cafe24.choiyooq1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.choiyooq1.service.TagService;

@Controller

public class TagController {
	
	@Autowired TagService tagService;
	
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
}
