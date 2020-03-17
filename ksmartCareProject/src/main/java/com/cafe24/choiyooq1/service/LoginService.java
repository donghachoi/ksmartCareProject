package com.cafe24.choiyooq1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Center;
import com.cafe24.choiyooq1.mapper.CenterMapper;

@Transactional
@Service
public class LoginService {
	
	@Autowired
	private CenterMapper centerMapper;
	
	List<Center> list = new ArrayList<>();
	public int checkLoginCenter(String centerCode, String centerId, String centerPw) {
		list = centerMapper.centerLoginCheck();
		String alert = "";
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getCenterCode().equals(centerCode));
		}
		return 0;
	}
}
