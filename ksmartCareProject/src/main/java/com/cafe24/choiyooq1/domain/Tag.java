package com.cafe24.choiyooq1.domain;

public class Tag {
	
	private int tagListNumber;
	private String tagCode;
	private String centerCode;
	private String centerName;
	private String employeeId;
	private String employeeName;
	private String elderId;
	private String elderName;
	private String tagStartDatetime;
	private String tagEndDatetime;
	private String tagServiceTime;
	private String tagServiceCategory;
	
	

	public int getTagListNumber() {
		return tagListNumber;
	}

	public void setTagListNumber(int tagListNumber) {
		this.tagListNumber = tagListNumber;
	}
	public String getTagCode() {
		return tagCode;
	}
	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
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



	public String getTagStartDatetime() {
		return tagStartDatetime;
	}



	public void setTagStartDatetime(String tagStartDatetime) {
		this.tagStartDatetime = tagStartDatetime;
	}



	public String getTagEndDatetime() {
		return tagEndDatetime;
	}



	public void setTagEndDatetime(String tagEndDatetime) {
		this.tagEndDatetime = tagEndDatetime;
	}



	public String getTagServiceTime() {
		return tagServiceTime;
	}



	public void setTagServiceTime(String tagServiceTime) {
		this.tagServiceTime = tagServiceTime;
	}



	public String getTagServiceCategory() {
		return tagServiceCategory;
	}



	public void setTagServiceCategory(String tagServiceCategory) {
		this.tagServiceCategory = tagServiceCategory;
	}



	@Override
	public String toString() {
		return "Tag [tagListNumber=" + tagListNumber + ", tagCode=" + tagCode + ", centerCode=" + centerCode
				+ ", centerName=" + centerName + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", elderId=" + elderId + ", elderName=" + elderName + ", tagStartDatetime=" + tagStartDatetime
				+ ", tagEndDatetime=" + tagEndDatetime + ", tagServiceTime=" + tagServiceTime + ", tagServiceCategory="
				+ tagServiceCategory + "]";
	}






}
