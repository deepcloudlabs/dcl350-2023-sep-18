package com.example.hr.application.business.exception;

import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	private final TcKimlikNo kimlikNo;

	public EmployeeNotFoundException(String message, TcKimlikNo kimlikNo) {
		super(message);
		this.kimlikNo = kimlikNo;
	}

	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}

}
