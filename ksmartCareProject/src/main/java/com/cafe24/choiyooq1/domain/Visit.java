package com.cafe24.choiyooq1.domain;

public class Visit {

	private String visit_code;
	private String center_code;
	private String center_name;
	private String elder_id;
	private String elder_name;
	private String employee_id;
	private String employee_name;
	private String visit_plan_date;
	private String visit_plan_time;
	private int visit_service_time;
	private String family_relation;
	private String visit_service_category;
	private String monthly_claim_group_code;
	
	public String getVisit_code() {
		return visit_code;
	}
	public void setVisit_code(String visit_code) {
		this.visit_code = visit_code;
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
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getVisit_plan_date() {
		return visit_plan_date;
	}
	public void setVisit_plan_date(String visit_plan_date) {
		this.visit_plan_date = visit_plan_date;
	}
	public String getVisit_plan_time() {
		return visit_plan_time;
	}
	public void setVisit_plan_time(String visit_plan_time) {
		this.visit_plan_time = visit_plan_time;
	}
	public int getVisit_service_time() {
		return visit_service_time;
	}
	public void setVisit_service_time(int visit_service_time) {
		this.visit_service_time = visit_service_time;
	}
	public String getFamily_relation() {
		return family_relation;
	}
	public void setFamily_relation(String family_relation) {
		this.family_relation = family_relation;
	}
	public String getVisit_service_category() {
		return visit_service_category;
	}
	public void setVisit_service_category(String visit_service_category) {
		this.visit_service_category = visit_service_category;
	}
	public String getMonthly_claim_group_code() {
		return monthly_claim_group_code;
	}
	public void setMonthly_claim_group_code(String monthly_claim_group_code) {
		this.monthly_claim_group_code = monthly_claim_group_code;
	}
	@Override
	public String toString() {
		return "Visit [visit_code=" + visit_code + ", center_code=" + center_code + ", center_name=" + center_name
				+ ", elder_id=" + elder_id + ", elder_name=" + elder_name + ", employee_id=" + employee_id
				+ ", employee_name=" + employee_name + ", visit_plan_date=" + visit_plan_date + ", visit_plan_time="
				+ visit_plan_time + ", visit_service_time=" + visit_service_time + ", family_relation="
				+ family_relation + ", visit_service_category=" + visit_service_category + ", monthly_claim_group_code="
				+ monthly_claim_group_code + "]";
	}      
}
