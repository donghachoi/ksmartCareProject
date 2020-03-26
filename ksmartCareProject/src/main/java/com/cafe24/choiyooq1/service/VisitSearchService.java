package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.VisitSearchMapper;

@Service
@Transactional
public class VisitSearchService {
	
	@Autowired
	private VisitSearchMapper visitSearchMapper;
	
	//수급자 서비스 내용 
	public List<Visit> elderCalenderSearch(String id){
		
		List<Visit> list = visitSearchMapper.elderCalenderSearch(id);
		
		return list;
	}

}
