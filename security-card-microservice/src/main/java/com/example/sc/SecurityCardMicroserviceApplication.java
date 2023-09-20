package com.example.sc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecurityCardMicroserviceApplication implements ApplicationRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityCardMicroserviceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			var restTemplate = new RestTemplate();
			var response = restTemplate.getForEntity("http://localhost:7400/hr/api/v1/employees/11111111110", String.class).getBody();
			System.out.println(response);			
		}catch (Throwable e) {
			System.err.println(e.getMessage());
		}
	}

}
