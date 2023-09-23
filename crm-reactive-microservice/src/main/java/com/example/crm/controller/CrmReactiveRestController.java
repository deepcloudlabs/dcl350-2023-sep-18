package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CrmReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CrmReactiveRestController {
	private final CrmReactiveService crmReativeService;

	public CrmReactiveRestController(CrmReactiveService crmReativeService) {
		this.crmReativeService = crmReativeService;
	}

	// GET /customers/11111111110
	@GetMapping("{identity}")
	public Mono<CustomerDocument> getCustomerById(@PathVariable String identity) {
		return crmReativeService.findCustomerByIdentity(identity);
	}

	// GET /customers?pageSize=20&pageNo=12
	@GetMapping(params = { "pageSize", "pageNo" })
	public Flux<CustomerDocument> getCustomersByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		return crmReativeService.findCustomers(pageNo, pageSize);
	}
	
	@PostMapping
	public Mono<CustomerDocument> acquireCustomer(@RequestBody CustomerDocument customer){
		return crmReativeService.acquireCustomer(customer);		
	}
	
	@PutMapping("{identity}")
	public Mono<CustomerDocument> updateCustomer(@PathVariable String identity,@RequestBody CustomerDocument customer){
		return crmReativeService.updateCustomer(customer);		
	}
	
	@DeleteMapping("{identity}")
	public Mono<CustomerDocument> releaseCustomerById(@PathVariable String identity) {
		return crmReativeService.releaseCustomerByIdentity(identity);
	}
}
