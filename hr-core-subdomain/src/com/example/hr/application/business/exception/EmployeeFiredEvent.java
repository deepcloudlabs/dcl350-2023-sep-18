package com.example.hr.application.business.exception;

import com.example.hr.domain.Employee;
import com.example.hr.domain.event.HrEvent;

public class EmployeeFiredEvent extends HrEvent {

	private final Employee firedEmployee;

	public EmployeeFiredEvent(Employee firedEmployee) {
		super(firedEmployee.getIdentityNo());
		this.firedEmployee = firedEmployee;
	}

	public Employee getFiredEmployee() {
		return firedEmployee;
	}

}
