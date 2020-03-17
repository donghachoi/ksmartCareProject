package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.BenefitCost;
import com.cafe24.choiyooq1.domain.BenefitMax;
import com.cafe24.choiyooq1.mapper.BenefitMapper;

@Service
@Transactional
public class BenefitService {
	
	@Autowired BenefitMapper benefitMapper;
	
	/* 수가 리스트 가져오기 */
	public List<BenefitCost> getBenefitCost(){
		return benefitMapper.getBenefitCost();
	}
	/* 수가 한도액 가져오기 */
	public List<BenefitMax> getBenefitMax(){
		
		return benefitMapper.getBenefitMax();
	}
}
