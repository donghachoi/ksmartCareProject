package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeSalaryController {
	
	@GetMapping("/employee/employeeSalary")
	public String employeeSalaryList() {
		return "employeeSalary/employeeSalaryList";
	}
}

