package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Rate {
	private final double value;

	private Rate(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public static Rate of(double value) {
		if (value <= 0.0)
			throw new IllegalArgumentException("%f must be a positive value for Rate".formatted(value));
		return new Rate(value);
	}

	@Override
	public String toString() {
		return "Rate [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

}
