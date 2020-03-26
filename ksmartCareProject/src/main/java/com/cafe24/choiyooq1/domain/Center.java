package com.cafe24.choiyooq1.domain;

public class Center {
	private int centerListNumber;
	private String centerCode;
	private String centerName;
	private String centerId;
	private String centerPw;
	private String centerManagerName;
	private String conterManagerPhone;
	private String centerPhone;
	private String centerLocal;
	private String centerAdress;
	private String serviceStartDate;
	private String serviceEndDate;
	private String serviceStatus;
	private String businessRegistrationNumber;
	private String employerName;
	
	
	public int getCenterListNumber() {
		return centerListNumber;
	}
	public void setCenterListNumber(int centerListNumber) {
		this.centerListNumber = centerListNumber;
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
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterPw() {
		return centerPw;
	}
	public void setCenterPw(String centerPw) {
		this.centerPw = centerPw;
	}
	public String getCenterManagerName() {
		return centerManagerName;
	}
	public void setCenterManagerName(String centerManagerName) {
		this.centerManagerName = centerManagerName;
	}
	public String getConterManagerPhone() {
		return conterManagerPhone;
	}
	public void setConterManagerPhone(String conterManagerPhone) {
		this.conterManagerPhone = conterManagerPhone;
	}
	public String getCenterPhone() {
		return centerPhone;
	}
	public void setCenterPhone(String centerPhone) {
		this.centerPhone = centerPhone;
	}
	public String getCenterLocal() {
		return centerLocal;
	}
	public void setCenterLocal(String centerLocal) {
		this.centerLocal = centerLocal;
	}
	public String getCenterAdress() {
		return centerAdress;
	}
	public void setCenterAdress(String centerAdress) {
		this.centerAdress = centerAdress;
	}
	public String getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getServiceEndDate() {
		return serviceEndDate;
	}
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	
	public String getBusinessRegistrationNumber() {
		return businessRegistrationNumber;
	}
	public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
		this.businessRegistrationNumber = businessRegistrationNumber;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	@Override
	public String toString() {
		return "Center [centerListNumber=" + centerListNumber + ", centerCode=" + centerCode + ", centerName="
				+ centerName + ", centerId=" + centerId + ", centerPw=" + centerPw + ", centerManagerName="
				+ centerManagerName + ", conterManagerPhone=" + conterManagerPhone + ", centerPhone=" + centerPhone
				+ ", centerLocal=" + centerLocal + ", centerAdress=" + centerAdress + ", serviceStartDate="
				+ serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", serviceStatus=" + serviceStatus
				+ ", businessRegistrationNumber=" + businessRegistrationNumber + ", employerName=" + employerName + "]";
	}
	
	
	
	
	
}
