package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "cslb", havingValue = "feign")
public class LotteryConsumerFeignClientService {
	private final LotteryService lotteryService;

	public LotteryConsumerFeignClientService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void consumeLotteryService() {
		System.err.println("Response: %s".formatted(lotteryService.draw(5)));
	}
}
