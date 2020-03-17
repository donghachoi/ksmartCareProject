package com.cafe24.choiyooq1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.service.CenterService;

@Controller
public class CenterController {
	
	@Autowired 
	private CenterService centerService;
	
	//센터가입 폼 불러오기
	@GetMapping("/center/centerInsert")	
	public String centerInsert() {
		return "center/centerInsert";
	}
	//센터가입 진행
	@PostMapping("/center/centerInsert")
	public String centerInsert(Center center) {
		System.out.println(center.toString());
		int result = centerService.centerInsert(center);
		 if(result >0) {
			 return "/center/centerInsertOk";
		 }
		return null;
	}
	@GetMapping("/centerInsertok")	
	public String centerInsertok() {
		return "center/centerInsertOk";
	}
	
	@GetMapping("/centerlist")
	public String centerList() {
		return "center/centerList";
	}

}
