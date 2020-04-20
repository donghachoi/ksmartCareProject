package com.cafe24.choiyooq1.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
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
	 
	 //메인화면
	 @GetMapping("/main")
	 public String getMainpage() {
		 return "/index";
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
			 if(center.getCenterId().equals(centerId)) {
				 if(center.getCenterPw().equals(centerPw)) {
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
			 SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
			 Date date = new Date();
			 session.setAttribute("SID", center.getCenterId());
			 session.setAttribute("SCENTERCODE", center.getCenterCode());
			 session.setAttribute("SCENTERNAME", center.getCenterName());
			 session.setAttribute("SMANAGERNAME", center.getCenterManagerName());
			 session.setAttribute("today", format1.format(date));
			 System.out.println(session.getAttribute("SID"));
			 System.out.println(session.getAttribute("SCENTERCODE")+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< in controller");
			 if(session.getAttribute("SCENTERCODE").equals("master")) {
				 
				 return "redirect:/master/centerList";
				 
			 }else {
				 return "redirect:/center/centerInformation";
			 }
		 }else {
			 redirectA.addAttribute("result", "등록된 센터가아닙니다.");	
			redirectA.addAttribute("result", result);	
			return "redirect:/login";
		 }
		 
	  }

	@GetMapping("/")
	public String index() {
		
		return "portfolio/portfolio1.html";
	}
	

	@GetMapping("/login")
	public String firstlogin(@RequestParam(value="result", required = false) String result
			, Model model) {
		model.addAttribute("result", result);
		return "login/firstlogin";
	}
}
