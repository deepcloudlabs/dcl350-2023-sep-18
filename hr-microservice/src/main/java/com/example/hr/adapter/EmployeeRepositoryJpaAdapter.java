package com.example.hr.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.heaxgon.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeEntityRespoitory;
import com.example.hr.repository.EmployeeRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRespoitory employeeEntityRespoitory;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRespoitory employeeEntityRespoitory) {
		this.employeeEntityRespoitory = employeeEntityRespoitory;
	}

	@Override
	public Optional<Employee> getEmployeeByIdentity(TcKimlikNo kimlikNo) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo kimlikNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Employee removeEmployee(Employee firedEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

}
