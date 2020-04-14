package com.cafe24.choiyooq1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.BenefitCost;
import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.EstimatedExpenses;
import com.cafe24.choiyooq1.domain.Visit;
import com.cafe24.choiyooq1.mapper.EstimatedExpensesMapper;
import com.cafe24.choiyooq1.mapper.VisitMapper;

@Service
@Transactional
public class EstimatEdexpensesService {

	@Autowired
	private EstimatedExpensesMapper estimatedexpensesMapper;
	@Autowired
	private VisitMapper visitMapper;
	
	//센터별 수급자 예상 금액(get)
	public List<EstimatedExpenses> centerEstimatedExpensesList(String centerCode, String syear, String smonth){
		
		List<Elder> elderList = estimatedexpensesMapper.centerElderList(centerCode);
		
		int benefitCost = 0;
		int nonBenefitCost =0;
		int total =0;
	    
		List<EstimatedExpenses> estimatedList = new ArrayList<EstimatedExpenses>();

		for(int i=0; i< elderList.size(); i++) {
			EstimatedExpenses estimated = new EstimatedExpenses();
			Elder elder = elderList.get(i);
			
			System.out.println("dfsdfsdfsdfsdfsdfs11111111" );
			int maxcost = estimatedexpensesMapper.elderMaxCost(elder.getElderId());
			System.out.println("dfsdfsdfsdfsdfs222222" +maxcost);
			estimated.setMaxCost(maxcost);
			estimated.setElderFinalServiceStatus(elder.getElderFinalServiceStatus());
			estimated.setElerId(elder.getElderId());
			estimated.setElderName(elder.getElderName());
			estimated.setElderBirthDate(elder.getElderBirthDate());
			estimated.setElderGender(elder.getElderGender());
			
			List<Visit> visitList = visitMapper.elderBenefitCost(elder.getElderId(), syear, smonth);
			
			for(int j=0; j< visitList.size(); j++) {
				Visit vit= visitList.get(j);
				//수정한 부분
				if((vit.getVisitServiceCategory()).equals("요양")) {
					vit.setVisitServiceTime((vit.getVisitServiceTime()).replaceAll("[^0-9]",""));	
				}
				BenefitCost bcost =  visitMapper.serviceCost(syear, vit.getVisitServiceCategory(),  
						vit.getVisitServiceTime());     //서비스 요금 
				
				benefitCost += bcost.getBenefitCost();
				nonBenefitCost += bcost.getNonBenefitCost();	
				total +=1;
			}
			
			estimated.setBenefitCost(benefitCost);
			estimated.setNonBenefitCost(nonBenefitCost);	
			estimated.setTotolNum(total);
			benefitCost = 0;
			nonBenefitCost =0;
			total = 0;
			
			estimatedList.add(estimated);
		}
	
		return estimatedList;
	}

	
	//수급자 개별 예상 금액
	public List<EstimatedExpenses> detailEstimatedExpenses(String ederId, String syear, String smonth){
		
		List<Visit> list = visitMapper.elderBenefitCost(ederId, syear, smonth);
	
		List<EstimatedExpenses> estimatedList2 = new ArrayList<EstimatedExpenses>();
		
	  		for(int i=0; i< list.size(); i++) {
			
				EstimatedExpenses estimated = new EstimatedExpenses();
	
				Visit vit= list.get(i);
				String serviceTimedetail = vit.getVisitServiceTime();
				System.out.println(serviceTimedetail);
				
				if((vit.getVisitServiceCategory()).equals("요양")) {
					vit.setVisitServiceTime((vit.getVisitServiceTime()).replaceAll("[^0-9]",""));	
				}
			
			BenefitCost bcost =  visitMapper.serviceCost(syear, vit.getVisitServiceCategory(),  
					vit.getVisitServiceTime());
			
			estimated.setData(vit.getVisitPlanDate());
			estimated.setServiceCategory(vit.getVisitServiceCategory());
			estimated.setServicedetail(serviceTimedetail);
			estimated.setBenefitCost(bcost.getBenefitCost());
			estimated.setNonBenefitCost(bcost.getNonBenefitCost());
				
			estimatedList2.add(estimated);
	  		}
		return estimatedList2;
	}
	
}
