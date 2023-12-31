package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerDocumentReactiveRepository extends ReactiveMongoRepository<CustomerDocument, String> {
	Mono<CustomerDocument> findOneByEmail(String email);

	Flux<CustomerDocument> findAllByAddressesCity(String city);
	
	@Query("{}")
	Flux<CustomerDocument> findAll(PageRequest page);
}
