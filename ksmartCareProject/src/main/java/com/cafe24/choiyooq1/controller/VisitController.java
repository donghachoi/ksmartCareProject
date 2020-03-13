package com.cafe24.choiyooq1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.service.VisitService;


@Controller
public class VisitController {

	@Autowired
	private VisitService visitservice;
	
	//임의에 세션값 
	private String center_code= "3-41590-00001";
	
	//수급자 목록
	@GetMapping("/employee/velderList")
	public String velderList(@RequestParam(value="center_code", required = false) String center_code, Model model) {
		List<Elder> list = visitservice.elderAllList(center_code);
		
		model.addAttribute("list", list);
		return "visit/calInsert";
	}
	
	//수급자 제공급여 요약
	@PostMapping("/employee/velderbenefitcost")
	//@PostMapping(value="/elderbenefitcost", produces = "application/json")
	public @ResponseBody List<Visit> velderBenefitCost(@RequestParam(value="elder_id") String elder_id, 
			@RequestParam(value="syear", required=false, defaultValue="2020") String syear,
			@RequestParam(value="smoth" , required=false, defaultValue="2") String smoth) {

		List<Visit> list = visitservice.elderBenefitCost(elder_id, syear, smoth);

		return list; 
	}
	
	//직원별 카테고리 
	@GetMapping("/employee/vemployeecategory")
	public @ResponseBody List<Employee> vempCategory(@RequestParam(value="empcategory") String empcategory){
		
		List<Employee> list = visitservice.empCategory(center_code, empcategory);
		System.out.println(list);
		return list;
	}
}
