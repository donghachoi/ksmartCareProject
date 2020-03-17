package com.cafe24.choiyooq1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.mapper.CenterMapper;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private CenterMapper centerMapper;
	//로그인 쳌
	public int checkLoginCenter(String centerCode, String centerId, String centerPw) {
		centerMapper.checkLoginCenter();
		System.out.println(centerCode);
		System.out.println(centerId);
		System.out.println(centerPw);
		return 1;
	}
}
