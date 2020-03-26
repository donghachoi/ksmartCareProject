package com.cafe24.choiyooq1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 직원 리스트 페이지로 이동
	@GetMapping("/center/employeeList")
	public String employeeList(Model model) {
		model.addAttribute("employeeList", employeeService.getEmployeeList());
		return "/employee/employeeList";
		
	}
	
	// 직원 정보수정을 위한 페이지로 이동
	@GetMapping("/center/employeeUpdate")
	public String employeeUpdate(@RequestParam(value = "employeeId") String employeeId, Model model) {
		model.addAttribute("Employee", employeeService.employeeSelectForUpdate(employeeId));
		return "/employee/employeeUpdate";
		
	}
	
	// 직원 정보수정
	@PostMapping("/center/employeeUpdate")
	public String employeeUpdate(Employee employee) {
		int result = employeeService.employeeUpdate(employee);
		if (result > 0) {
			return "redirect:/center/employeeList";
		}	
		return "redirect:/center/employeeList";
	}
	
	// 직원 삭제
	@GetMapping("/center/employeeDelete")
	public String employeeDelete(@RequestParam(value = "employeeId") String employeeId, Model model) {
		int result = employeeService.employeeDelete(employeeId);
		if (result >0) {
			return "redirect:/center/employeeList";
		}
		
		return "redirect:/center/employeeList";
		
	}
	
	
	


}
