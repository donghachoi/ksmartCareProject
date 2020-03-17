package com.cafe24.choiyooq1.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.BenefitCost;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.service.VisitService;


@Controller
public class VisitController {

	@Autowired
	private VisitService visitservice;
	
	//임의에 세션값 
	private String centerCode= "3-41590-00001";
	
	//수급자 목록
	@GetMapping("/employee/velderList")
	public String velderList(@RequestParam(value="centerCode", required = false) String center_code, Model model) {
		List<Elder> list = visitservice.elderAllList(center_code);
		
		model.addAttribute("list", list);
		return "/visit/calenderInsert";
	}
	
	//수급자 총 금
	@PostMapping("/employee/velderbenefitcost")
	//@PostMapping(value="/elderbenefitcost", produces = "application/json")
	public @ResponseBody Map<String, Integer> velderBenefitCost(@RequestParam(value="elder_id") String elder_id, 
			@RequestParam(value="maxcost") int maxcost,
			@RequestParam(value="syear", required=false) String syear,
			@RequestParam(value="smonth" , required=false) String smonth) {
		
		Calendar today = Calendar.getInstance();
		if(syear == null || smonth == null) {
			syear = Integer.toString(today.get(Calendar.YEAR));
			smonth = Integer.toString(today.get(Calendar.MONTH) + 1);
		}
		
		Map<String, Integer>  list = visitservice.elderBenefitCost(elder_id, syear, smonth, maxcost);
		list.put("syear", Integer.parseInt(syear));
		list.put("smonth", Integer.parseInt(smonth));
		
		return list; 
	}
	
	//수급자 년, 월 별 로 수급 예상 내용 출력
	@PostMapping("/employee/yearSearch")
	public String vyerSearch(@RequestParam(value="elder_id") String elder_id, @RequestParam(value="syear") String syear, 
			@RequestParam(value="smonth") String smonth, Model model){
		
		//List<Visit> list = visitservice.elderBenefitCost(elder_id, syear, smonth);
	
		//model.addAttribute("velderbenefitcost", list);
		return "/visit/calenderInsert";
	}	
	
	
	//직원별 카테고리 
	@GetMapping("/employee/vemployeecategory")
	public @ResponseBody List<Employee> vempCategory(@RequestParam(value="empcategory") String empcategory){
		
		List<Employee> list = visitservice.empCategory(centerCode, empcategory);
		System.out.println(list);
		return list;
	}

	//일정등록 
    @PostMapping(value="/employee/visitInsert", produces = "application/json")
	public String visitInsert(Visit visit){
		System.out.println(visit.toString());
		
		return "/df";
	}
    
    
    //방문일정검색(직원별)
    @GetMapping("/employee/emplyeeCalenderSearch")
    public String vemplyeeCalenderSearch(Model model) {
    	
    	List<Employee> list = visitservice.emplyeeList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "visit/emplyeeCalenderSearch";
    }
    
    //방문일정검색(수급자별)
    @GetMapping("/employee/elderCalenderSearch")
    public String velderCalenderSearch(Model model) {
    	
    	List<Elder> list = visitservice.elderAllList(centerCode);
    	model.addAttribute("list", list);
    	
    	return "visit/elderCalenderSearch";
    }    
   

}
