package com.cafe24.choiyooq1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.service.EmployeeService;

@Controller
public class EmployeeContorller {
	
	@Autowired
	private EmployeeService employeeService;
	
	// 직원등록 폼 불러오기_센터 담당자가 진행
	@GetMapping("/center/employeeInsert")
	public String employeeInsert() {
		return "/employee/employeeInsert";
	}
	
	// 직원등록 진행_센터 담당자가 진행
	@PostMapping("/center/employeeInsert")
	public String  employeeInsert(Employee employee,
								HttpSession session	) {
		int result = employeeService.employeeInsert(employee,session);
		if(result >0) {
			return "/employee/employeeList";
		}
				
		return "/employee/employeeList";		
	}
	
	
	
	@GetMapping("/center/employeeList")
	
	public String employeeList() {
		return "employee/employeeList";
	}

}
