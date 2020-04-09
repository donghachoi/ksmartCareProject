package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Visit;

@Mapper
public interface CenterMainMapper {
	
	//센터 정보 
	public Center centerInformation(String centerCode);
	
	//수급자 현황
	public List<Center> elderStatus(String centerCode);
	
	//요양사 현황
	public List<Center> employeeStatus(String centerCode);
	
	//서비스 형황
	public List<Elder> serviceStatus(String centerCode);
	
	//일일 일정
	public List<Visit> dailySchedule(String centerCode);

	
	
}
