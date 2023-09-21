package com.example.hr.document;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Data
@Document(collection = "employees")
public class EmployeeDocument {
	@Id
	@TcKimlikNo
	private String identityNo;
	@Field(name = "fname")
	@NotBlank
	private String firstName;
	@Column(name = "lname")
	@NotBlank
	private String lastName;
	@Iban
	@Indexed(unique = true)
	private String iban;
	private double salary;
	private FiatCurrency currency;
	@Field(name = "byear")
	private int birthYear;
	@Field
	private String photo;
	private List<Department> departments;
	private JobStyle jobStyle;
}
