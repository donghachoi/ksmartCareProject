package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;



@Mapper
public interface VisitMapper {
	//수급자 목록
	public List<Elder> elderAllList(String center_code);
	
	//수급자 제공급여 요약
	public List<Visit> elderBenefitCost(String elder_id, String syear, String smoth);
	
	//직원별 목록
	public List<Employee> empCategory(String center_code, String empcategory);
	
	//일정 등록하기
	public List<Visit> visitInsert(Visit visit);
	
}