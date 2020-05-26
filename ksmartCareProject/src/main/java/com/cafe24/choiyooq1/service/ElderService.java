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
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	//추후에 로그인할 직원의 세션값 
	private String employeeId = "e_000001";
	private String employeeName = "이형열";
	
	/* [수급자] 삭제 */
	public void elderDelete(String elderId) {
		System.out.println(elderId +"in service");
		elderMapper.deleteElder(elderId);
		
	}
	
	/* [수급자] 검색 */
	public List<Elder> searchElder(String sk, String sv, HttpSession session,
									String elderSearchBeginBirthdate,String elderSearchEndBirthdate) {
		System.out.println(elderSearchBeginBirthdate);
		System.out.println(elderSearchEndBirthdate);
		System.out.println(sk);
		System.out.println(sv);
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		if(elderSearchBeginBirthdate==null) {
			return elderMapper.searchElder(sk, sv, centerCode);
		}else {
			return elderMapper.searchElderByBirth(sk, elderSearchBeginBirthdate, elderSearchEndBirthdate, centerCode);
		}
		
	}
	
	
	/* [검사] 삭제 */
	public void deleteRegularCheck(String elderRegularCheckCode) {
		elderMapper.deleteRegularCheck(elderRegularCheckCode);
		
	}
	
	/* [검사] 수정 */
	public void updateRegularCheck(ElderRegularCheck elderRegularCheck) {
		elderMapper.updateRegularCheck(elderRegularCheck);
	}
	
	/* [검사] 등록 */
	public void insertRegularCheck(List<ElderRegularCheck> list
			,HttpSession session) {
		String centerName= (String) session.getAttribute("SCENTERNAME");
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		for(int i =0;i<list.size();i++) {
			list.get(i).setElderRegularCheckCode("check_"+(elderMapper.getMaxNum()+1));
			list.get(i).setCenterCode(centerCode);
			list.get(i).setCenterName(centerName);
			list.get(i).setEmployeeId(employeeId);
			list.get(i).setEmployeeName(employeeName);
			list.get(i).setElderRegularCheckRegistrationDate("now()");
			elderMapper.insertRegularCheck(list.get(i));
			// ibatis 내에서 NOW()메서드 안먹음......
		}
		
	}
	
	/* [검사] 리스트 */
	public List<ElderRegularCheck> getOneElderRegularList(String elderId){
		List<ElderRegularCheck> list = elderMapper.getOneElderRegularList(elderId);
		for(int i =0;i<list.size();i++) {
			if(list.get(i).getElderRegularCheckPlanDate()==null) {
				list.get(i).setElderRegularCheckPlanDate("");
			}
			if(list.get(i).getElderRegularCheckDoingDate().equals("0000-00-00")) {
				list.get(i).setElderRegularCheckDoingDate("시행전");
			}
			if(list.get(i).getElderRegularCheckRegistrationDate()==null) {
				list.get(i).setElderRegularCheckRegistrationDate("");
			}
		}
		return list;
	}
	
	/* [계약] 수정 */
	public void updateStatus(ElderStatus elderStatus) {
		
		//만약 계약타입이 해지,사망, 타기관 일시 endDate는 null값으로 넘어오니 0000-00-00로 해둡니다.
		String elderStatusType = elderStatus.getServiceStatus();
		if(elderStatusType.equals("해지") || elderStatusType.equals("사망") || elderStatusType.equals("타기관")) {
			elderStatus.setServiceEndDate("0000-00-00");
		}
		elderMapper.updateStatus(elderStatus);
		
	}
	
	/* 수급자 계약관리 삭제 */
	public void deleteElderStatus(String statusCode) {
		elderMapper.deleteElderStatus(statusCode);
	}
	
	/* 수급자 계약 관리 등록 */
	public void insertElderStatus(ElderStatus elderStatus
			,HttpSession session) {
		String centerName= (String) session.getAttribute("SCENTERNAME");
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		int maxNum = elderMapper.getElderStatusMaxNum();
		
		if(maxNum < 9) {
			elderStatus.setServiceStatusCode("s_current_cd_0"+(maxNum+1));
		}else {
			elderStatus.setServiceStatusCode("s_current_cd_"+(maxNum+1));
		}
		elderStatus.setCenterCode(centerCode);
		elderStatus.setCenterName(centerName);
		String status = elderStatus.getServiceStatus();
		if("사망".equals(status) || "해지".equals(status) || "타기관".equals(status)) {
			elderStatus.setServiceEndDate("0000-00-00");
		}
		
		
		elderMapper.insertElderStatus(elderStatus);
	}
	
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
		
		
		//하나의 수급자 최근의 정기검사 
		List<ElderRegularCheck> list = elderMapper.getLastElderRegularHistory(elderId);
		for(int i = 0; i< list.size();i++) {
			String category = list.get(i).getElderRegularCheckCategory();
			if(list.get(i).getElderRegularCheckDoingDate().equals("0000-00-00")) {
				list.get(i).setElderRegularCheckDoingDate("시행전");
			}
			if(category.equals("낙상위험측정")) {
				map.put("fallDownCheck", list.get(i));
			}if(category.equals("욕창위험측정")) {
				map.put("bedsoreCheck", list.get(i));
			}if(category.equals("인지기능검사")) {
				map.put("functionCheck", list.get(i));
			}if(category.equals("욕구사정")) {
				map.put("needsCheck", list.get(i));
			}
		}
		
		// ServiceEndDate 시간이 0000-00-00이면 공백으로 넘깁니다.
		List<ElderStatus> Statuslist = elderMapper.getOneElderStatusList(elderId);
		for(int i=0;i<Statuslist.size();i++) {
			String EndDate = Statuslist.get(i).getServiceEndDate();
			if(EndDate.equals("0000-00-00")) {
				Statuslist.get(i).setServiceEndDate("");
			}
		}
		
		map.put("elderstatusList", Statuslist);
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
		
		ElderRegularCheck elderRegularCheck = new ElderRegularCheck(); 
		String centerName= (String) session.getAttribute("SCENTERNAME");
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		elder.setCenterName(centerName);
		elder.setCenterCode(centerCode);
		elder.setLongTermCareValidity(elderLevelHistory.getElderServiceApprovalEndDate());
		elder.setElderFinalServiceStatus("수급중");
		elderMapper.insertElder(elder);
		
		//수급자 초기 체크리스트 입력
		String[] list = {"낙상위험측정","욕창위험측정","인지기능검사","욕구사정" };
		for(int i=0;i<list.length;i++) {
			elderRegularCheck.setElderRegularCheckCode("check_"+(elderMapper.getMaxNum()+1));
			elderRegularCheck.setCenterCode(centerCode);
			elderRegularCheck.setCenterName(centerName);
			elderRegularCheck.setElderId(elder.getElderId());
			elderRegularCheck.setElderName(elder.getElderName());
			elderRegularCheck.setElderRegularCheckCategory(list[i]);
			elderRegularCheck.setElderRegularCheckDoingDate("0000-00-00");
			elderMapper.insertFirstRegularCheck(elderRegularCheck);
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
		elderMapper.insertFirstStatus(elderstatus);
		
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
	public List<Elder> getElderList(HttpSession session){
		String centerCode= (String) session.getAttribute("SCENTERCODE");
		List<Elder> list = elderMapper.getElderList(centerCode);
		return list;
	}
	
	/* 보장기관 리스트 가져오기 */
	public List<GuaranteeingAgencyMapper> getGuaranteeingAgencyList(){
		List<GuaranteeingAgencyMapper> list = guaranteeingAgency.getGuaranteeingAgencyList();
		
		return list;
	}
	
	
	/* 수급자 아이디 체크 */
	public String checkElderId(String elderId) {
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
