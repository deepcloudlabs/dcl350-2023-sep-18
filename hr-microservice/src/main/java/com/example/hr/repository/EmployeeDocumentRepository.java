package com.example.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String>{
	List<EmployeeDocument> findAllByJobStyle(JobStyle jobStyle);
	List<EmployeeDocument> findAllByBirthYearBetween(int fromBirthYear,int toBirthYear);
	List<EmployeeDocument> findAllByJobStyleAndDepartmentsIn(JobStyle jobStyle,List<Department> departments);

}
