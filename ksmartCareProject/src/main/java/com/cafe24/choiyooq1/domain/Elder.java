package com.cafe24.choiyooq1.domain;

public class Elder {

	private String elderId;
	private String elderName;
	private String centerCode;
	private String centerName;
	private String elderBirthname;
	private String elderGender;
	private String elderFinalServiceApprovalLevel;
	private String elderFinalServiceStatus;
	private String elderDisease;
	private String elderCoinsurance;
	private String totalVisits;
	private String totalServiceTime;
	private String serviceLocalmanageCode;
	private String serviceLocalmanageName;
	private String longTermCareNumver;
	private String longTermCareValidity;
	private String elderPhone;
	private String elderAdressForStay;
	private String elderAdressForPost;
	private String elderRemarks;
	private String elderGroups;
	private int benefitMaxCost;
	
	public String getElderId() {
		return elderId;
	}
	public void setElderId(String elderId) {
		this.elderId = elderId;
	}
	public String getElderName() {
		return elderName;
	}
	public void setElderName(String elderName) {
		this.elderName = elderName;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getElderBirthname() {
		return elderBirthname;
	}
	public void setElderBirthname(String elderBirthname) {
		this.elderBirthname = elderBirthname;
	}
	public String getElderGender() {
		return elderGender;
	}
	public void setElderGender(String elderGender) {
		this.elderGender = elderGender;
	}
	public String getElderFinalServiceApprovalLevel() {
		return elderFinalServiceApprovalLevel;
	}
	public void setElderFinalServiceApprovalLevel(String elderFinalServiceApprovalLevel) {
		this.elderFinalServiceApprovalLevel = elderFinalServiceApprovalLevel;
	}
	public String getElderFinalServiceStatus() {
		return elderFinalServiceStatus;
	}
	public void setElderFinalServiceStatus(String elderFinalServiceStatus) {
		this.elderFinalServiceStatus = elderFinalServiceStatus;
	}
	public String getElderDisease() {
		return elderDisease;
	}
	public void setElderDisease(String elderDisease) {
		this.elderDisease = elderDisease;
	}
	public String getElderCoinsurance() {
		return elderCoinsurance;
	}
	public void setElderCoinsurance(String elderCoinsurance) {
		this.elderCoinsurance = elderCoinsurance;
	}
	public String getTotalVisits() {
		return totalVisits;
	}
	public void setTotalVisits(String totalVisits) {
		this.totalVisits = totalVisits;
	}
	public String getTotalServiceTime() {
		return totalServiceTime;
	}
	public void setTotalServiceTime(String totalServiceTime) {
		this.totalServiceTime = totalServiceTime;
	}
	public String getServiceLocalmanageCode() {
		return serviceLocalmanageCode;
	}
	public void setServiceLocalmanageCode(String serviceLocalmanageCode) {
		this.serviceLocalmanageCode = serviceLocalmanageCode;
	}
	public String getServiceLocalmanageName() {
		return serviceLocalmanageName;
	}
	public void setServiceLocalmanageName(String serviceLocalmanageName) {
		this.serviceLocalmanageName = serviceLocalmanageName;
	}
	public String getLongTermCareNumver() {
		return longTermCareNumver;
	}
	public void setLongTermCareNumver(String longTermCareNumver) {
		this.longTermCareNumver = longTermCareNumver;
	}
	public String getLongTermCareValidity() {
		return longTermCareValidity;
	}
	public void setLongTermCareValidity(String longTermCareValidity) {
		this.longTermCareValidity = longTermCareValidity;
	}
	public String getElderPhone() {
		return elderPhone;
	}
	public void setElderPhone(String elderPhone) {
		this.elderPhone = elderPhone;
	}
	public String getElderAdressForStay() {
		return elderAdressForStay;
	}
	public void setElderAdressForStay(String elderAdressForStay) {
		this.elderAdressForStay = elderAdressForStay;
	}
	public String getElderAdressForPost() {
		return elderAdressForPost;
	}
	public void setElderAdressForPost(String elderAdressForPost) {
		this.elderAdressForPost = elderAdressForPost;
	}
	public String getElderRemarks() {
		return elderRemarks;
	}
	public void setElderRemarks(String elderRemarks) {
		this.elderRemarks = elderRemarks;
	}
	public String getElderGroups() {
		return elderGroups;
	}
	public void setElderGroups(String elderGroups) {
		this.elderGroups = elderGroups;
	}
	public int getBenefitMaxCost() {
		return benefitMaxCost;
	}
	public void setBenefitMaxCost(int benefitMaxCost) {
		this.benefitMaxCost = benefitMaxCost;
	}
	@Override
	public String toString() {
		return "Elder [elderId=" + elderId + ", elderName=" + elderName + ", centerCode=" + centerCode + ", centerName="
				+ centerName + ", elderBirthname=" + elderBirthname + ", elderGender=" + elderGender
				+ ", elderFinalServiceApprovalLevel=" + elderFinalServiceApprovalLevel + ", elderFinalServiceStatus="
				+ elderFinalServiceStatus + ", elderDisease=" + elderDisease + ", elderCoinsurance=" + elderCoinsurance
				+ ", totalVisits=" + totalVisits + ", totalServiceTime=" + totalServiceTime
				+ ", serviceLocalmanageCode=" + serviceLocalmanageCode + ", serviceLocalmanageName="
				+ serviceLocalmanageName + ", longTermCareNumver=" + longTermCareNumver + ", longTermCareValidity="
				+ longTermCareValidity + ", elderPhone=" + elderPhone + ", elderAdressForStay=" + elderAdressForStay
				+ ", elderAdressForPost=" + elderAdressForPost + ", elderRemarks=" + elderRemarks + ", elderGroups="
				+ elderGroups + ", benefitMaxCost=" + benefitMaxCost + "]";
	}
}
