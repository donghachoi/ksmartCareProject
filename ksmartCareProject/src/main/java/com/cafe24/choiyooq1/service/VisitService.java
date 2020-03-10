package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.mapper.VisitMapper;

@Service
@Transactional 
public class VisitService {

	@Autowired
	private VisitMapper visitMapper;
	
	public List<Elder> elderAllList(String center_code){
		List<Elder> list = visitMapper.elderAllList(center_code);
		return list;
	}
}
