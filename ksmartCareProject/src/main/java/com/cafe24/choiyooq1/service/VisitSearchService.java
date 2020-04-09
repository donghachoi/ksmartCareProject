package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.VisitMapper;
import com.cafe24.choiyooq1.mapper.VisitSearchMapper;


@Service
@Transactional
public class VisitSearchService {
	
	@Autowired
	private VisitSearchMapper visitSearchMapper;
	
	@Autowired
	private VisitMapper visitMapper;
	
	//방문 일정에서 수급자 일정 검색 
	public List<Visit> elderCalenderSearch(String id){
		
		List<Visit> list = visitSearchMapper.elderCalenderSearch(id);
		
		return list;
	}
	
	//방문 일정에서 직원 일정 검색 
	public List<Visit> employeeCalenderSearch(String id){
		
		List<Visit> list = visitSearchMapper.employeeCalenderSearch(id);
		
		return list;
	}
	
	//수급자 엑셀 출력 
	public List<Visit> excelDown(String id, String syear, String smoth){
		List<Visit> list = visitMapper.elderBenefitCost(id, syear, smoth);
		
		return list;
	}
	
	//직원 엑셀 출력
	public List<Visit> employeeExcelDown(String id, String syear, String smoth){
		List<Visit> list = visitSearchMapper.employeeExcelDown(id, syear, smoth);
		return list;
	}
}
