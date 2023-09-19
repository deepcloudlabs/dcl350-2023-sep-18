package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeFiredEvent;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.event.EmployeeHiredEvent;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEvent> eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentityNo(TcKimlikNo kimlikNo) {
		return employeeRepository.getEmployeeByIdentity(kimlikNo);
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var kimlikNo = employee.getIdentityNo();
		if (employeeRepository.existsByIdentity(kimlikNo))
			throw new ExistingEmployeeException("Employee to hire already exists.",kimlikNo);
		var persistedEmployee = employeeRepository.createEmployee(employee);
		var event = new EmployeeHiredEvent(kimlikNo);
		eventPublisher.publishEvent(event);
		return persistedEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo kimlikNo) {
		var employee = employeeRepository.getEmployeeByIdentity(kimlikNo);
		var firedEmployee = employee.orElseThrow(() -> new EmployeeNotFoundException("Employee to fire does not exists.",kimlikNo) );
		Employee removedEmployee = employeeRepository.removeEmployee(firedEmployee);
		var event = new EmployeeFiredEvent(removedEmployee);
		eventPublisher.publishEvent(event);		
		return removedEmployee;
	}

}
