package com.cafe24.choiyooq1.service;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.mapper.LoginMapper;

@Transactional
@Service
public class LoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	Center center = new Center();
	
	
	
	//날짜 체크 메서트
	public int checkDate(String centerCode) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date date = new Date();
		int today = Integer.parseInt(format1.format(date).replace("-", ""));
		
		center = loginMapper.centerLoginCheck(centerCode);
		//날짜 체크
		int endDate = Integer.parseInt(center.getServiceEndDate().replace("-", ""));
		if(today>endDate) {
			System.out.println("사용기간이 끝났습니다.");
			return 0;
		}
		return 1;
	}
	
	public Center checkLoginCenter(String centerCode) {
		
		center = loginMapper.centerLoginCheck(centerCode);
		return center;
	}
}
