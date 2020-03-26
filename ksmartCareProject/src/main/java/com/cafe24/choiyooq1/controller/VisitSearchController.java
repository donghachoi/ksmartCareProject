package com.cafe24.choiyooq1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.service.VisitSearchService;


@Controller
public class VisitSearchController {

	@Autowired
	private VisitSearchService visitSerchService;
	
	@PostMapping("/elder/elderCalenderSearch")
	public @ResponseBody List<Visit> elderCalenderSearch(@RequestParam(value="id") String id){
		
		List<Visit> list = visitSerchService.elderCalenderSearch(id);
		return list;
	}
}
