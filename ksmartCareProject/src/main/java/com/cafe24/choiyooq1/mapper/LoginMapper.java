package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Center;

@Mapper
public interface LoginMapper {
	
	public Center centerLoginCheck(String centerCode);
	
}
