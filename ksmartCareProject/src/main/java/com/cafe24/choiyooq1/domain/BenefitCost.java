package com.cafe24.choiyooq1.domain;

public class BenefitCost {
	private String benefitCostCode;
	private String serviceCategory;
	private String serviceTimeAndType;
	private String benefitCostYear;
	private int benefitCost;
	private int nonBenefitCost;
	
	public String getBenefitCostCode() {
		return benefitCostCode;
	}
	public void setBenefitCostCode(String benefitCostCode) {
		this.benefitCostCode = benefitCostCode;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getServiceTimeAndType() {
		return serviceTimeAndType;
	}
	public void setServiceTimeAndType(String serviceTimeAndType) {
		this.serviceTimeAndType = serviceTimeAndType;
	}
	public String getBenefitCostYear() {
		return benefitCostYear;
	}
	public void setBenefitCostYear(String benefitCostYear) {
		this.benefitCostYear = benefitCostYear;
	}
	public int getBenefitCost() {
		return benefitCost;
	}
	public void setBenefitCost(int benefitCost) {
		this.benefitCost = benefitCost;
	}
	public int getNonBenefitCost() {
		return nonBenefitCost;
	}
	public void setNonBenefitCost(int nonBenefitCost) {
		this.nonBenefitCost = nonBenefitCost;
	}
	@Override
	public String toString() {
		return "BenefitCost [benefitCostCode=" + benefitCostCode + ", serviceCategory=" + serviceCategory
				+ ", serviceTimeAndType=" + serviceTimeAndType + ", benefitCostYear=" + benefitCostYear
				+ ", benefitCost=" + benefitCost + ", nonBenefitCost=" + nonBenefitCost + "]";
	}
}
