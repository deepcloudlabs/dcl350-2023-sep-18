package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.heaxgon.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> getEmployeeByIdentity(TcKimlikNo kimlikNo) {
		var employeeEntity = employeeEntityRepository.findById(kimlikNo.getValue());
		return employeeEntity.map(entity -> modelMapper.map(entity, Employee.class));
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo kimlikNo) {
		return employeeEntityRepository.existsById(kimlikNo.getValue());
	}

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) {
		System.err.println(employee);
		var employeeJpaEntity = modelMapper.map(employee, EmployeeEntity.class);
		var savedEmployeeJpaEntity =employeeEntityRepository.save(employeeJpaEntity);
		return modelMapper.map(savedEmployeeJpaEntity, Employee.class);
	}

	@Override
	@Transactional
	public Employee removeEmployee(Employee employee) {
		String identityNo=employee.getIdentityNo().getValue();
		var firedEmployeeEntity = employeeEntityRepository.findById(identityNo);
		EmployeeEntity entity = firedEmployeeEntity.get();
		employeeEntityRepository.delete(entity);
		return modelMapper.map(entity,Employee.class);
	}

}
