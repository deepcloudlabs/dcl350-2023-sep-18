package com.example.hr.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
public class HrService {
	private final HrApplication hrApplication;
	
	public HrService(HrApplication hrApplication) {
		this.hrApplication = hrApplication;
	}

	@Cacheable(cacheNames = "employees", key = "#identityNo")
	public EmployeeResponse findById(String identityNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public HireEmployeeResponse addEmployee(HireEmployeeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public EmployeeResponse removeEmployee(String identityNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
