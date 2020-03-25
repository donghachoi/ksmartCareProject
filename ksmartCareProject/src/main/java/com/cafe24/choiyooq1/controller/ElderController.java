package com.cafe24.choiyooq1.controller;

import java.util.List;
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
	
	/* 수급자 등급 입력 */
	@PostMapping("/employee/levelInsert")
	public @ResponseBody void levelInsert(ElderLevelHistory elderLevelHistory
											,HttpSession session){
		System.out.println("hi");
		System.out.println(elderLevelHistory.getElderServiceApprovalLevel());
		System.out.println(elderLevelHistory.getElderServiceApprovalNumber());
		System.out.println(elderLevelHistory.getElderServiceApprovalStartDate());
		System.out.println(elderLevelHistory.getElderServiceApprovalEndDate());
		System.out.println(elderLevelHistory.getElderServiceApprovalCategory());
		System.out.println(elderLevelHistory.getElderServiceApprovalCategory2());
		System.out.println(elderLevelHistory.getElderId());
		
		
	}
	
	
	/* 수급자 등록 */
	@PostMapping("/employee/elderInsert")
	public String elderInsert(Elder elder
							,ElderLevelHistory elderLevelHistory
							,HttpSession session) {
		
		//수급자 입력
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
	public String elderInsert(Model model) {
		model.addAttribute("guaranteeingAgency", elderService.getGuaranteeingAgencyList());
		return "elder/elderInsert";
	}
	
	/* 수급자 리스트 */
	@GetMapping("/employee/elderList")
	public String elderList(Model model) {
		model.addAttribute("elderList", elderService.getElderList());
		return "elder/elderList";
	}
	
	/* 수급자 상세 리스트 */
	@PostMapping("/elderDetailList")
	public @ResponseBody Map<String,Object> getElderDetailList(@RequestBody Map<String,Object> map){
		String elderId = (String) map.get("elderId");
		return elderService.getOneElderList(elderId);
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