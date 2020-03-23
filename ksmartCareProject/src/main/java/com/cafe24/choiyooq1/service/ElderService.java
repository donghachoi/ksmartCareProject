package com.cafe24.choiyooq1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.ElderLevelHistory;
import com.cafe24.choiyooq1.mapper.ElderMapper;
import com.cafe24.choiyooq1.mapper.GuaranteeingAgencyMapper;

@Transactional
@Service
public class ElderService {
	
	@Autowired ElderMapper elderMapper;
	@Autowired GuaranteeingAgencyMapper guaranteeingAgency;
	
	/* 수급자 상세리스트 메서드 */
	public Map<String,Object> getOneElderList(String elderId){
		Map<String,Object> map = new HashMap<String,Object>();
		if(elderMapper.getElderLastStatus(elderId).getServiceEndDate()==null) {
			elderMapper.getElderLastStatus(elderId).setServiceEndDate("1");
		}
		
		elderMapper.getLastElderLevelHistory(elderId).toString();
		
		map.put("elderOenList", elderMapper.getOneElderList(elderId));
		map.put("elderLastLevel", elderMapper.getElderLastLevelHistory(elderId));
		map.put("elderLastStatus", elderMapper.getElderLastStatus(elderId));
		map.put("elderFirstStatusDate", elderMapper.getElderFirtsStatusDate(elderId));
		map.put("elderLastLevelHistory", elderMapper.getElderLastLevelHistory(elderId));
		return map;
	}
	
	/* 수급자 입력 메서드 */
	public void insertElder(Elder elder,ElderLevelHistory elderLevelHistory ,HttpSession session) {
		String centerName= (String) session.getAttribute("SCENTERNAME");
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		System.out.println(centerCode);
		System.out.println(centerName);
		elder.setCenterName(centerName);
		elder.setCenterCode(centerCode);
		elder.setLongTermCareValidity(elderLevelHistory.getElderServiceApprovalEndDate());
		elderMapper.insertElder(elder);
		
	}
	
	
	/* 수급자 리스트 메서드 */
	public List<Elder> getElderList(){
		List<Elder> list = elderMapper.getElderList();
		return list;
	}
	
	/* 보장기관 리스트 가져오기 */
	public List<GuaranteeingAgencyMapper> getGuaranteeingAgencyList(){
		List<GuaranteeingAgencyMapper> list = guaranteeingAgency.getGuaranteeingAgencyList();
		
		return list;
	}
	
	
	/* 수급자 아이디 체크 */
	public String checkElderId(String elderId) {
		System.out.println(elderId);
		String result = "아이디 사용가능합니다";
		List<Elder> list = elderMapper.checkElderId();
		for(int i=0;i<list.size();i++) {
			if(elderId.equals(list.get(i).getElderId())) {
				System.out.println("아이디중복.");
				result = "아이디 중복";
			}
		}
		
		System.out.println(result);
		return result;
	}
}
