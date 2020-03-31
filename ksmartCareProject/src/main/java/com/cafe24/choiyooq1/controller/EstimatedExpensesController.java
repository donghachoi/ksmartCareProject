package com.cafe24.choiyooq1.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.EstimatedExpenses;
import com.cafe24.choiyooq1.service.EstimatEdexpensesService;

@Controller
public class EstimatedExpensesController {

	@Autowired
	private EstimatEdexpensesService estimatedexpensesService ;
	//임의에 세션값 
	private String centerCode= "3-41590-00001";
	private String canterName ="전주스마트재가센터";
	
	
	@GetMapping("/elder/estimatedExpensesList")
	public String centerEstimatedExpensesList(String center, Model model) {
		
		Calendar today = Calendar.getInstance();
		
		String	syear = Integer.toString(today.get(Calendar.YEAR));
	    String  smonth = Integer.toString(today.get(Calendar.MONTH) + 1);
		
		
		if(smonth.length()<2) {
			smonth ="0"+smonth;
		}
		
		List<EstimatedExpenses> list = estimatedexpensesService.centerEstimatedExpensesList(centerCode, syear, smonth);
		model.addAttribute("list", list);
		return "/estimatedexpenses/estimatedExpenses";
	}
	
	
	@PostMapping("/elder/estimatedExpensesList")
	public @ResponseBody List<EstimatedExpenses> centerEstimatedExpensesList2(
			@RequestParam(value="syear") String syear, @RequestParam(value="smonth") String smonth){
		
		System.out.println(syear);
		System.out.println(smonth+"111111");

		if(smonth.length()<2) {
			smonth ="0"+smonth;
		}
		System.out.println(smonth+"22222222");

		List<EstimatedExpenses> list = estimatedexpensesService.centerEstimatedExpensesList(centerCode, syear, smonth);
		   System.out.println(list);
		return list;
	}
	
	@PostMapping("/elder/detailestimatedExpenses")
	public @ResponseBody List<EstimatedExpenses> detailEstimatedExpenses(@RequestParam(value="elderId") String elderId, 
			@RequestParam(value="syear", required=false) String syear, @RequestParam(value="smonth", required= false) String smonth){
		
		if(smonth.length()<2) {
			smonth ="0"+smonth;
		}
		List<EstimatedExpenses> list = estimatedexpensesService.detailEstimatedExpenses(elderId, syear, smonth);

//		List<EstimatedExpenses> list = estimatedexpensesService.detailEstimatedExpenses(elderId, syear, smonth);
		
		return list;
		
	}
		
}
