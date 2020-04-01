package com.cafe24.choiyooq1.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.service.LoginService;


@Controller
public class MainController {
	
	 @Autowired private LoginService loginService;
	 Center center = new Center();
	 Map<String, Object> map = new HashMap<String, Object>();
	 
	 //로그아웃 
	 @GetMapping("/logout")
	 public String login(HttpSession session) {
		 session.invalidate();
		 return "login/firstlogin";
	 }
	 
	 //로그인 확인
	 @PostMapping("/login") 
	 public String login(@RequestParam(value="centerCode")String centerCode
						 ,@RequestParam(value="centerId")String centerId
						 ,@RequestParam(value="centerPw")String centerPw
						 ,HttpServletRequest request
						 ,RedirectAttributes redirectA
						 ,Model model) {
		 
		 map = loginService.checkLoginCenter(centerCode, centerId, centerPw);
		 HttpSession session = request.getSession();
		 
		 
		 String result = (String) map.get("str");
		 center = (Center) map.get("center");
		 if(center!=null) {
			 System.out.println("센터 확인");
			 if(center.getCenterId().equals(centerId)) {
				 System.out.println("아이디 확인");
				 if(center.getCenterPw().equals(centerPw)) {
					 System.out.println("비번 확인");
					 model.addAttribute("SID", center.getCenterId());
					 model.addAttribute("SCENTERNAME", center.getCenterName());
					 model.addAttribute("SMANAGERNAME", center.getCenterManagerName());
					 
				 }else {
					 redirectA.addAttribute("result", "틀린 비밀번호입니다.");	
						return "redirect:/login";
					 }
			 }else {
				 redirectA.addAttribute("result", "등록된 아이디가 아닙니다.");	
					return "redirect:/login";
				 }
			 session.setAttribute("SID", center.getCenterId());
			 session.setAttribute("SCENTERCODE", center.getCenterCode());
			 session.setAttribute("SCENTERNAME", center.getCenterName());
			 session.setAttribute("SMANAGERNAME", center.getCenterManagerName());
			 return "index1"; 

		 }else {
			 redirectA.addAttribute("result", "등록된 센터가아닙니다.");	
			redirectA.addAttribute("result", result);	
			return "redirect:/login";
		 }
		 
	  }

	@GetMapping("/")
	public String index1() {
		return "index1";
	}
	
	
	@GetMapping("/1")
	public String index2() {
		return "index";
	}
	
	@GetMapping("/login")
	public String firstlogin(@RequestParam(value="result", required = false) String result
			, Model model) {
		model.addAttribute("result", result);
		return "login/firstlogin";
	}

}
