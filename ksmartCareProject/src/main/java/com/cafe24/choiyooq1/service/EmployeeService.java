package com.cafe24.choiyooq1.service;

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
	
}
