package com.cafe24.choiyooq1.service;

import java.util.ArrayList;
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


public class VisitService3 {

	@Autowired
	private VisitMapper visitMapper;
	
	private int elderMaxCost =0;
	
	//수급자 목록 
	public List<Elder> elderAllList(String center_code){
		List<Elder> list = visitMapper.elderAllList(center_code);
		return list;
	}
	
	//수가 총 내용및 잔액 금액  
	public Map<String, Object> elderBenefitCost(String elder_id, String syear, String smoth, int maxcost){
	//public List<BenefitCost> elderBenefitCost(String elder_id, String syear, String smoth){
		
		List<Visit> list = visitMapper.elderBenefitCost(elder_id, syear, smoth);
		int BenefitCost = 0;
		int NonBenefitCost =0;
	    int yoyang = 0;
	    int bath =0;
	    int nurse =0;
	    Integer getBenefitCost[]= new Integer[3];
	    Integer getNonBenefitCost[] = new Integer[3];
		
		for(int i=0; i< list.size(); i++) {
			Visit vit= list.get(i);
			BenefitCost bcost =  visitMapper.serviceCost(syear, vit.getVisitServiceCategory(),  
					vit.getVisitServiceTime());

			BenefitCost += bcost.getBenefitCost();
			NonBenefitCost += bcost.getNonBenefitCost();
			//setlist.add(bcost);
			
			if(bcost.getServiceCategory().contains("요양")) {
				yoyang +=1;
				getBenefitCost[0] +=bcost.getBenefitCost();
				getNonBenefitCost[0] += bcost.getNonBenefitCost();
			}else if(bcost.getServiceCategory().contains("목욕")) {
				bath += 1;
				getBenefitCost[1] +=bcost.getBenefitCost();
				getNonBenefitCost[1] += bcost.getNonBenefitCost();
			}else if(bcost.getServiceCategory().contains("간호")) {
				nurse += 1;
				getBenefitCost[2] +=bcost.getBenefitCost();
				getNonBenefitCost[2] += bcost.getNonBenefitCost();
			}
		}

		int subCost = maxcost - BenefitCost;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yoyang", yoyang);   //요양 총 횟수
		map.put("bath", bath);       //목욕 총 횟수
		map.put("nurse", nurse);     //간호 총 횟수
		map.put("BenefitCost", BenefitCost); //총 급여 비용
		map.put("NonBenefitCost", NonBenefitCost); //총 본인 부담비용
		map.put("maxcost", maxcost);     //최대한도 수가 비용
		map.put("subCost", subCost);     //잔액 
		map.put("getBenefitCost", getBenefitCost);     //잔액 
		map.put("getBenefitCost", getNonBenefitCost); 
		
		return map;
		
		//System.out.println(BenefitCost);
		//System.out.println(NonBenefitCost);
		//System.out.println(setlist);
		//return setlist;
	}
	
	//직원 종류 목록
	public List<Employee> empCategory(String empcategory, String centercode){
		
		List<Employee> list = visitMapper.empCategory(empcategory, centercode);
		return list;
	}
	
	//일정 등록 
	public List<Visit> visitInsert(Visit visit){
		
		visit.setVisitCode("dfsdfsdfsdf");
		return null;
	}
}
