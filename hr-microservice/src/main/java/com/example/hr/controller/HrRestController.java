package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.heaxgon.Adapter;
import com.example.hr.application.HrApplication;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
@Validated
@Adapter(port = HrApplication.class)
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("{identityNo}")
	public EmployeeResponse getEmployee(@PathVariable String identityNo){
		return hrService.findById(identityNo);
	}
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request){
		return hrService.addEmployee(request);		
	}
	
	@DeleteMapping("{identityNo}")
	public EmployeeResponse fireEmployee(@PathVariable String identityNo){
		return hrService.removeEmployee(identityNo);
	}
}
