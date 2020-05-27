package com.cafe24.choiyooq1.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class VisitController {

	@Autowired
	private VisitService visitservice;
	
	//임의에 세션값 
	private String centerCode= "3-41590-00001";
	private String canterName ="전주스마트재가센터";
	
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
	public @ResponseBody Map<String, Object> velderBenefitCost(@RequestParam(value="elder_id") String elder_id, 
			@RequestParam(value="maxcost") int maxcost,
			@RequestParam(value="syear", required=false) String syear,
			@RequestParam(value="smonth" , required=false) String smonth) {
		
		Calendar today = Calendar.getInstance();
		if(syear == null || smonth == null) {
			syear = Integer.toString(today.get(Calendar.YEAR));
			smonth = Integer.toString(today.get(Calendar.MONTH) + 1);
		}
		
		if(smonth.length()>0) {
			smonth ="0"+smonth;
		}
		
		List<Visit> calender = visitservice.vCalenderList(elder_id);
		List<Employee> empList = visitservice.emplyeeList(centerCode);  //센터별 직원 목록 가져옴
		Map<String, Integer>  list = visitservice.elderBenefitCost(elder_id, syear, smonth, maxcost);
		list.put("syear", Integer.parseInt(syear));
		list.put("smonth", Integer.parseInt(smonth));
		
		Map<String, Object> totle =new HashMap<String, Object>();
		totle.put("cost", list);
		totle.put("calender", calender);
		totle.put("empList", empList);
		return totle; 
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

	
	//직원 같은날짜, 시간 중복 체크
	@PostMapping("/employee/emplyeeDayCheck")
	public @ResponseBody int vemplyeeDayCheck(@RequestParam(value="employeeId") String employeeId, 
			@RequestParam(value="visitPlanDate") String visitPlanDate,
			@RequestParam(value="visitPlanTime") String visitPlanTime) {
		   
		int result = visitservice.vemplyeeDayCheck(employeeId, visitPlanDate, visitPlanTime);
		           
		return result;
	}
	
	//일정등록 
    @PostMapping("/employee/visitInsert")
	public @ResponseBody String visitInsert(Visit visit){
		
		visit.setCenterCode(centerCode);
		visit.setCenterName(canterName);
		
		String str = visitservice.visitInsert(visit);
		
		return str;
	}
    
    //일정 업데이트 
    @PostMapping("/employee/visitUpdate")
    public @ResponseBody int visitUpdate(Visit visit) {
    	System.out.println("dfdfsdfsdfsdf"+ visit);
    	int result = visitservice.visitUpdate(visit);
		return result;
    }
    
    //일정 삭제
    @GetMapping("/employee/visitDelete")
    public @ResponseBody int visitDelete(String visitCode) {
    	int result = visitservice.visitDelete(visitCode);
		return result;
    }
    
    //방문코드 검색
    @GetMapping("/employee/getVisitCode")
    public @ResponseBody String getVisitCode(String visitCode) {
    	String getCode = visitservice.getVisitCode(visitCode);
		return getCode;
    }
}
