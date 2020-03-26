package com.cafe24.choiyooq1.domain;

public class ElderLevelHistory {
	private String elderLevelHistoryCode;
	private String elderId;
	private String elderName;
	private String centerCode;
	private String centerName;
	private String elderServiceApprovalLevel;
	private String elderServiceApprovalCategory;
	private String elderServiceApprovalCategory2;
	private String elderSercviceApplyDate;
	private String elderServiceApprovalStartDate;
	private String elderServiceApprovalEndDate;
	private String elderServiceApprovalNumber;
	
	public String getElderLevelHistoryCode() {
		return elderLevelHistoryCode;
	}
	public void setElderLevelHistoryCode(String elderLevelHistoryCode) {
		this.elderLevelHistoryCode = elderLevelHistoryCode;
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
	public String getElderServiceApprovalLevel() {
		return elderServiceApprovalLevel;
	}
	public void setElderServiceApprovalLevel(String elderServiceApprovalLevel) {
		this.elderServiceApprovalLevel = elderServiceApprovalLevel;
	}
	public String getElderServiceApprovalCategory() {
		return elderServiceApprovalCategory;
	}
	public void setElderServiceApprovalCategory(String elderServiceApprovalCategory) {
		this.elderServiceApprovalCategory = elderServiceApprovalCategory;
	}
	public String getElderServiceApprovalCategory2() {
		return elderServiceApprovalCategory2;
	}
	public void setElderServiceApprovalCategory2(String elderServiceApprovalCategory2) {
		this.elderServiceApprovalCategory2 = elderServiceApprovalCategory2;
	}
	public String getElderSercviceApplyDate() {
		return elderSercviceApplyDate;
	}
	public void setElderSercviceApplyDate(String elderSercviceApplyDate) {
		this.elderSercviceApplyDate = elderSercviceApplyDate;
	}
	public String getElderServiceApprovalStartDate() {
		return elderServiceApprovalStartDate;
	}
	public void setElderServiceApprovalStartDate(String elderServiceApprovalStartDate) {
		this.elderServiceApprovalStartDate = elderServiceApprovalStartDate;
	}
	public String getElderServiceApprovalEndDate() {
		return elderServiceApprovalEndDate;
	}
	public void setElderServiceApprovalEndDate(String elderServiceApprovalEndDate) {
		this.elderServiceApprovalEndDate = elderServiceApprovalEndDate;
	}
	public String getElderServiceApprovalNumber() {
		return elderServiceApprovalNumber;
	}
	public void setElderServiceApprovalNumber(String elderServiceApprovalNumber) {
		this.elderServiceApprovalNumber = elderServiceApprovalNumber;
	}
	@Override
	public String toString() {
		return "ElderLevelHistory [elderLevelHistoryCode=" + elderLevelHistoryCode + ", elderId=" + elderId
				+ ", elderName=" + elderName + ", centerCode=" + centerCode + ", centerName=" + centerName
				+ ", elderServiceApprovalLevel=" + elderServiceApprovalLevel + ", elderServiceApprovalCategory="
				+ elderServiceApprovalCategory + ", elderServiceApprovalCategory2=" + elderServiceApprovalCategory2
				+ ", elderSercviceApplyDate=" + elderSercviceApplyDate + ", elderServiceApprovalStartDate="
				+ elderServiceApprovalStartDate + ", elderServiceApprovalEndDate=" + elderServiceApprovalEndDate
				+ ", elderServiceApprovalNumber=" + elderServiceApprovalNumber + "]";
	}
}
