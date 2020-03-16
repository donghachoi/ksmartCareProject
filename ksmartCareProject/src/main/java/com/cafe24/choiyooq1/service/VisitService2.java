package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.VisitMapper;


public class VisitService2 {

	@Autowired
	private VisitMapper visitMapper;
	
	//수급자 목록 
	public List<Elder> elderAllList(String center_code){
		List<Elder> list = visitMapper.elderAllList(center_code);
		return list;
	}
	
	//수가내용 정리 
	public List<Visit> elderBenefitCost(String elder_id, String syear, String smoth){
		
		
		List<Visit> list = visitMapper.elderBenefitCost(elder_id, syear, smoth);
		return list;
	}
	
	//직원 종류 목록
	public List<Employee> empCategory(String empcategory, String centercode){
		
		List<Employee> list = visitMapper.empCategory(empcategory, centercode);
		return list;
	}
	
	//일정 등록 
	public List<Visit> visitInsert(Visit visit){
		
		visit.setVisitCode("dfsdfsdfsdf");
		return null;
	}
}
