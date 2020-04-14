package com.cafe24.choiyooq1.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	
	
	
	/**
	 * 유효기간 확인 메서드
	 * @param centerCode
	 * @return boolean
	 * 
	 */
	public boolean checkDate(String centerCode) {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		Date date = new Date();
		int today = Integer.parseInt(format1.format(date).replace("-", ""));
		
		center = loginMapper.centerLoginCheck(centerCode);
		if(center.getServiceEndDate()!=null) {
			int endDate = Integer.parseInt(center.getServiceEndDate().replace("-", ""));
			if(today>=endDate) {
				System.out.println("사용기간이 끝났습니다.");
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 로그인 체크 메서드
	 * @param centerCode
	 * @param centerId
	 * @param centerPw
	 * @return
	 */
	public Map<String, Object> checkLoginCenter(String centerCode, String centerId, String centerPw) {
		center = loginMapper.centerLoginCheck(centerCode);
		Map<String, Object> map = new HashMap<String, Object>();
		//센터 코드,아이디,비번 체크
		if(center!=null) {
			System.out.println("센터코드 일치");
				if(centerId.equals(center.getCenterId())) {
					System.out.println("센터 아이디일치");
					if(centerPw.equals(center.getCenterPw())) {
						System.out.println("센터 비번일치 ");
						if(checkDate(centerCode)) {
							System.out.println("센터 유효기간 유효");
							map.put("center", center);
						}else {
							System.out.println("유효기간 을 등록이나 연장해주세요.");
							map.put("str", "유효기간 을 등록이나 연장해주세요.");
						}
					}else {
						System.out.println("센터 비번 불 일치");
						map.put("str", "센터 비번 불 일치");
					}
				}else {
					System.out.println("센터아이디 불 일치");
					map.put("str", "센터 아이디 불 일치");
				}
			}else {
				System.out.println("없는 센터코드");
				map.put("str", "없는 센터코드");
		}
		return map;
	}
}
