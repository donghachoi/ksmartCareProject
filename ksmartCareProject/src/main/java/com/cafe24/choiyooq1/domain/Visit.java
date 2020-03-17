package com.cafe24.choiyooq1.domain;

public class Visit {

	private String visitCode;
	private String centerCode;
	private String centerName;
	private String elderId;
	private String elderName;
	private String employeeId;
	private String employeeName;
	private String visitPlanDate;
	private String visitPlanTime;
	private String visitServiceTime;
	private String familyRelation;
	private String visitServiceCategory;
	private String monthlyClaimGroupCode;
	private String backgroundColor;
	private String description;
	private String yoyangBathNonBenefit;
	private String serviceCategoryDetail;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getYoyangBathNonBenefit() {
		return yoyangBathNonBenefit;
	}
	public void setYoyangBathNonBenefit(String yoyangBathNonBenefit) {
		this.yoyangBathNonBenefit = yoyangBathNonBenefit;
	}
	public String getServiceCategoryDetail() {
		return serviceCategoryDetail;
	}
	public void setServiceCategoryDetail(String serviceCategoryDetail) {
		this.serviceCategoryDetail = serviceCategoryDetail;
	}
	public String getYoyang_bath_non_benefit() {
		return yoyangBathNonBenefit;
	}
	public void setYoyang_bath_non_benefit(String yoyangBathNonBenefit) {
		this.yoyangBathNonBenefit = yoyangBathNonBenefit;
	}
	public String getVisitCode() {
		return visitCode;
	}
	public void setVisitCode(String visitCode) {
		this.visitCode = visitCode;
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getVisitPlanDate() {
		return visitPlanDate;
	}
	public void setVisitPlanDate(String visitPlanDate) {
		this.visitPlanDate = visitPlanDate;
	}
	public String getVisitPlanTime() {
		return visitPlanTime;
	}
	public void setVisitPlanTime(String visitPlanTime) {
		this.visitPlanTime = visitPlanTime;
	}
	public String getVisitServiceTime() {
		return visitServiceTime;
	}
	public void setVisitServiceTime(String visitServiceTime) {
		this.visitServiceTime = visitServiceTime;
	}
	public String getFamilyRelation() {
		return familyRelation;
	}
	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}
	public String getVisitServiceCategory() {
		return visitServiceCategory;
	}
	public void setVisitServiceCategory(String visitServiceCategory) {
		this.visitServiceCategory = visitServiceCategory;
	}
	public String getMonthlyClaimGroupCode() {
		return monthlyClaimGroupCode;
	}
	public void setMonthlyClaimGroupCode(String monthlyClaimGroupCode) {
		this.monthlyClaimGroupCode = monthlyClaimGroupCode;
	}
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Visit [visitCode=" + visitCode + ", centerCode=" + centerCode + ", centerName=" + centerName
				+ ", elderId=" + elderId + ", elderName=" + elderName + ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", visitPlanDate=" + visitPlanDate + ", visitPlanTime=" + visitPlanTime
				+ ", visitServiceTime=" + visitServiceTime + ", familyRelation=" + familyRelation
				+ ", visitServiceCategory=" + visitServiceCategory + ", monthlyClaimGroupCode=" + monthlyClaimGroupCode
				+ ", backgroundColor=" + backgroundColor + ", description=" + description + ", yoyangBathNonBenefit="
				+ yoyangBathNonBenefit + "]";
	}
}
