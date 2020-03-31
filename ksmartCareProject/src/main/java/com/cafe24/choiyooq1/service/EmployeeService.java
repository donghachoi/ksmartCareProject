package com.cafe24.choiyooq1.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Employee;
import com.cafe24.choiyooq1.mapper.EmployeeMapper;

@Service
@Transactional
public class EmployeeService {

	@Autowired EmployeeMapper employeeMapper;
	
	//직원등록 메서드
	public int employeeInsert(Employee employee,HttpSession session) {
		String centerCode=(String) session.getAttribute("SCENTERCODE");
		String centerName=(String) session.getAttribute("SCENTERNAME");
		
		employee.setCenterCode(centerCode);
		employee.setCenterName(centerName);
		
		
		return employeeMapper.employeeInsert(employee);
	}
	
	//직원 리스트
	public List<Employee> getEmployeeList(){
		return employeeMapper.getEmployeeList();
		
	}
	
	//직원 정보 수정을 위한 불러오기
	public Employee employeeSelectForUpdate(String employeeId) {
		return employeeMapper.employeeSelectForUpdate(employeeId);		
	}
	
	//직원 정보 수정 메서드
	public int employeeUpdate(Employee employee) {
		return employeeMapper.employeeUpdate(employee);
	}
	
	//직원 삭제 메서드
	public int employeeDelete(String employeeId) {
		return employeeMapper.employeeDelete(employeeId);
	}

	
}
