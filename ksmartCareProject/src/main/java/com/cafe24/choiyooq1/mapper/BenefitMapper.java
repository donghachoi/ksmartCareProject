package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.BenefitCost;
import com.cafe24.choiyooq1.domain.BenefitMax;

@Mapper
public interface BenefitMapper {
	
	/*수가 리스트 가져오는 맵퍼*/
	public List<BenefitCost> getBenefitCost();
	
	/* 수가 한도액 리스트 가져오기 */
	public List<BenefitMax> getBenefitMax();
}