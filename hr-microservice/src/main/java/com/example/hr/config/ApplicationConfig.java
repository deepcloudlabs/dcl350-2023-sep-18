package com.example.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class ApplicationConfig {

	@Bean
	HrApplication createHrApplication(
			EmployeeRepository employeeRepository, // EmployeeRepositoryJpaAdapter
			EventPublisher<HrEvent> eventPublisher // EventPublisherKafkaAdapter
	) {
		return new StandardHrApplication(employeeRepository, eventPublisher);
	}
}
