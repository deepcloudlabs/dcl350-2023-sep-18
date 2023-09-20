package com.example.hr.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.ddd.DomainEntity;

// Sub-Domain (Core/Supporting Sub-domain)
// Problem Space --> Solution Space
// Sub-Domain    --> Bounded-Context
// Ubiquitous Language -> Bounded-Context
// Entity -> i) Persistent ii) Identity iii) Mutable iv) Business Method
@DomainEntity(identity = "identityNo", aggregate = true)
public class Employee {
	private TcKimlikNo identityNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private List<Department> departments;
	private JobStyle style;
	private Photo photo;
	private BirthYear birthYear;

	public Employee(Builder builder) {
		this.identityNo = builder.identityNo;
		this.fullname = builder.fullname;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.departments = builder.departments;
		this.style = builder.style;
		this.photo = builder.photo;
		this.birthYear = builder.birthYear;
	}

	public TcKimlikNo getIdentityNo() {
		return identityNo;
	}

	public FullName getFullname() {
		return fullname;
	}

	public Iban getIban() {
		return iban;
	}

	public Money getSalary() {
		return salary;
	}

	public List<Department> getDepartments() {
		return List.copyOf(departments);
	}

	public JobStyle getStyle() {
		return style;
	}

	public Photo getPhoto() {
		return photo;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identityNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(identityNo, other.identityNo);
	}



	@Override
	public String toString() {
		return "Employee [identityNo=" + identityNo + ", fullname=" + fullname + ", iban=" + iban + ", salary=" + salary
				+ ", departments=" + departments + ", style=" + style + ", photo=" + photo.getBase64Values() + ", birthYear=" + birthYear
				+ "]";
	}



	public static class Builder {
		private final TcKimlikNo identityNo;
		private FullName fullname;
		private Iban iban;
		private Money salary;
		private List<Department> departments;
		private JobStyle style;
		private Photo photo;
		private BirthYear birthYear;

		public Builder(TcKimlikNo identityNo) {
			this.identityNo = identityNo;
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = FullName.of(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.valueOf(value, currency);
			return this;
		}

		public Builder departments(String... departments) {
			this.departments = new ArrayList<>(List.of(departments).stream().map(Department::valueOf).toList());
			return this;
		}

		public Builder departments(Department... departments) {
			this.departments = new ArrayList<>(List.of(departments));
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder photo(String values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder style(JobStyle style) {
			this.style = style;
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Employee build() {
			// Validation Rule
			// Business Rule
			if (departments.stream().anyMatch(Department.IT::equals) && salary.lessThan(Money.valueOf(36_000))) {
			   	throw new IllegalStateException("Violates BR-100");
			}
			// Policy
			if (salary.lessThan(Money.valueOf(11_000))) {
				throw new IllegalStateException("Violates Policy-10");
			}	
			// Invariants
			return new Employee(this);
		}
	}

	public void promote(Money newSalary, Department... departments) {
		this.salary = newSalary;
		this.departments.clear();
		this.departments.addAll(List.of(departments));
		// Validation Rule
		// Business Rule
		// Policy
		// Invariants
		// accept new state ; reject new state otherwise
		// implement the memento pattern (GoF Design Patterns)
	}
	
	public void updateSalary(Rate rate) {
		this.salary = this.salary.multiply(1.0 + rate.getValue());
		// Validation Rule
		// Business Rule
		// Policy
		// Invariants
		// accept new state ; reject new state otherwise
		// implement the memento pattern (GoF Design Patterns)
	}

}
