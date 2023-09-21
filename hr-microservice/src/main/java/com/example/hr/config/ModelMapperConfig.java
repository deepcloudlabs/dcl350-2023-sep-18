package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee, HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER =
			context -> {
				var employee = context.getSource();
				var response = new HireEmployeeResponse();
				response.setIdentityNo(employee.getIdentityNo().getValue());
				response.setFirstName(employee.getFullname().firstName());
				response.setLastName(employee.getFullname().lastName());
				return response;
			}; 
	private static final Converter<Employee, EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =
		context -> {
			var employee = context.getSource();
			var response = new EmployeeResponse();
			response.setIdentityNo(employee.getIdentityNo().getValue());
			response.setFirstName(employee.getFullname().firstName());
			response.setLastName(employee.getFullname().lastName());
			response.setIban(employee.getIban().getValue());
			response.setSalary(employee.getSalary().getValue());
			response.setCurrency(employee.getSalary().getCurrency());
			response.setDepartments(employee.getDepartments());
			response.setPhoto(employee.getPhoto().getBase64Values());
			response.setJobStyle(employee.getStyle());
			response.setBirthYear(employee.getBirthYear().value());
			return response;
		}; 
		private static final Converter<Employee, EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER =
				context -> {
					var employee = context.getSource();
					var entity = new EmployeeEntity();
					entity.setIdentityNo(employee.getIdentityNo().getValue());
					entity.setFirstName(employee.getFullname().firstName());
					entity.setLastName(employee.getFullname().lastName());
					entity.setIban(employee.getIban().getValue());
					entity.setSalary(employee.getSalary().getValue());
					entity.setCurrency(employee.getSalary().getCurrency());
					entity.setDepartments(employee.getDepartments());
					entity.setPhoto(employee.getPhoto().getValues());
					entity.setJobStyle(employee.getStyle());
					entity.setBirthYear(employee.getBirthYear().value());
					return entity;
				};	
		private static final Converter<Employee, EmployeeDocument> EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER =
				context -> {
					var employee = context.getSource();
					var document = new EmployeeDocument();
					document.setIdentityNo(employee.getIdentityNo().getValue());
					document.setFirstName(employee.getFullname().firstName());
					document.setLastName(employee.getFullname().lastName());
					document.setIban(employee.getIban().getValue());
					document.setSalary(employee.getSalary().getValue());
					document.setCurrency(employee.getSalary().getCurrency());
					document.setDepartments(employee.getDepartments());
					document.setPhoto(employee.getPhoto().getBase64Values());
					document.setJobStyle(employee.getStyle());
					document.setBirthYear(employee.getBirthYear().value());
					return document;
				};		
				
		private static final Converter<HireEmployeeRequest, Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =
				context -> {
					var request = context.getSource();
					return new Employee.Builder(TcKimlikNo.valueOf(request.getIdentityNo()))
					                   .fullname(request.getFirstName(),request.getLastName())
					                   .iban(request.getIban())
					                   .salary(request.getSalary(),request.getCurrency())
					                   .departments(request.getDepartments().stream().map(Department::name).toList().toArray(new String[0]))
					                   .style(request.getJobStyle())
					                   .photo(request.getPhoto())
					                   .birthYear(request.getBirthYear())
					                   .build();
				}; 	

		private static final Converter<EmployeeEntity, Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER =
				context -> {
					var entity = context.getSource();
					return new Employee.Builder(TcKimlikNo.valueOf(entity.getIdentityNo()))
					                   .fullname(entity.getFirstName(),entity.getLastName())
					                   .iban(entity.getIban())
					                   .salary(entity.getSalary(),entity.getCurrency())
					                   .departments(entity.getDepartments().stream().map(Department::name).toList().toArray(new String[0]))
					                   .style(entity.getJobStyle())
					                   .photo(entity.getPhoto())
					                   .birthYear(entity.getBirthYear())
					                   .build();
				}; 	
		private static final Converter<EmployeeDocument, Employee> EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER =
				context -> {
					var document = context.getSource();
					return new Employee.Builder(TcKimlikNo.valueOf(document.getIdentityNo()))
							.fullname(document.getFirstName(),document.getLastName())
							.iban(document.getIban())
							.salary(document.getSalary(),document.getCurrency())
							.departments(document.getDepartments().stream().map(Department::name).toList().toArray(new String[0]))
							.style(document.getJobStyle())
							.photo(document.getPhoto())
							.birthYear(document.getBirthYear())
							.build();
				}; 	
				
	@Bean
	ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER, EmployeeDocument.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER, Employee.class, EmployeeDocument.class);
		return modelMapper;
	}
}
