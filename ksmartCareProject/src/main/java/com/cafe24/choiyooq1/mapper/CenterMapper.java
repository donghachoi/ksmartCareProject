package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Center;


@Mapper
public interface CenterMapper {
	
	//센터 입력 
	public int centerInsert(Center center);
	
	//센터 로그인 체크
	public List<Center> centerLoginCheck();
}
