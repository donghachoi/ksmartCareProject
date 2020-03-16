package com.cafe24.choiyooq1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.choiyooq1.service.BenefitService;

@Controller
public class ElderController {
	
	@Autowired BenefitService benefitService;
	
	/* 수급자 등록 */
	@GetMapping("/employee/elderInsert")
	public String elderInsert() {
		return "elder/elderInsert";
	}
	
	/* 수급자 리스트 */
	@GetMapping("/employee/elderList")
	public String elderList() {
		return "elder/elderList";
	}
	
	/* 부트스트랩 확인용 */
	@GetMapping("/table")
	public String table(Model model) {
		model.addAttribute("benefitcost", benefitService.getBenefitCost());
		return "elder/table";
	}
	
	/* 수가 리스트 & 한도액 */
	@GetMapping("/benefitcostAndMax")
	public String sugaList(Model model) {
		System.out.println("hi in controller");
		model.addAttribute("benefitcost", benefitService.getBenefitCost());
		model.addAttribute("benefitMax", benefitService.getBenefitMax());
		
		return "benefit/benefitCostList";
	}
	
	
	
	
}
