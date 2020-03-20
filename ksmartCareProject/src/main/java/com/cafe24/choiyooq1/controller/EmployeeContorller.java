package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeContorller {
	
	// 직원 등록_센터 담당자가 진행
	@GetMapping("/center/employeeInsert")
	
	public String insertEmployee() {
		return "/employee/employeeInsert";
	}
	
	
	@GetMapping("/emplist")
	
	public String employeeList() {
		return "employee/employeeList";
	}

}
