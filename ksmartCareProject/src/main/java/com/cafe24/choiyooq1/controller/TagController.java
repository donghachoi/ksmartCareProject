package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TagController {
	@GetMapping("/center/tagList")
	public String tagList() {
		return "tag/tagList";
	}
	@GetMapping("/center/tagCompareList")
	public String tagCompareList() {
		return "tag/tagCompareList";
	}
}
