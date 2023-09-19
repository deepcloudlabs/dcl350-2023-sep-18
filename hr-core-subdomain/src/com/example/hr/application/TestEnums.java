package com.example.hr.application;

import com.example.hr.domain.JobStyle;

public class TestEnums {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		for (var jobStyle : JobStyle.values()) {
			System.out.println("%s: %d,%d".formatted(jobStyle.name(),jobStyle.ordinal(),jobStyle.getSgkCode()));
		}
		var jobStyle = JobStyle.valueOf("PART__TIME"); 
	}

}
