package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerDocumentReactiveRepository customerDocumentReactiveRepository;
	
	public CrmReactiveService(CustomerDocumentReactiveRepository customerDocumentReactiveRepository) {
		this.customerDocumentReactiveRepository = customerDocumentReactiveRepository;
	}

	public Mono<CustomerDocument> findCustomerByIdentity(String identity) {
		return customerDocumentReactiveRepository.findById(identity);
	}

	public Flux<CustomerDocument> findCustomers(int pageNo, int pageSize) {
		return customerDocumentReactiveRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> acquireCustomer(CustomerDocument customer) {
		return customerDocumentReactiveRepository.insert(customer);
	}

	public Mono<CustomerDocument> updateCustomer(CustomerDocument customer) {
		return customerDocumentReactiveRepository.save(customer);
	}

	public Mono<CustomerDocument> releaseCustomerByIdentity(String identity) {
		return customerDocumentReactiveRepository.findById(identity)
                              .doOnNext( customer -> {
                            	  customerDocumentReactiveRepository.delete(customer)
                            	                                    .subscribe((status) -> {
                            	                                    	System.err.println("Customer is deleted: %s".formatted(status));
                            	                                    }); 
                              });
	}

}
