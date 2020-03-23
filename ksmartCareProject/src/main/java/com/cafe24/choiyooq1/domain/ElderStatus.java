package com.cafe24.choiyooq1.domain;

public class ElderStatus {
	private String serviceStatusCode;
	private String centerCode;
	private String centerName;
	private String elderId;
	private String elderName;
	private String serviceStatus;
	private String serviceStartDate;
	private String serviceEndDate;
	public String getServiceStatusCode() {
		return serviceStatusCode;
	}
	public void setServiceStatusCode(String serviceStatusCode) {
		this.serviceStatusCode = serviceStatusCode;
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
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public String getServiceEndDate() {
		return serviceEndDate;
	}
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	@Override
	public String toString() {
		return "ElderStatus [serviceStatusCode=" + serviceStatusCode + ", centerCode=" + centerCode + ", centerName="
				+ centerName + ", elderId=" + elderId + ", elderName=" + elderName + ", serviceStatus=" + serviceStatus
				+ ", serviceStartDate=" + serviceStartDate + ", serviceEndDate=" + serviceEndDate + "]";
	}

}
