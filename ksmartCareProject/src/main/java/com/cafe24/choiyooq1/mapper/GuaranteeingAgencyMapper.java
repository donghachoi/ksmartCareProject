package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GuaranteeingAgencyMapper {
	

	/* 보장기관 리스트가져오기 */
	public List<GuaranteeingAgencyMapper> getGuaranteeingAgencyList();

}
