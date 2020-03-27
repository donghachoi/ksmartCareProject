package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Visit;

@Mapper
public interface VisitSearchMapper {

	//방문일정 수급자 일정 검색
	public List<Visit> elderCalenderSearch(String id);
	
	//방문일정 직원 일정검색
	public List<Visit> employeeCalenderSearch(String id);

}
