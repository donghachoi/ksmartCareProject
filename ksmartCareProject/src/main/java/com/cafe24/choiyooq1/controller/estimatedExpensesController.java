package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class estimatedExpensesController {

	
	@GetMapping("/employee/estimatedExpenses")
	public String estimatedExpenses() {
		
		return "estimatedexpenses/estimatedExpenses";
	}
}
