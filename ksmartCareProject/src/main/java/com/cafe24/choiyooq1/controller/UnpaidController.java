package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnpaidController {

	@GetMapping("center/unpaidList")
	public String getUnpaidList() {
		
		return "unpaid/unpaidList";
	}
}