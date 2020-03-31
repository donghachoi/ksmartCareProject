package com.cafe24.choiyooq1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.service.VisitSearchService;
import com.cafe24.choiyooq1.service.VisitService;


@Controller
public class VisitSearchController {

	@Autowired
	private VisitSearchService visitSerchService;
	
	@Autowired
	private VisitService visitservice;
	
	//임의에 세션값 
	private String centerCode= "3-41590-00001";
	private String canterName ="전주스마트재가센터";
	
    //방문일정검색 직원 리스트 
    @GetMapping("/employee/emplyeeCalenderSearch")
    public String vemplyeeCalenderSearch(Model model) {
    	
    	List<Employee> list = visitservice.emplyeeList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "visit/emplyeeCalenderSearch";
    }
    
    //방문일정검색 수급자 리스트 
    @GetMapping("/employee/elderCalenderSearch")
    public String velderCalenderSearch(Model model) {
    	
    	List<Elder> list = visitservice.elderAllList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "visit/elderCalenderSearch";
    }    
	
	//방문일정 검색 수급자 일정 리스트 
	@PostMapping("/elder/elderCalenderSearch")
	public @ResponseBody List<Visit> elderCalenderSearch(@RequestParam(value="id") String id){
		
		List<Visit> list = visitSerchService.elderCalenderSearch(id);
		return list;
	}
	
	//방문일정 검색 직원 일정 리스트 
	@PostMapping("/employee/employeeCalenderSearch")
	public @ResponseBody List<Visit> employeeCalenderSearch(@RequestParam(value="id") String id){
		
		List<Visit> list = visitSerchService.employeeCalenderSearch(id);
		return list;
	}
	
}
