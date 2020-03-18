package com.cafe24.choiyooq1.controller;


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
		 
		 center = loginService.checkLoginCenter(centerCode, centerId, centerPw);
		 HttpSession session = request.getSession();
		 
		 if(center!=null) {
			 session.setAttribute("SID", center.getCenterId());
			 session.setAttribute("SCENTERCODE", center.getCenterCode());
			 System.out.println(session.getAttribute("SID"));
			 System.out.println(session.getAttribute("SCENTERCODE"));
			 model.addAttribute("SID", center.getCenterId());
			 model.addAttribute("SCENTERNAME", center.getCenterName());
			 model.addAttribute("SMANAGERNAME", center.getCenterManagerName());
			 return "index1"; 

		 }else {
			 redirectA.addAttribute("result", "등록된 회원이 아닙니다.");	
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
