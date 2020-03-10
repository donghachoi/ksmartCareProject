package com.cafe24.choiyooq1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.service.VisitService;


@Controller
public class VisitController {

	@Autowired
	private VisitService visitservice;
	
	//수급자 목록
	@GetMapping("/calendar")
	public String visitElderInsert(@RequestParam(value="center_code") String center_code, Model model) {
		List<Elder> list = visitservice.elderAllList(center_code);
		
		model.addAttribute("list", list);
		return "visit/calInsert";
	}
	
	//수급자 제공급여 요약
	@GetMapping("/elderbenefitcost")
	public String elderBenefitCost() {
		
		
		return null;
	}
}
