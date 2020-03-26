package com.cafe24.choiyooq1.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Guardian;
import com.cafe24.choiyooq1.mapper.GuardianMapper;

@Service
@Transactional
public class GuardianService {
	
	@Autowired GuardianMapper guardianMapper;
	
	//보호자 등록 메서드
	public int guardianInsert(Guardian guardian,
								HttpSession session) {
		String centerCode=(String) session.getAttribute("SCENTERCODE");
		String centerName=(String) session.getAttribute("SCENTERNAME");
		
		guardian.setCenterCode(centerCode);
		guardian.setCenterName(centerName);
		
		return guardianMapper.guardianInsert(guardian);
		
	}
	
}
