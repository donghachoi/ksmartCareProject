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
	
	//센터 리스트 가져오기
	public List<Center> getCenterList();
	
	//센터정보 업데이트를 위한 내용 불러오기
	public Center centerSelectForUpdate(String centerCode);
	
	//센터정보 업데이트
	public int centerUpdate(Center center);
	
	//센터정보 삭제
	public int centerDelete(String center);
}
