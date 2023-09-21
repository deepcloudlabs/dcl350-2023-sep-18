package com.example.hr.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "eventPlatform", havingValue = "rabbit-websocket")
public class RabbitHrEventPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;
	private final String hrEventsExchangeName;
	
	public RabbitHrEventPublisher(RabbitTemplate rabbitTemplate, 
			@Value("${hrEventsExchangeName}") String hrEventsExchangeName,
			ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
		this.hrEventsExchangeName = hrEventsExchangeName;
	}
	
	@EventListener
	public void handleHrEvent(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend(hrEventsExchangeName, null, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting java object to json: %s".formatted(e.getMessage()));
		}			
	}
}
