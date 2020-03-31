package com.cafe24.choiyooq1.domain;


public class EstimatedExpenses {

	private String elerId;
	private String elderName;
	private int benefitCost;
	private int nonBenefitCost;
	private int maxCost;
	private int subCost;
	private int yoyangCost;
	private int bathCost;
	private int nurseCost;
	private int myYoyangCost;
	private int myBathCost;
	private int myNurseCost;
	private int totolNum;
	private String elderFinalServiceStatus;
	private String data;
	private String serviceCategory;
	private String servicedetail;
	private String elderGender;
	private String elderBirthDate;
	
	public String getElderGender() {
		return elderGender;
	}
	public void setElderGender(String elderGender) {
		this.elderGender = elderGender;
	}
	public String getElderBirthDate() {
		return elderBirthDate;
	}
	public void setElderBirthDate(String elderBirthDate) {
		this.elderBirthDate = elderBirthDate;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getServicedetail() {
		return servicedetail;
	}
	public void setServicedetail(String servicedetail) {
		this.servicedetail = servicedetail;
	}
	public String getElderFinalServiceStatus() {
		return elderFinalServiceStatus;
	}
	public void setElderFinalServiceStatus(String elderFinalServiceStatus) {
		this.elderFinalServiceStatus = elderFinalServiceStatus;
	}
	public String getElerId() {
		return elerId;
	}
	public void setElerId(String elerId) {
		this.elerId = elerId;
	}
	public String getElderName() {
		return elderName;
	}
	public void setElderName(String elderName) {
		this.elderName = elderName;
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
	public int getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(int maxCost) {
		this.maxCost = maxCost;
	}
	public int getSubCost() {
		return subCost;
	}
	public void setSubCost(int subCost) {
		this.subCost = subCost;
	}
	public int getYoyangCost() {
		return yoyangCost;
	}
	public void setYoyangCost(int yoyangCost) {
		this.yoyangCost = yoyangCost;
	}
	public int getBathCost() {
		return bathCost;
	}
	public void setBathCost(int bathCost) {
		this.bathCost = bathCost;
	}
	public int getNurseCost() {
		return nurseCost;
	}
	public void setNurseCost(int nurseCost) {
		this.nurseCost = nurseCost;
	}
	public int getMyYoyangCost() {
		return myYoyangCost;
	}
	public void setMyYoyangCost(int myYoyangCost) {
		this.myYoyangCost = myYoyangCost;
	}
	public int getMyBathCost() {
		return myBathCost;
	}
	public void setMyBathCost(int myBathCost) {
		this.myBathCost = myBathCost;
	}
	public int getMyNurseCost() {
		return myNurseCost;
	}
	public void setMyNurseCost(int myNurseCost) {
		this.myNurseCost = myNurseCost;
	}
	public int getTotolNum() {
		return totolNum;
	}
	public void setTotolNum(int totolNum) {
		this.totolNum = totolNum;
	}
	@Override
	public String toString() {
		return "EstimatedExpenses [elerId=" + elerId + ", elderName=" + elderName + ", benefitCost=" + benefitCost
				+ ", nonBenefitCost=" + nonBenefitCost + ", maxCost=" + maxCost + ", subCost=" + subCost
				+ ", yoyangCost=" + yoyangCost + ", bathCost=" + bathCost + ", nurseCost=" + nurseCost
				+ ", myYoyangCost=" + myYoyangCost + ", myBathCost=" + myBathCost + ", myNurseCost=" + myNurseCost
				+ ", totolNum=" + totolNum + "]";
	}
	
}
