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
	
	/* 보호자 리스트 및 검색, 관리 */
	@GetMapping("/center/guardianList")	
	public String guardianList() {
		return "/guardian/guardianList";
	}
}
