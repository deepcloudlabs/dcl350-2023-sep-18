package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(name = "cslb", havingValue = "manual")
public class LotteryConsumerService {

	private String URL = "http://%s:%d/api/v1/numbers?column=5";
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private final AtomicInteger instanceCount = new AtomicInteger();

	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	@Scheduled(fixedRate = 60_000)
	public void retrieveInstances() {
		instances = discoveryClient.getInstances("lottery");
	}

	@Scheduled(fixedRate = 3_000)
	public void consumeLotteryService() {
		var restTemplate = new RestTemplate();
		var instance = instances.get(instanceCount.getAndIncrement() % instances.size());
		var host = instance.getHost();
		var port = instance.getPort();
		var restServiceUrl = URL.formatted(host, port);
		System.err.println("Sending a get request to %s".formatted(restServiceUrl));
		try {
			var response = restTemplate.getForEntity(restServiceUrl, String.class).getBody();
			System.err.println("Response: %s".formatted(response));
		} catch (Throwable t) {
			System.err.println("Service has failed to send a response!");
			retrieveInstances();
		}
	}
}
