package com.cafe24.choiyooq1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.service.CenterService;

@Controller
public class CenterController {

	@Autowired
	private CenterService centerService;

	
	@GetMapping("/master/cetnerEenter")
	public String cetnerEenter() {
		return "/mainTest";
	}
	// 센터가입 폼 불러오기
	@GetMapping("/master/centerInsert")
	public String centerInsert() {
		return "center/centerInsert";
	}

	// 센터가입 진행
	@PostMapping("/master/centerInsert")
	public String centerInsert(Center center,Model model) {
		System.out.println(center.toString());
		int result = centerService.centerInsert(center);
		
		if (result > 0) {
			model.addAttribute("centerName", center.getCenterName());
			return "center/centerInsertOk";
		}
		return null;
	}

	// 센터가입 정상 진행시 보여질 페이지로 이동
	@GetMapping("/centerInsertok")
	public String centerInsertok() {
		return "center/centerInsertOk";
	}

	// 센터 리스트
	@GetMapping("/master/centerList")
	public String centerList(Model model) {
		model.addAttribute("centerList", centerService.getCenterList());
		// System.out.println(centerService.getCenterList().toString());
		return "center/centerList";
	}

	// 센터 정보 수정을 위한 불러오기
	@GetMapping("/center/centerUpdate")
	public String centerUpdate(@RequestParam(value = "centerCode") String centerCode, Model model) {
		model.addAttribute("Center", centerService.centerSelectForUpdate(centerCode));
		return "center/centerUpdate";

	}
	
	// 센터 정보수정 처리 
	@PostMapping("/master/centerUpdate")
	public String centerUpdate(Center center) {
		int result = centerService.centerUpdate(center);
		if (result > 0) {
			return "redirect:/master/centerList";
		}
		return "redirect:/master/centerList";
	}

	// 센터 삭제
	@GetMapping("/master/centerDelete")
	public String centerDelete(@RequestParam(value = "centerCode") String centerCode, Model model) {
		model.addAttribute("Center", centerService.centerDelete(centerCode));
		int result = centerService.centerDelete(centerCode);
		if (result > 0) {
			return "redirect:/master/centerList";
		}
		return "redirect:/master/centerList";
	}
	
	/*
	 * @PostMapping("/master/centerDelete") public String centerDelete(String
	 * centerCode) { int result = centerService.centerDelete(centerCode);
	 * if(result>0) { return "redirect:/center/centerList"; }return
	 * "redirect:/center/centerList"; }
	 */

}
