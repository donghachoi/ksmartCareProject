package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.BenefitCost;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;



@Mapper
public interface VisitMapper {
	//수급자 목록
	public List<Elder> elderAllList(String centerCode);
	
	//수급자 서비스 내용 
	public List<Visit> elderBenefitCost(String elder_id, String syear, String smoth);
	
	//서비스 종류별 금액 
	public BenefitCost serviceCost(String syear, String scategory, String stype);
	
	//직원별 목록
	public List<Employee> empCategory(String centerCode, String empcategory);
	
	//일정 등록하기
	public List<Visit> visitInsert(Visit visit);

	//직원 목록 
	public List<Employee> emplyeeList(String centerCode);

	
	
}