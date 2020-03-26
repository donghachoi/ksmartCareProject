package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Visit;

@Mapper
public interface VisitSearchMapper {

	public List<Visit> elderCalenderSearch(String id);
}
