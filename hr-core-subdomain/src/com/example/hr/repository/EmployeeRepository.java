package com.example.hr.repository;

import java.util.Optional;

import com.example.heaxgon.Port;
import com.example.heaxgon.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVEN_PORT)
public interface EmployeeRepository {

	Optional<Employee> getEmployeeByIdentity(TcKimlikNo kimlikNo);

	boolean existsByIdentity(TcKimlikNo kimlikNo);

	Employee createEmployee(Employee employee);

	Employee removeEmployee(Employee firedEmployee);

}
