package com.cafe24.choiyooq1.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Center;


@Mapper
public interface CenterMapper {
	
	public int centerInsert(Center center);
}
