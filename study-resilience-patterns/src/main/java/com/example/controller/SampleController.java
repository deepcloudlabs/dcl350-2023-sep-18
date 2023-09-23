package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.BusinessService;

@RestController
@RequestMapping
public class SampleController {
	private final BusinessService businessService;
	
	public SampleController(BusinessService businessService) {
		this.businessService = businessService;
	}

	@GetMapping
	public List<Integer> fun(){
		return businessService.run();
	}
}
