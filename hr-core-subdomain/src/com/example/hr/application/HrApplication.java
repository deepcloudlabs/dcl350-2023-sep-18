package com.example.hr.application;

import java.util.Optional;

import com.example.heaxgon.Port;
import com.example.heaxgon.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVING_PORT)
public interface HrApplication {
	Optional<Employee> findEmployeeByIdentityNo(TcKimlikNo kimlikNo);
	Employee hireEmployee(Employee employee);
	Employee fireEmployee(TcKimlikNo kimlikNo);
}
