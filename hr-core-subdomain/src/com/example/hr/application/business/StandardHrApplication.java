package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	
	public StandardHrApplication(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
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
		return employeeRepository.createEmployee(employee);
	}

	@Override
	public Employee fireEmployee(TcKimlikNo kimlikNo) {
		var employee = employeeRepository.getEmployeeByIdentity(kimlikNo);
		var firedEmployee = employee.orElseThrow(() -> new EmployeeNotFoundException("Employee to fire does not exists.",kimlikNo) );
		return employeeRepository.removeEmployee(firedEmployee);
	}

}
