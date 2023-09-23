package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class ExternalService {

	// @Retry(name = "fun",fallbackMethod = "funFallback")
	@RateLimiter(name = "fun")
	@CircuitBreaker(name="fun")
	public int fun() {
//		if(ThreadLocalRandom.current().nextBoolean())
//			throw new IllegalStateException("Cannot connect to the server");
		return 42;
	}

	public int funFallback(Throwable t) {
		System.err.println("Fallback method is running...");
		return 108;
	}

	@Async
	@TimeLimiter(name = "sun", fallbackMethod = "sunFallback")
	public CompletableFuture<Integer> sun() { // asynchronous
		return CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 6));
			} catch (Exception e) {
			}
			return 549;
		});
	}

	public CompletableFuture<Integer> sunFallback(Throwable t) {
		System.err.println("sunFallback is running...");
		return CompletableFuture.supplyAsync(() -> {
			return 3615;
		});
	}

}
