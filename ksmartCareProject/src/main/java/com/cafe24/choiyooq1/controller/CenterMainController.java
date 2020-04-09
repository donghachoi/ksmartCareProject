package com.cafe24.choiyooq1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.service.CenterMainService;

@Controller
public class CenterMainController {
	
	@Autowired
	private CenterMainService centerMainService;

	@GetMapping("/center/centerInformation")
	public String centerInformation(HttpSession session, Model model) {
				
		String centerCode = (String)session.getAttribute("SCENTERCODE");
		Center centerInformation = centerMainService.centerInformation(centerCode);
		List<Center> elderStatus =  centerMainService.elderStatus(centerCode);
		List<Center> employeeStatus = centerMainService.employeeStatus(centerCode);
		List<Elder> serviceStatus =  centerMainService.serviceStatus(centerCode);
		List<Visit> dailySchedule = centerMainService.dailySchedule(centerCode);
	
		model.addAttribute("centerInformation", centerInformation);
		model.addAttribute("elderStatus", elderStatus);
		model.addAttribute("employeeStatus", employeeStatus);
		model.addAttribute("serviceStatus", serviceStatus);
		model.addAttribute("dailySchedule", dailySchedule);
		return "/center/centerMain";
	}
}
