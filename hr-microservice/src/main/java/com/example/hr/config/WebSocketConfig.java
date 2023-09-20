package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.HrEventWebSocketPublisherService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	private final HrEventWebSocketPublisherService hrEventWebSocketPublisherService;

	public WebSocketConfig(HrEventWebSocketPublisherService hrEventWebSocketPublisherService) {
		this.hrEventWebSocketPublisherService = hrEventWebSocketPublisherService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(hrEventWebSocketPublisherService, "/events")
		        .setAllowedOrigins("*");
	}

}
