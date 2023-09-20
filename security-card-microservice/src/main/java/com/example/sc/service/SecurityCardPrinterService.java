package com.example.sc.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardPrinterService {
	@KafkaListener(topics = "hr-events", groupId = "security-card")
	public void handleHrEvents(String event) {
		System.err.println("New hr event has arrived: %s.".formatted(event));
	}
}
