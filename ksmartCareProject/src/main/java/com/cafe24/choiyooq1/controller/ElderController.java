package com.cafe24.choiyooq1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.choiyooq1.mapper.BenefitMapper;

@Controller
public class ElderController {
	
	@Autowired BenefitMapper benefitMapper;
	
	/* 수급자 등록 */
	@GetMapping("/elderInsert")
	public String elderInsert() {
		return "elder/elderInsert";
	}
	
	/* 수급자 리스트 */
	@GetMapping("/elderList")
	public String getEelderList(Model model) {
		return "elder/elderList";
	}
}
