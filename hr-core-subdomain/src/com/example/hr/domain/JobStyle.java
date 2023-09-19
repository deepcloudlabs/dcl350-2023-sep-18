package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public enum JobStyle {
	FULL_TIME(100), PART_TIME(110), INTERN(200), FREELANCE(300);
	private final int SgkCode;

	private JobStyle(int sgkCode) {
		SgkCode = sgkCode;
	}

	public int getSgkCode() {
		return SgkCode;
	}
	
}
