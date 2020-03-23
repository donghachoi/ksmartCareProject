package com.cafe24.choiyooq1.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Employee;

@Mapper
public interface EmployeeMapper {

	//직원등록
	public int employeeInsert(Employee employee);
}
