package com.cafe24.choiyooq1.domain;

public class Employee {
	
	private String employeeId;
	private String centerCode;
	private String centerName;
	private String employeeName;
	private String employeePw;
	private String employeeCategory;
	private String employeeGender;
	private String employeePersonalNumber;
	private String employeePhone;
	private String employeeStartDate;
	private String employeeWorkingStatus;
	private String employeeWorkingType;
	private String employeePosition;
	private String employeePayType;
	private String employeeHourlyPay;
	private String employeeAccount;
	private String employeeAccountHolder;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeePw() {
		return employeePw;
	}
	public void setEmployeePw(String employeePw) {
		this.employeePw = employeePw;
	}
	public String getEmployeeCategory() {
		return employeeCategory;
	}
	public void setEmployeeCategory(String employeeCategory) {
		this.employeeCategory = employeeCategory;
	}
	public String getEmployeeGender() {
		return employeeGender;
	}
	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}
	public String getEmployeePersonalNumber() {
		return employeePersonalNumber;
	}
	public void setEmployeePersonalNumber(String employeePersonalNumber) {
		this.employeePersonalNumber = employeePersonalNumber;
	}
	public String getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}
	public String getEmployeeStartDate() {
		return employeeStartDate;
	}
	public void setEmployeeStartDate(String employeeStartDate) {
		this.employeeStartDate = employeeStartDate;
	}
	public String getEmployeeWorkingStatus() {
		return employeeWorkingStatus;
	}
	public void setEmployeeWorkingStatus(String employeeWorkingStatus) {
		this.employeeWorkingStatus = employeeWorkingStatus;
	}
	public String getEmployeeWorkingType() {
		return employeeWorkingType;
	}
	public void setEmployeeWorkingType(String employeeWorkingType) {
		this.employeeWorkingType = employeeWorkingType;
	}
	public String getEmployeePosition() {
		return employeePosition;
	}
	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}
	public String getEmployeePayType() {
		return employeePayType;
	}
	public void setEmployeePayType(String employeePayType) {
		this.employeePayType = employeePayType;
	}
	public String getEmployeeHourlyPay() {
		return employeeHourlyPay;
	}
	public void setEmployeeHourlyPay(String employeeHourlyPay) {
		this.employeeHourlyPay = employeeHourlyPay;
	}
	public String getEmployeeAccount() {
		return employeeAccount;
	}
	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}
	public String getEmployeeAccountHolder() {
		return employeeAccountHolder;
	}
	public void setEmployeeAccountHolder(String employeeAccountHolder) {
		this.employeeAccountHolder = employeeAccountHolder;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", centerCode=" + centerCode + ", centerName=" + centerName
				+ ", employeeName=" + employeeName + ", employeePw=" + employeePw + ", employeeCategory="
				+ employeeCategory + ", employeeGender=" + employeeGender + ", employeePersonalNumber="
				+ employeePersonalNumber + ", employeePhone=" + employeePhone + ", employeeStartDate="
				+ employeeStartDate + ", employeeWorkingStatus=" + employeeWorkingStatus + ", employeeWorkingType="
				+ employeeWorkingType + ", employeePosition=" + employeePosition + ", employeePayType="
				+ employeePayType + ", employeeHourlyPay=" + employeeHourlyPay + ", employeeAccount=" + employeeAccount
				+ ", employeeAccountHolder=" + employeeAccountHolder + "]";
	}
}
