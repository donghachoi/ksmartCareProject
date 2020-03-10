package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Elder;



@Mapper
public interface VisitMapper {
	//수급자 목록
	public List<Elder> elderAllList(String center_code);
	
	//수급자 제공급여 요약
}
