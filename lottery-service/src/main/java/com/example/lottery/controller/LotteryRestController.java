package com.example.lottery.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lottery.service.LotteryService;

@RestController
//@RequestScope
@RequestMapping("/numbers")
@CrossOrigin
@Validated
public class LotteryRestController {

	private final LotteryService lotteryService;
	private final int serverPort;

	public LotteryRestController(LotteryService lotteryService,@Value("${server.port}") int serverPort) {
		System.err.println(lotteryService.getClass().getName());
		this.lotteryService = lotteryService;
		this.serverPort = serverPort;
	}

	@GetMapping(params="column")
	List<List<Integer>> getLotteryNumbers(@RequestParam @Min(5) int column){
		System.err.println("New GET request to localhost: %d".formatted(serverPort));
		return lotteryService.draw(column);
	}
}
