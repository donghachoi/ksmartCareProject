package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.BenefitCost;

@Mapper
public interface BenefitMapper {
	
	/*수가 리스트 가져오는 맵퍼*/
	public List<BenefitCost> getBenefitCost();
}