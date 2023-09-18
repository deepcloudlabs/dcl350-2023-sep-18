package com.example.hr.domain;

import java.util.List;
// Sub-Domain (Core/Supporting Sub-domain)
// Problem Space --> Solution Space
// Sub-Domain    --> Bounded-Context
// Ubiquitous Language -> Bounded-Context

public class Employee {
	private TcKimlikNo identityNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private List<Department> departments;
	private JobStyle style;
	private Photo photo;
	private Email email;
	private BirthYear birthYear;
}
