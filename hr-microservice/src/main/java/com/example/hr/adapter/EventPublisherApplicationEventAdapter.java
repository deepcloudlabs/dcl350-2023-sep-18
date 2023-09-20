package com.example.hr.adapter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.heaxgon.Adapter;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;

@Service
@Adapter(port = EventPublisher.class)
@ConditionalOnProperty(name="eventPlatform", havingValue = "kafka-websocket")
public class EventPublisherApplicationEventAdapter implements EventPublisher<HrEvent> {
	private final ApplicationEventPublisher eventPublisher;

	public EventPublisherApplicationEventAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publishEvent(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
