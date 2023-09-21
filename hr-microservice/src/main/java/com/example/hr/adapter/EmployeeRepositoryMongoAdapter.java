package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.heaxgon.Adapter;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
@ConditionalOnProperty(name="persistencePlatform", havingValue = "mongodb")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository employeeDocumentRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository employeeDocumentRepository, ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
		System.err.println("EmployeeRepositoryMongoAdapter is created.");
	}

	@Override
	public Optional<Employee> getEmployeeByIdentity(TcKimlikNo kimlikNo) {
		var employeeDocument = employeeDocumentRepository.findById(kimlikNo.getValue());
		return employeeDocument.map(document -> modelMapper.map(document, Employee.class));
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo kimlikNo) {
		return employeeDocumentRepository.existsById(kimlikNo.getValue());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Employee createEmployee(Employee employee) {
		var employeeDocument = modelMapper.map(employee, EmployeeDocument.class);
		var savedEmployeeDocument =employeeDocumentRepository.save(employeeDocument);
		return modelMapper.map(savedEmployeeDocument, Employee.class);
	}

	@Override
	@Transactional
	public Employee removeEmployee(Employee employee) {
		String identityNo=employee.getIdentityNo().getValue();
		var firedEmployeeDocument = employeeDocumentRepository.findById(identityNo);
		EmployeeDocument document = firedEmployeeDocument.get();
		employeeDocumentRepository.delete(document);
		return modelMapper.map(document,Employee.class);
	}

}
