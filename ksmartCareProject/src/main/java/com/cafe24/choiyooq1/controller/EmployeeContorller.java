package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeContorller {
	
	@GetMapping("/insertemp")
	
	public String insertEmployee() {
		return "employee/insertemployee";
	}
	@GetMapping("/emplist")
	
	public String employeeList() {
		return "employee/employeeList";
	}

}
