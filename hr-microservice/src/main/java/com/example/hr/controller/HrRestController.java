package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.heaxgon.Adapter;
import com.example.hr.application.HrApplication;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

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
	public EmployeeResponse getEmployee(@PathVariable @TcKimlikNo String identityNo){
		System.err.println(hrService.getClass().getName());
		return hrService.findById(identityNo);
	}
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request){
		return hrService.addEmployee(request);		
	}
	
	@DeleteMapping("{identityNo}")
	public EmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identityNo){
		return hrService.removeEmployee(identityNo);
	}
	
}
