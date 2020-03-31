package com.cafe24.choiyooq1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.choiyooq1.domain.Guardian;
import com.cafe24.choiyooq1.service.GuardianService;

@Controller
public class GuardianController {
	
	@Autowired
	private GuardianService guardianService;
	
	/* 보호자 등록 폼 불러오기_센터 담당자가 진행*/
	@GetMapping("/center/guardianInsert")	
	public String guardianInsert() {
		return "/guardian/guardianInsert";
	}
	
	//보호자 등록 메서드
	@PostMapping("/center/guardianInsert")
	public String guardianInsert(Guardian guardian,
								HttpSession session	) {
		int result = guardianService.guardianInsert(guardian, session);
		if(result>0) {
			return "/guardian/guardianInsert";
		}
		return "/guardian/guardianInsert";
	}
	
	//보호자 리스트 페이지로 이동
	@GetMapping("/center/guardianList")	
	public String guardianList(Model model) {
		model.addAttribute("guardianList", guardianService.guardianList());
		return "/guardian/guardianList";
	}
	
	//보호자 정보수정을 위한 페이지로 이동
	@GetMapping("/center/guardianUpdate")
	public String guardianSelectForUpdate(@RequestParam(value = "guardianId") String guardianId, Model model) {
		model.addAttribute("Guardian", guardianService.guardianSelectForUpdate(guardianId));
		return "/guardian/guardianUpdate";
	}
	
	//보호자 정보수정 메서드
	@PostMapping("/center/guardianUpdate")
	public String guardianUpdate(Guardian guardian) {
		int result = guardianService.guardianUpdate(guardian);
		if(result > 0) {
			 return "redirect:/center/guardianList";
		}
		return "redirect:/center/guardianList";
	}
	
	//보호자 삭제 메서드
	@GetMapping("/center/guardianDelete")
	public String guardianDelete(@RequestParam(value = "guardianId") String guardianId, Model model){
		int result = guardianService.guardianDelete(guardianId);
		if(result >0) {
			 return "redirect:/center/guardianList";
		}
		return "redirect:/center/guardianList";
	}
 
		
}
