package com.cafe24.choiyooq1.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.ElderLevelHistory;
import com.cafe24.choiyooq1.service.BenefitService;
import com.cafe24.choiyooq1.service.ElderService;

@Controller
public class ElderController {
	
	@Autowired BenefitService benefitService;
	@Autowired ElderService elderService; 
	
	
	/* 수급자 등록 */
	@PostMapping("/employee/elderInsert")
	public String elderInsert(Elder elder
							,ElderLevelHistory elderLevelHistory
							,HttpSession session) {
		
		
		
		elderService.insertElder(elder, elderLevelHistory,session);
		return "elder/elderInsert";
	}
	
	/* 수급자 아이디 체크 ajax*/
	@PostMapping("/employee/idCheck")
	public @ResponseBody String checkId(@RequestBody Map<String,Object> map){
		String elderId =(String) map.get("Id");
		return elderService.checkElderId(elderId);
	}
	
	/* 수급자 등록 화면으로 */
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
