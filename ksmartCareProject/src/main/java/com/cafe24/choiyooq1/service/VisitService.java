package com.cafe24.choiyooq1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.BenefitCost;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.VisitMapper;

@Service
@Transactional 
public class VisitService {

	@Autowired
	private VisitMapper visitMapper;
	
	private int elderMaxCost =0;
	private int subCost = 0;
	
	//수급자 목록 
	public List<Elder> elderAllList(String centerCode){
		List<Elder> list = visitMapper.elderAllList(centerCode);
		return list;
	}
	
	//수가 총 내용및 잔액 금액  
	public Map<String, Integer> elderBenefitCost(String elder_id, String syear, String smoth, int maxcost){
	//public List<BenefitCost> elderBenefitCost(String elder_id, String syear, String smoth){
		
		List<Visit> list = visitMapper.elderBenefitCost(elder_id, syear, smoth);
		int BenefitCost = 0;
		int NonBenefitCost =0;
	    int yoyang = 0;
	    int bath =0;
	    int nurse =0;
	    int getYoyang =0;
	    int getbath =0;
	    int getnurse =0;
	    int mgetYoyang =0;
	    int mgetbath =0;
	    int mgetnurse =0;
	    
		for(int i=0; i< list.size(); i++) {
			Visit vit= list.get(i);
			//수정한 부분
			if((vit.getVisitServiceCategory()).equals("요양")) {
				vit.setVisitServiceTime((vit.getVisitServiceTime()).replaceAll("[^0-9]",""));	
			}
			BenefitCost bcost =  visitMapper.serviceCost(syear, vit.getVisitServiceCategory(),  
					vit.getVisitServiceTime());
			
			BenefitCost += bcost.getBenefitCost();
			NonBenefitCost += bcost.getNonBenefitCost();
			//setlist.add(bcost);
			
			if(bcost.getServiceCategory().contains("요양")) {
				getYoyang += bcost.getBenefitCost();
				mgetYoyang += bcost.getNonBenefitCost();
				yoyang +=1;
			}else if(bcost.getServiceCategory().contains("목욕")) {
				getbath += bcost.getBenefitCost();
				mgetbath += bcost.getNonBenefitCost();
				bath += 1;
			}else if(bcost.getServiceCategory().contains("간호")) {
				getnurse += bcost.getBenefitCost();
				mgetnurse += bcost.getNonBenefitCost();
				nurse += 1;
			}
		}

		subCost = maxcost - BenefitCost;  //잔액 전역 변수 선언
		int tolnum = yoyang+bath+nurse;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("yoyang", yoyang);   //요양 총 횟수
		map.put("bath", bath);       //목욕 총 횟수
		map.put("nurse", nurse);     //간호 총 횟수
		map.put("BenefitCost", BenefitCost); //총 급여 비용
		map.put("NonBenefitCost", NonBenefitCost); //총 본인 부담비용
		map.put("maxcost", maxcost);     //최대한도 수가 비용
		map.put("subCost", subCost);     //잔액 
		map.put("getYoyang", getYoyang);   //요양 수가
		map.put("getbath", getbath);       //목욕 수가
		map.put("getnurse", getnurse);     //간호 수가
		map.put("mgetYoyang", mgetYoyang);  //요양 본인 부담금 
		map.put("mgetbath", mgetbath);      //목욕 본인 부담금
		map.put("mgetnurse", mgetnurse);	//간호 본인 부담금 
		map.put("tolnum", tolnum);          //총 횟수
		
		System.out.println(getYoyang);	
		return map;	
	}
	
	//직원 종류 목록
	public List<Employee> empCategory(String empcategory, String centercode){
		
		List<Employee> list = visitMapper.empCategory(empcategory, centercode);
		return list;
	}
	
	
	
	//일정 등록 
	public String visitInsert(Visit visit){
//	public List<Visit> visitInsert(Visit visit){
		String str = null;
		
		String ort = visit.getVisitServiceTime();
		if((visit.getVisitServiceCategory()).equals("요양")) {
			 visit.setVisitServiceTime((visit.getVisitServiceTime()).replaceAll("[^0-9]",""));	
		}
		
		BenefitCost bcost =  visitMapper.serviceCost((visit.getMonthlyClaimGroupCode()).substring(0,4), visit.getVisitServiceCategory(),  
				visit.getVisitServiceTime());
		
		visit.setVisitServiceTime(ort);
		if(subCost- bcost.getBenefitCost()<0) {
			str ="초과";
		}else {
			visitMapper.visitInsert(visit);
			str ="정상";
		}
		
		//
		return str;
	}
	
	
   //직원 리스트 보여줌 
	public List<Employee> emplyeeList(String centerCode) {
		
		List<Employee> list = visitMapper.emplyeeList(centerCode);
		// TODO Auto-generated method stub
		return list;
	}

	//일정 등록 후 캘린더 리스트 보여주기
	public List<Visit> vCalenderList(String elderId){
		List<Visit> list = visitMapper.vCalenderList(elderId);
		return list;
	}
	
//	//직원 같은날짜, 시간 중복 체크
//	public void vemplyeeDayCheck(String employeeId, String visitPlanDate, String visitPlanTime) {
//		//String[] visitPlanTime1 = visitPlanTime.split("~");
//		
//		
//		visitMapper.emplyeeDayCheck(employeeId, visitPlanDate, visitPlanTime.replace("[^0-9]", ""));
//		//visitMapper.emplyeeDayCheck(employeeId, visitPlanDate,visitPlanTime1[0], visitPlanTime1[1]);
//		// TODO Auto-generated method stub
//		
//	}


}
