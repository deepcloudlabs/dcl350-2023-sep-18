package com.example.hr.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class HrEvent {
	private final String eventId = UUID.randomUUID().toString();
	private final ZonedDateTime timestamp = ZonedDateTime.now();
	private final TcKimlikNo identityNo;

	public HrEvent(TcKimlikNo identityNo) {
		this.identityNo = identityNo;
	}

	public String getEventId() {
		return eventId;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public TcKimlikNo getIdentityNo() {
		return identityNo;
	}

}
