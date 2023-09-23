package com.example.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@Service
public class BusinessService {
	private final ExternalService externalService;
	
	public BusinessService(ExternalService externalService) {
		this.externalService = externalService;
	}

	@Scheduled(fixedRate = 10_000)
	public void callExternalService() {
		System.err.println("Received value: %d".formatted(externalService.fun()));
		externalService.sun().thenAccept(System.err::println);

	}

	@Bulkhead(type = Type.SEMAPHORE, name = "run")
	public List<Integer> run() {
		// TODO Auto-generated method stub
		return null;
	}
}
