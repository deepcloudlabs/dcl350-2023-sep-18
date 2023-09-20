package com.example.hr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "eventPlatform", havingValue = "kafka-websocket")
public class KafkaHrEventPublisher {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String hrEventsTopicName;
	
	public KafkaHrEventPublisher(KafkaTemplate<String, String> kafkaTemplate, 
			@Value("${hrEventsTopicName}") String hrEventsTopicName,
			ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.hrEventsTopicName = hrEventsTopicName;
	}
	@EventListener
	public void handleHrEvent(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(hrEventsTopicName, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting java object to json: %s".formatted(e.getMessage()));
		}			
	}
}
