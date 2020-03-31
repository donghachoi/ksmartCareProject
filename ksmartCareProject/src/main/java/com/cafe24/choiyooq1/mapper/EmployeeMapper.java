package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Employee;

@Mapper
public interface EmployeeMapper {

	//직원등록
	public int employeeInsert(Employee employee);
	
	//직원리스트
	public List<Employee> getEmployeeList();
	
	//직원정보 수정을 위한 불러오기
	public Employee employeeSelectForUpdate(String employeeId);
	
	//직원정보 수정
	public int employeeUpdate(Employee employee);
	
	//직원정보 삭제
	public int employeeDelete(String employeeId);
}
