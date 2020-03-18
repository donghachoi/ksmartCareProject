package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.mapper.CenterMapper;

@Service
@Transactional
public class CenterService {
	@Autowired CenterMapper centerMapper;
	
	//센터 입력
	public int centerInsert(Center center) {
		return centerMapper.centerInsert(center);
		
	}
	//센터 리스트
	public List<Center> getCenterList(){
		return centerMapper.getCenterList();
		
	}
	
	

}
