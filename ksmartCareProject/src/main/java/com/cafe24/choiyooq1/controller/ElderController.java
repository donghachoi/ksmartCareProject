package com.cafe24.choiyooq1.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.ElderLevelHistory;
import com.cafe24.choiyooq1.domain.ElderRegularCheck;
import com.cafe24.choiyooq1.domain.ElderStatus;
import com.cafe24.choiyooq1.service.BenefitService;
import com.cafe24.choiyooq1.service.ElderService;

@Controller
public class ElderController {
	
	@Autowired BenefitService benefitService;
	@Autowired ElderService elderService; 
	
	/* [검사] 삭제 */
	@PostMapping("/employee/deleteRegularCheck")
	public @ResponseBody void deleteRegularCheck(@RequestBody Map<String,Object> map){
		String elderRegularCheckCode = (String) map.get("code");
		elderService.deleteRegularCheck(elderRegularCheckCode);
	}
	
	/* [검사] 수정 */
	@PostMapping("/employee/updateRegularCheck")
	public @ResponseBody void updateRegularCheck(ElderRegularCheck elderRegularCheck 
												,HttpSession session) {
		
		elderService.updateRegularCheck(elderRegularCheck);
	}
	
	/* [검사] 등록 */
	@PostMapping("/employee/insertRegularCheck")
	public @ResponseBody void insertRegularCheck(@RequestBody List<ElderRegularCheck> list
												,HttpSession session) {
		elderService.insertRegularCheck(list, session);
		
	}
	
	/* [검사] 리스트 */
	@PostMapping("/employee/regularCheck")
	public @ResponseBody List<ElderRegularCheck> regularCheckList(@RequestBody Map<String,Object> map){
		String elderId = (String) map.get("elderId");
		System.out.println(elderService.getOneElderRegularList(elderId).get(0).getElderRegularCheckDoingDate()+"<<<<<<<<<<<<<<<<<<<<<<<<<<\\=============");
		return elderService.getOneElderRegularList(elderId);		
	}
	
	
	/* [계약] 수정 */
	@PostMapping("/employee/statusUpdate")
	public @ResponseBody void updateStatus(ElderStatus elderStatus) {
		System.out.println(elderStatus.toString());
		elderService.updateStatus(elderStatus);
	}
	
	/* [계약] 삭제 */
	@PostMapping("/employee/statusDelete")
	public @ResponseBody void statusDelete(@RequestBody Map<String,Object> map){
		System.out.println("계약관리 삭제!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String code = (String) map.get("code");
		elderService.deleteElderStatus(code);
	}
	
	
	/* [계약] 등록 */
	@PostMapping("/employee/insertStatus")
	public @ResponseBody void insertElderStatus(ElderStatus elderStatus
											,HttpSession session) {
		elderService.insertElderStatus(elderStatus, session);
		
	}
	
	/* [등급] 삭제 */
	@PostMapping("/employee/levelDelete")
	public @ResponseBody void levelDelete(@RequestBody Map<String,Object> map){
		String code = (String) map.get("code");
		elderService.deleteElderLevel(code);
		
	}
	
	/* [등급] 수정 */
	@PostMapping("/employee/levelUpdate")
	public @ResponseBody void levelUpdate(ElderLevelHistory elderLevelHistory){
		elderService.updateElderLevel(elderLevelHistory);
		
	}
	
	/*[등급] 입력 */
	@PostMapping("/employee/levelInsert")
	public @ResponseBody void levelInsert(ElderLevelHistory elderLevelHistory
											,HttpSession session){
		
		elderService.insertElderLevel(elderLevelHistory, session);
	}
	
	/* [수급자] 등록 */
	@PostMapping("/employee/elderInsert")
	public String elderInsert(Elder elder
							,ElderLevelHistory elderLevelHistory
							,HttpSession session) {
		
		elderService.insertElder(elder, elderLevelHistory, session);
		return "elder/elderInsert";
	}
	
	/* [수급자] 아이디 체크 ajax*/
	@PostMapping("/employee/idCheck")
	public @ResponseBody String checkId(@RequestBody Map<String,Object> map){
		String elderId =(String) map.get("Id");
		return elderService.checkElderId(elderId);
	}
	
	/* [수급자] 등록 화면으로 */
	@GetMapping("/employee/elderInsert")
	public String elderInsert(Model model) {
		model.addAttribute("guaranteeingAgency", elderService.getGuaranteeingAgencyList());
		return "elder/elderInsert";
	}
	
	/* [수급자] 리스트 */
	@GetMapping("/employee/elderList")
	public String elderList(Model model) {
		model.addAttribute("elderList", elderService.getElderList());
		return "elder/elderList";
	}
	
	/* [수급자] 상세 리스트 */
	@PostMapping("/elderDetailList")
	public @ResponseBody Map<String,Object> getElderDetailList(@RequestBody Map<String,Object> map){
		String elderId = (String) map.get("elderId");
		return elderService.getOneElderList(elderId);
	}
	
	/* [공통] 수가 리스트 & 한도액 */
	@GetMapping("/benefitcostAndMax")
	public String sugaList(Model model) {
		model.addAttribute("benefitcost", benefitService.getBenefitCost());
		model.addAttribute("benefitMax", benefitService.getBenefitMax());
		
		return "benefit/benefitCostList";
	}
	
}