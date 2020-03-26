package com.cafe24.choiyooq1.domain;

public class GuaranteeingAgency {
	private String guaranteeingAgencyNember;
	private String guaranteeingAgencyName;
	private String guaranteeingAgencyPost;
	private String guaranteeingAgencyAddress;
	private String guaranteeingAgencyConnectnumber;
	public String getGuaranteeingAgencyNember() {
		return guaranteeingAgencyNember;
	}
	public void setGuaranteeingAgencyNember(String guaranteeingAgencyNember) {
		this.guaranteeingAgencyNember = guaranteeingAgencyNember;
	}
	public String getGuaranteeingAgencyName() {
		return guaranteeingAgencyName;
	}
	public void setGuaranteeingAgencyName(String guaranteeingAgencyName) {
		this.guaranteeingAgencyName = guaranteeingAgencyName;
	}
	public String getGuaranteeingAgencyPost() {
		return guaranteeingAgencyPost;
	}
	public void setGuaranteeingAgencyPost(String guaranteeingAgencyPost) {
		this.guaranteeingAgencyPost = guaranteeingAgencyPost;
	}
	public String getGuaranteeingAgencyAddress() {
		return guaranteeingAgencyAddress;
	}
	public void setGuaranteeingAgencyAddress(String guaranteeingAgencyAddress) {
		this.guaranteeingAgencyAddress = guaranteeingAgencyAddress;
	}
	public String getGuaranteeingAgencyConnectnumber() {
		return guaranteeingAgencyConnectnumber;
	}
	public void setGuaranteeingAgencyConnectnumber(String guaranteeingAgencyConnectnumber) {
		this.guaranteeingAgencyConnectnumber = guaranteeingAgencyConnectnumber;
	}
	@Override
	public String toString() {
		return "GuaranteeingAgency [guaranteeingAgencyNember=" + guaranteeingAgencyNember + ", guaranteeingAgencyName="
				+ guaranteeingAgencyName + ", guaranteeingAgencyPost=" + guaranteeingAgencyPost
				+ ", guaranteeingAgencyAddress=" + guaranteeingAgencyAddress + ", guaranteeingAgencyConnectnumber="
				+ guaranteeingAgencyConnectnumber + "]";
	}
	

}
