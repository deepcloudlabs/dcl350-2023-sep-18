package com.example.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfig {
	@Bean("my-pool")
	ExecutorService exec1(@Value("${poolSize}")int poolSize) {
		return Executors.newFixedThreadPool(poolSize);
	}
}
