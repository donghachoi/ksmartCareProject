package com.cafe24.choiyooq1.service;


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
	public Center checkLoginCenter(String centerCode, String centerId, String centerPw) {
		center = loginMapper.centerLoginCheck(centerCode);
		if(center!=null) {
			System.out.println("센터코드 일치");
				if(centerId.equals(center.getCenterId())) {
					System.out.println("센터 아이디일치");
					if(centerPw.equals(center.getCenterPw())) {
						System.out.println("센터 비번일치 로그인 성공");
						return center;
						
					}else {
						System.out.println("센터비번 불 일치");
						return null;
					}
				}else {
					System.out.println("센터아이디 불 일치");
					return null;
				}
			}else {
				System.out.println("없는 센터코드");
				return null;
				
		}
		
	}
}
