package com.example.hr.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "eventPlatform", havingValue = "rabbit-websocket")
public class HrEventWebSocketPublisherService implements WebSocketHandler {
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;

	public HrEventWebSocketPublisherService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void handleHrEvent(HrEvent event) throws JsonProcessingException {
		var eventAsJson = objectMapper.writeValueAsString(event);
		sessions.forEach((sessionId, session) -> {
			try {
				session.sendMessage(new TextMessage(eventAsJson));
			} catch (IOException e) {
				System.err.println("Error has occured while sending the event: %s".formatted(e.getMessage()));
			}
		});
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		var sessionId = session.getId();
		System.err.println("New session is open: %s.".formatted(sessionId));
		sessions.put(sessionId, session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err
				.println("An error has occured at session (%s): %s".formatted(session.getId(), exception.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		var sessionId = session.getId();
		sessions.remove(sessionId);
		System.err.println("The session is closed: %s.".formatted(sessionId));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
