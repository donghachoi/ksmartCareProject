package com.cafe24.choiyooq1.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	/* 수급자 등급 및 인정기간 삭제 */
	public void deleteElderLevel(String levelCode) {
		elderMapper.deleteElderLevel(levelCode);
	}
	
	
	/* 수급자 등급 및 인정 기간 수정 */
	public void updateElderLevel(ElderLevelHistory elderLevelHistory) {
		elderMapper.updateElderLevel(elderLevelHistory);
		
	}
	
	/* 수급자 등급 및 인정 기간 입력 메서드 */
	public void insertElderLevel(ElderLevelHistory elderLevelHistory ,HttpSession session) {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		Date date = new Date();
		String today = format1.format(date);
		System.out.println(today);
		elderLevelHistory.setElderLevelHistoryCode("s_level_history_"+(elderMapper.getElderLevelMaxNum()+1));
		String centerName= (String) session.getAttribute("SCENTERNAME");
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		elderLevelHistory.setCenterCode(centerCode);
		elderLevelHistory.setCenterName(centerName);
		if(elderLevelHistory.getElderSercviceApplyDate() == null) {
			elderLevelHistory.setElderSercviceApplyDate(today);
		}
		elderMapper.insertElderLevel(elderLevelHistory);
	}
	
	/* 수급자 상세리스트 메서드 */
	public Map<String,Object> getOneElderList(String elderId){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<ElderRegularCheck> list = elderMapper.getLastElderRegularHistory(elderId);
		for(int i = 0; i< list.size();i++) {
			String category = list.get(i).getElderRegularCheckCategory();
			if(list.get(i).getElderRegularCheckDoingDate().equals("0000-00-00")) {
				list.get(i).setElderRegularCheckDoingDate("시행전");
			}
			if(category.equals("낙상위험 측정")) {
				map.put("fallDownCheck", list.get(i));
			}if(category.equals("욕창위험 측정")) {
				map.put("bedsoreCheck", list.get(i));
			}if(category.equals("인지기능 검사")) {
				map.put("functionCheck", list.get(i));
			}if(category.equals("욕구사정")) {
				map.put("needsCheck", list.get(i));
			}
			
			System.out.println(list.get(i).getElderRegularCheckDoingDate());
		}
		
		map.put("elderstatusList", elderMapper.getOneElderStatusList(elderId));
		map.put("elderOenList", elderMapper.getOneElderList(elderId));
		map.put("elderLastLevel", elderMapper.getElderLastLevelHistory(elderId));
		map.put("elderLastStatus", elderMapper.getElderLastStatus(elderId));
		map.put("elderFirstStatusDate", elderMapper.getElderFirtsStatusDate(elderId));
		map.put("elderLastLevelHistory", elderMapper.getElderLastLevelHistory(elderId));
		map.put("elderOneLevelHistory", elderMapper.getOneElderLevelHistoryList(elderId));
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
		elder.setElderFinalServiceStatus("수급중");
		elderMapper.insertElder(elder);
		
		//수급자 초기 체크리스트 입력
		String[] list = {"낙상위험 측정","욕창위험 측정","인지기능 검사","욕구사정" };
		for(int i=0;i<list.length;i++) {
			elderCheck.setElderRegularCheckCode("check_"+(elderMapper.getMaxNum()+1));
			elderCheck.setCenterCode(centerCode);
			elderCheck.setCenterName(centerName);
			elderCheck.setElderId(elder.getElderId());
			elderCheck.setElderName(elder.getElderName());
			elderCheck.setElderRegularCheckCategory(list[i]);
			elderCheck.setElderRegularCheckDoingDate("0000-00-00");
			elderMapper.insertRegularCheck(elderCheck);
		}
		
		//수급자 수급상태 초기 등록 
		ElderStatus elderstatus = new ElderStatus();
		elderstatus.setServiceStatusCode("s_current_cd_"+(elderMapper.getElderStatusMaxNum()+1));
		elderstatus.setCenterCode(centerCode);
		elderstatus.setCenterName(centerName);
		elderstatus.setElderId(elder.getElderId());
		elderstatus.setElderName(elder.getElderName());
		elderstatus.setServiceStatus("수급");
		elderstatus.setServiceEndDate("0000-00-00");
		elderMapper.insertStatus(elderstatus);
		
		//수급자 등급 상태 초기등록
		System.out.println(elderMapper.getElderLevelMaxNum());
		elderLevelHistory.setElderLevelHistoryCode("s_level_history_"+(elderMapper.getElderLevelMaxNum()+1));
		elderLevelHistory.setElderId(elder.getElderId());
		elderLevelHistory.setElderName(elder.getElderName());
		elderLevelHistory.setCenterCode(centerCode);
		elderLevelHistory.setCenterName(centerName);
		elderLevelHistory.setElderServiceApprovalLevel(elder.getElderFinalServiceApprovalLevel());
		elderLevelHistory.setElderServiceApprovalCategory("3최초처리완료(결과완료)");
		elderLevelHistory.setElderServiceApprovalCategory("3_1최초등급승인");
		elderLevelHistory.setElderServiceApprovalNumber(elder.getLongTermCareNumver());
		elderMapper.insertFirstLevel(elderLevelHistory);
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
