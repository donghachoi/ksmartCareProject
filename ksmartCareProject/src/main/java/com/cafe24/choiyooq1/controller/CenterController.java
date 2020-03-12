package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CenterController {
	
	@GetMapping("/insertcenter")	
	public String insertEmployee() {
		return "center/insertCenter";
	}
	
	@GetMapping("/centerlist")
	public String centerList() {
		return "center/centerList";
	}

}
