package com.cafe24.choiyooq1.domain;

public class Elder {

	private String elder_id;
	private String elder_name;
	private String center_code;
	private String center_name;
	private String elder_birthname;
	private String elder_gender;
	private String elder_final_service_approval_level;
	private String elder_final_service_status;
	private String elder_disease;
	private String elder_coinsurance;
	private String total_visits;
	private String total_service_time;
	private String service_localmanage_code;
	private String service_localmanage_name;
	private String long_term_care_numver;
	private String long_term_care_validity;
	private String elder_phone;
	private String elder_adress_for_stay;
	private String elder_adress_for_post;
	private String elder_remarks;
	private String elder_groups;
	private int benefit_max_cost;
	
	public String getElder_id() {
		return elder_id;
	}
	public void setElder_id(String elder_id) {
		this.elder_id = elder_id;
	}
	public String getElder_name() {
		return elder_name;
	}
	public void setElder_name(String elder_name) {
		this.elder_name = elder_name;
	}
	public String getCenter_code() {
		return center_code;
	}
	public void setCenter_code(String center_code) {
		this.center_code = center_code;
	}
	public String getCenter_name() {
		return center_name;
	}
	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}
	public String getElder_birthname() {
		return elder_birthname;
	}
	public void setElder_birthname(String elder_birthname) {
		this.elder_birthname = elder_birthname;
	}
	public String getElder_gender() {
		return elder_gender;
	}
	public void setElder_gender(String elder_gender) {
		this.elder_gender = elder_gender;
	}
	public String getElder_final_service_approval_level() {
		return elder_final_service_approval_level;
	}
	public void setElder_final_service_approval_level(String elder_final_service_approval_level) {
		this.elder_final_service_approval_level = elder_final_service_approval_level;
	}
	public String getElder_final_service_status() {
		return elder_final_service_status;
	}
	public void setElder_final_service_status(String elder_final_service_status) {
		this.elder_final_service_status = elder_final_service_status;
	}
	public String getElder_disease() {
		return elder_disease;
	}
	public void setElder_disease(String elder_disease) {
		this.elder_disease = elder_disease;
	}
	public String getElder_coinsurance() {
		return elder_coinsurance;
	}
	public void setElder_coinsurance(String elder_coinsurance) {
		this.elder_coinsurance = elder_coinsurance;
	}
	public String getTotal_visits() {
		return total_visits;
	}
	public void setTotal_visits(String total_visits) {
		this.total_visits = total_visits;
	}
	public String getTotal_service_time() {
		return total_service_time;
	}
	public void setTotal_service_time(String total_service_time) {
		this.total_service_time = total_service_time;
	}
	public String getService_localmanage_code() {
		return service_localmanage_code;
	}
	public void setService_localmanage_code(String service_localmanage_code) {
		this.service_localmanage_code = service_localmanage_code;
	}
	public String getService_localmanage_name() {
		return service_localmanage_name;
	}
	public void setService_localmanage_name(String service_localmanage_name) {
		this.service_localmanage_name = service_localmanage_name;
	}
	public String getLong_term_care_numver() {
		return long_term_care_numver;
	}
	public void setLong_term_care_numver(String long_term_care_numver) {
		this.long_term_care_numver = long_term_care_numver;
	}
	public String getLong_term_care_validity() {
		return long_term_care_validity;
	}
	public void setLong_term_care_validity(String long_term_care_validity) {
		this.long_term_care_validity = long_term_care_validity;
	}
	public String getElder_phone() {
		return elder_phone;
	}
	public void setElder_phone(String elder_phone) {
		this.elder_phone = elder_phone;
	}
	public String getElder_adress_for_stay() {
		return elder_adress_for_stay;
	}
	public void setElder_adress_for_stay(String elder_adress_for_stay) {
		this.elder_adress_for_stay = elder_adress_for_stay;
	}
	public String getElder_adress_for_post() {
		return elder_adress_for_post;
	}
	public void setElder_adress_for_post(String elder_adress_for_post) {
		this.elder_adress_for_post = elder_adress_for_post;
	}
	public String getElder_remarks() {
		return elder_remarks;
	}
	public void setElder_remarks(String elder_remarks) {
		this.elder_remarks = elder_remarks;
	}
	public String getElder_groups() {
		return elder_groups;
	}
	public void setElder_groups(String elder_groups) {
		this.elder_groups = elder_groups;
	}
	
	
	public int getBenefit_max_cost() {
		return benefit_max_cost;
	}
	public void setBenefit_max_cost(int benefit_max_cost) {
		this.benefit_max_cost = benefit_max_cost;
	}
	
	@Override
	public String toString() {
		return "Elder [elder_id=" + elder_id + ", elder_name=" + elder_name + ", center_code=" + center_code
				+ ", center_name=" + center_name + ", elder_birthname=" + elder_birthname + ", elder_gender="
				+ elder_gender + ", elder_final_service_approval_level=" + elder_final_service_approval_level
				+ ", elder_final_service_status=" + elder_final_service_status + ", elder_disease=" + elder_disease
				+ ", elder_coinsurance=" + elder_coinsurance + ", total_visits=" + total_visits
				+ ", total_service_time=" + total_service_time + ", service_localmanage_code="
				+ service_localmanage_code + ", service_localmanage_name=" + service_localmanage_name
				+ ", long_term_care_numver=" + long_term_care_numver + ", long_term_care_validity="
				+ long_term_care_validity + ", elder_phone=" + elder_phone + ", elder_adress_for_stay="
				+ elder_adress_for_stay + ", elder_adress_for_post=" + elder_adress_for_post + ", elder_remarks="
				+ elder_remarks + ", elder_groups=" + elder_groups + "]";
	}                   
}
