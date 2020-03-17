package com.cafe24.choiyooq1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class MainController {
	/*
	 * @Autowired private LoginService loginService;
	 * 
	 * //로그인 확인
	 * 
	 * @PostMapping("/login") public String
	 * login(@RequestParam(value="centerCode")String centerCode
	 * ,@RequestParam(value="centerId")String centerId
	 * ,@RequestParam(value="centerPw")String centerPw) {
	 * System.out.println(loginService.checkLoginCenter(centerCode, centerId,
	 * centerPw));
	 * 
	 * 
	 * return "login/firstlogin"; }
	 */
	
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
	
	

}
