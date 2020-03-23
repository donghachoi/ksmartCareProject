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
import com.cafe24.choiyooq1.domain.ElderRegularCheck;
import com.cafe24.choiyooq1.domain.ElderStatus;
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

		List<ElderRegularCheck> list = elderMapper.getLastElderRegularHistory(elderId);
		/*
		 * for(int y=0;y<list.size();y++) {
		 * System.out.println(list.get(y).getElderRegularCheckDoingDate()); }
		 */
		for(int i = 0; i< list.size();i++) {
			String category = list.get(i).getElderRegularCheckCategory();
			if(category.equals("낙상위험 측정")) {
				map.put("fallDownCheck", list.get(i));
			}
			if(category.equals("욕창위험 측정")) {
				map.put("bedsoreCheck", list.get(i));
			}if(category.equals("인지기능 검사")) {
				map.put("functionCheck", list.get(i));
			}if(category.equals("욕구사정")) {
				map.put("needsCheck", list.get(i));
			}
			System.out.println(list.get(i).getElderRegularCheckDoingDate());
		}
		map.put("elderOenList", elderMapper.getOneElderList(elderId));
		map.put("elderLastLevel", elderMapper.getElderLastLevelHistory(elderId));
		map.put("elderLastStatus", elderMapper.getElderLastStatus(elderId));
		map.put("elderFirstStatusDate", elderMapper.getElderFirtsStatusDate(elderId));
		map.put("elderLastLevelHistory", elderMapper.getElderLastLevelHistory(elderId));
		return map;
	}
	
	/* 수급자 초기 입력 메서드 */
	public void insertElder(Elder elder,ElderLevelHistory elderLevelHistory ,HttpSession session) {
		ElderRegularCheck elderCheck = new ElderRegularCheck(); 
		String centerName= (String) session.getAttribute("SCENTERNAME");
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		elder.setCenterName(centerName);
		elder.setCenterCode(centerCode);
		elder.setLongTermCareValidity(elderLevelHistory.getElderServiceApprovalEndDate());
		elderMapper.insertElder(elder);
		
		//수급자 초기 체크리스트 입력
		String[] list = {"낙상위험 측정","욕창위험 측정","인지기능 검사","욕구사정" };
		for(int i=0;i<list.length;i++) {
			String checkCode = "check_"+(elderMapper.getMaxNum()+1);
			elderCheck.setElderRegularCheckCode(checkCode);
			elderCheck.setCenterCode(centerCode);
			elderCheck.setCenterName(centerName);
			elderCheck.setElderId(elder.getElderId());
			elderCheck.setElderName(elder.getElderName());
			elderCheck.setElderRegularCheckCategory(list[i]);
			elderCheck.setElderRegularCheckDoingDate("0000-00-00");
			elderMapper.insertRegularCheck(elderCheck);
		}
		
		//수급자 수급상테 초기 등록 
		ElderStatus elderstatus = new ElderStatus();
		String currentCode = "s_current_cd_"+(elderMapper.getElderStatusMaxNum()+1);
		elderstatus.setServiceStatusCode(currentCode);
		elderstatus.setCenterCode(centerCode);
		elderstatus.setCenterName(centerName);
		elderstatus.setElderId(elder.getElderId());
		elderstatus.setElderName(elder.getElderName());
		elderstatus.setServiceStatus("수급");
		elderstatus.setServiceEndDate("0000-00-00");
		elderMapper.insertStatus(elderstatus);
		
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
