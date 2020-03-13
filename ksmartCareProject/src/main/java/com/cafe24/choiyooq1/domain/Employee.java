package com.cafe24.choiyooq1.domain;

public class Employee {
	
	private String employee_id;
	private String center_code;
	private String center_name;
	private String employee_name;
	private String employee_pw;
	private String employee_category;
	private String employee_gender;
	private String employee_personal_number;
	private String employee_phone;
	private String employee_start_date;
	private String employee_working_status;
	private String employee_working_type;
	private String employee_position;
	private String employee_pay_type;
	private String employee_hourly_pay;
	private String employee_account;
	private String employee_account_holder;
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
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
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_pw() {
		return employee_pw;
	}
	public void setEmployee_pw(String employee_pw) {
		this.employee_pw = employee_pw;
	}
	public String getEmployee_category() {
		return employee_category;
	}
	public void setEmployee_category(String employee_category) {
		this.employee_category = employee_category;
	}
	public String getEmployee_gender() {
		return employee_gender;
	}
	public void setEmployee_gender(String employee_gender) {
		this.employee_gender = employee_gender;
	}
	public String getEmployee_personal_number() {
		return employee_personal_number;
	}
	public void setEmployee_personal_number(String employee_personal_number) {
		this.employee_personal_number = employee_personal_number;
	}
	public String getEmployee_phone() {
		return employee_phone;
	}
	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}
	public String getEmployee_start_date() {
		return employee_start_date;
	}
	public void setEmployee_start_date(String employee_start_date) {
		this.employee_start_date = employee_start_date;
	}
	public String getEmployee_working_status() {
		return employee_working_status;
	}
	public void setEmployee_working_status(String employee_working_status) {
		this.employee_working_status = employee_working_status;
	}
	public String getEmployee_working_type() {
		return employee_working_type;
	}
	public void setEmployee_working_type(String employee_working_type) {
		this.employee_working_type = employee_working_type;
	}
	public String getEmployee_position() {
		return employee_position;
	}
	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}
	public String getEmployee_pay_type() {
		return employee_pay_type;
	}
	public void setEmployee_pay_type(String employee_pay_type) {
		this.employee_pay_type = employee_pay_type;
	}
	public String getEmployee_hourly_pay() {
		return employee_hourly_pay;
	}
	public void setEmployee_hourly_pay(String employee_hourly_pay) {
		this.employee_hourly_pay = employee_hourly_pay;
	}
	public String getEmployee_account() {
		return employee_account;
	}
	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	public String getEmployee_account_holder() {
		return employee_account_holder;
	}
	public void setEmployee_account_holder(String employee_account_holder) {
		this.employee_account_holder = employee_account_holder;
	}
	
	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", center_code=" + center_code + ", center_name=" + center_name
				+ ", employee_name=" + employee_name + ", employee_pw=" + employee_pw + ", employee_category="
				+ employee_category + ", employee_gender=" + employee_gender + ", employee_personal_number="
				+ employee_personal_number + ", employee_phone=" + employee_phone + ", employee_start_date="
				+ employee_start_date + ", employee_working_status=" + employee_working_status
				+ ", employee_working_type=" + employee_working_type + ", employee_position=" + employee_position
				+ ", employee_pay_type=" + employee_pay_type + ", employee_hourly_pay=" + employee_hourly_pay
				+ ", employee_account=" + employee_account + ", employee_account_holder=" + employee_account_holder
				+ "]";
	}                 
}
