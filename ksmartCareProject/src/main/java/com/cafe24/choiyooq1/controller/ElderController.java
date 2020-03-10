package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ElderController {
	
	
	
	
	/* 수급자 리스트 */
	@GetMapping("/elderList")
	public String getdelderList() {
		
		return "elder/elderList";
	}
}
