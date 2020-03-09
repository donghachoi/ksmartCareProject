package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	@GetMapping("/")
	public String index1() {
		return "index1";
	}
	@GetMapping("/1")
	public String index2() {
		return "index";
	}
	
}
