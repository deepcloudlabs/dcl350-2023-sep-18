package com.example.sc.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class HrEventWebSocketClientService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;

	public HrEventWebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.doHandshake(this, "ws://localhost:7400/hr/api/v1/events");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the server!");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New message has arrived from websocket server: %s.".formatted(message.getPayload()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err
				.println("An error has occured at session (%s): %s".formatted(session.getId(), exception.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("The session is closed: %s.".formatted(session.getId()));

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
