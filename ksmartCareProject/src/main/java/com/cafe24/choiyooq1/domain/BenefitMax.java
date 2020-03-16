package com.cafe24.choiyooq1.domain;

public class BenefitMax {
	private String benefitMaxCode;
	private String serviceLevel;
	private String benefitMaxYear;
	private int benefitMaxCost;
	
	public String getBenefitMaxCode() {
		return benefitMaxCode;
	}
	public void setBenefitMaxCode(String benefitMaxCode) {
		this.benefitMaxCode = benefitMaxCode;
	}
	public String getServiceLevel() {
		return serviceLevel;
	}
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
	public String getBenefitMaxYear() {
		return benefitMaxYear;
	}
	public void setBenefitMaxYear(String benefitMaxYear) {
		this.benefitMaxYear = benefitMaxYear;
	}
	public int getBenefitMaxCost() {
		return benefitMaxCost;
	}
	public void setBenefitMaxCost(int benefitMaxCost) {
		this.benefitMaxCost = benefitMaxCost;
	}
	@Override
	public String toString() {
		return "BenefitMax [benefitMaxCode=" + benefitMaxCode + ", serviceLevel=" + serviceLevel + ", benefitMaxYear="
				+ benefitMaxYear + ", benefitMaxCost=" + benefitMaxCost + "]";
	}
}
