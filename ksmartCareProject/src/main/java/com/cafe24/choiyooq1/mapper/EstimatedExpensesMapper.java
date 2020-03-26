package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Visit;

@Mapper
public interface EstimatedExpensesMapper {

	//예상비용 청구 리스트
	public List<Visit> EstimatedexpensesList(String ederId, String data) ;
	
	//수급자 최대 한도 
	public int elderMaxCost(String elerId);
	
	//센터 수급자 예상 청구 목록
	public List<Elder> centerElderList(String centerCode);
	
	//수급자 서비스 내용 
	public List<Visit> visitServiceList(String elderId, String syear, String smonth);
}
