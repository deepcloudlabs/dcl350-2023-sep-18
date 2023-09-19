package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends HrEvent {

	public EmployeeHiredEvent(TcKimlikNo identityNo) {
		super(identityNo);
	}
	
}
