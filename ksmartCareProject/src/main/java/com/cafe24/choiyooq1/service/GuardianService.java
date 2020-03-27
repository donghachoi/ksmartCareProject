package com.cafe24.choiyooq1.service;

import java.util.List;

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
	
	//보호자 리스트 불러오기
	public List<Guardian> guardianList() {
		return guardianMapper.guardianList();		
	}
	
	//보호자 정보수정을 위한 페이지로 이동
	public Guardian guardianSelectForUpdate(String  guardianId) {
		return guardianMapper.guardianSelectForUpdate(guardianId);
	}
	
	//보호자 정보수정 메서드
	public int guardianUpdate(Guardian guardian) {
		return guardianMapper.guardianUpdate(guardian);
		
	}
	
	//보호자 삭제 메서드
	public int guardianDelete(String guardianId) {
		return guardianMapper.guardianDelte(guardianId);
		
	}
	
	
}
