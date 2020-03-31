package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Guardian;

@Mapper
public interface GuardianMapper {
	
	//보호자 등록
	public int guardianInsert(Guardian guardian);
	
	//보호자 리스트
	public List<Guardian> guardianList();
	
	//보호자 정보수정을 위한 불러오기
	public Guardian guardianSelectForUpdate(String guardianId);
	
	//보호자 정보수정
	public int guardianUpdate(Guardian guardian);
	
	//보호자 삭제
	public int guardianDelte(String guardianId);
	
}
