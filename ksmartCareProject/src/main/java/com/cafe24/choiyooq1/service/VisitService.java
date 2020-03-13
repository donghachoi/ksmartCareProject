package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.VisitMapper;

@Service
@Transactional 
public class VisitService {

	@Autowired
	private VisitMapper visitMapper;
	
	public List<Elder> elderAllList(String center_code){
		List<Elder> list = visitMapper.elderAllList(center_code);
		return list;
	}
	
	public List<Visit> elderBenefitCost(String elder_id, String syear, String smoth){
		
		List<Visit> list = visitMapper.elderBenefitCost(elder_id, syear, smoth);
		return list;
	}
	
	public List<Employee> empCategory(String empcategory, String centercode){
		
		List<Employee> list = visitMapper.empCategory(empcategory, centercode);
		return list;
	}
}
