package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Elder;

@Mapper
public interface ElderMapper {
	
	
	/* 수급자 아이디 체크위한 모든 id가져오기 */
	public List<Elder> checkElderId();
	
	/* 수급자 입력메서드 */
	public void insertElder(Elder elder);
}
