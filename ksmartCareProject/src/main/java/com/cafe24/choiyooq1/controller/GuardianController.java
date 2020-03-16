package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuardianController {
	
	/* 보호자 등록 */
	@GetMapping("/center/guardianInsert")	
	public String guardianInsert() {
		return "/guardian/guardianInsert";
	}
}
