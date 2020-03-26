package com.cafe24.choiyooq1.domain;

public class ElderRegularCheck {
	private String elderRegularCheckCode;
	private String centerCode;
	private String centerName;
	private String elderId;
	private String elderName;
	private String employeeId;
	private String employeeName;
	private String elderRegularCheckCategory;
	private String elderRegularCheckPlanDate;
	private String elderRegularCheckDoingDate;
	private String elderRegularCheckRegistrationDate;
	public String getElderRegularCheckCode() {
		return elderRegularCheckCode;
	}
	public void setElderRegularCheckCode(String elderRegularCheckCode) {
		this.elderRegularCheckCode = elderRegularCheckCode;
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
	public String getElderRegularCheckCategory() {
		return elderRegularCheckCategory;
	}
	public void setElderRegularCheckCategory(String elderRegularCheckCategory) {
		this.elderRegularCheckCategory = elderRegularCheckCategory;
	}
	public String getElderRegularCheckPlanDate() {
		return elderRegularCheckPlanDate;
	}
	public void setElderRegularCheckPlanDate(String elderRegularCheckPlanDate) {
		this.elderRegularCheckPlanDate = elderRegularCheckPlanDate;
	}
	public String getElderRegularCheckDoingDate() {
		return elderRegularCheckDoingDate;
	}
	public void setElderRegularCheckDoingDate(String elderRegularCheckDoingDate) {
		this.elderRegularCheckDoingDate = elderRegularCheckDoingDate;
	}
	public String getElderRegularCheckRegistrationDate() {
		return elderRegularCheckRegistrationDate;
	}
	public void setElderRegularCheckRegistrationDate(String elderRegularCheckRegistrationDate) {
		this.elderRegularCheckRegistrationDate = elderRegularCheckRegistrationDate;
	}
	@Override
	public String toString() {
		return "ElderRegularCheck [elderRegularCheckCode=" + elderRegularCheckCode + ", centerCode=" + centerCode
				+ ", centerName=" + centerName + ", elderId=" + elderId + ", elderName=" + elderName + ", employeeId="
				+ employeeId + ", employeeName=" + employeeName + ", elderRegularCheckCategory="
				+ elderRegularCheckCategory + ", elderRegularCheckPlanDate=" + elderRegularCheckPlanDate
				+ ", elderRegularCheckDoingDate=" + elderRegularCheckDoingDate + ", elderRegularCheckRegistrationDate="
				+ elderRegularCheckRegistrationDate + "]";
	}

}
