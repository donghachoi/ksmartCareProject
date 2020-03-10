package com.cafe24.choiyooq1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.choiyooq1.mapper.BenefitMapper;

@Controller
public class MainController {
	
	@Autowired BenefitMapper benefitMapper;
	
	@GetMapping("/")
	public String index1() {
		return "index1";
	}
	
	
	@GetMapping("/1")
	public String index2() {
		return "index";
	}
	
	@GetMapping("/login")
	public String firstlogin() {
		return "login/firstlogin";
	}
	
	
	/* 시험용 수가 리스트 */
	@GetMapping("/suga")
	public String sugaList(Model model) {
		model.addAttribute("benefitcost", benefitMapper.getBenefitCost());
		return "suga/sugaList";
	}
}
