package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;
	
	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse findById(String identityNo) {
		TcKimlikNo kimlikNo = TcKimlikNo.valueOf(identityNo);
		var employee = hrApplication.findEmployeeByIdentityNo(kimlikNo);
		Employee foundEmployee = employee.orElseThrow(() -> new EmployeeNotFoundException("Cannot find the employee", kimlikNo));
		return modelMapper.map(foundEmployee, EmployeeResponse.class);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public HireEmployeeResponse addEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var hiredEmployee = hrApplication.hireEmployee(employee);
		return modelMapper.map(hiredEmployee, HireEmployeeResponse.class);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public EmployeeResponse removeEmployee(String identityNo) {
		var kimlikNo = TcKimlikNo.valueOf(identityNo);
		var firedEmployee = hrApplication.fireEmployee(kimlikNo);
		return modelMapper.map(firedEmployee, EmployeeResponse.class);
	}

}
