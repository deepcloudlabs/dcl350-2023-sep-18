package com.example.hr.dto.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Data
public class HireEmployeeRequest {
	@TcKimlikNo
	private String identityNo;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Iban
	private String iban;
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Max(2006)
	private int birthYear;
	private String photo;
	@NotNull
	private List<Department> departments;
	@NotNull
	private JobStyle jobStyle;
}
