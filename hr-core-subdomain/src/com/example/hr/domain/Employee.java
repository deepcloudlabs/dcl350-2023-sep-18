package com.example.hr.domain;

import java.util.List;

import com.example.ddd.DomainEntity;
// Sub-Domain (Core/Supporting Sub-domain)
// Problem Space --> Solution Space
// Sub-Domain    --> Bounded-Context
// Ubiquitous Language -> Bounded-Context
// Entity -> i) Persistent ii) Identity iii) Mutable iv) Business Method
@DomainEntity(identity= "identityNo")
public class Employee {
	private TcKimlikNo identityNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private List<Department> departments;
	private JobStyle style;
	private Photo photo;
	private BirthYear birthYear;
	
	public Employee(TcKimlikNo identityNo, FullName fullname, Iban iban, Money salary, List<Department> departments,
			JobStyle style, Photo photo, BirthYear birthYear) {
		this.identityNo = identityNo;
		this.fullname = fullname;
		this.iban = iban;
		this.salary = salary;
		this.departments = departments;
		this.style = style;
		this.photo = photo;
		this.birthYear = birthYear;
		// Validation
		// Business Rule
		// Policy
		// Invariants
	}
	
}
