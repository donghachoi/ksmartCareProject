package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.CenterMainMapper;


@Service
@Transactional
public class CenterMainService {
	
	@Autowired
	private CenterMainMapper centerMainMapper;
	
	//센터 정보 가져옴
	public Center centerInformation(String centerCode) {
		Center centerInformation= centerMainMapper.centerInformation(centerCode);
		return centerInformation;
	}

	//수급자 현황
	public List<Center> elderStatus(String centerCode) {
		return centerMainMapper.elderStatus(centerCode);
	}

	//요양사 현황
	public List<Center> employeeStatus(String centerCode) {
		return centerMainMapper.employeeStatus(centerCode);
	}
	
	//서비스 형황
	public List<Elder> serviceStatus(String centerCode){
		return centerMainMapper.serviceStatus(centerCode);
	}
	
	//일일 일정
	public List<Visit> dailySchedule(String centerCode){
		return centerMainMapper.dailySchedule(centerCode);
	}
}
