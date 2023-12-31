package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money valueOf(double value, FiatCurrency currency) {
		Objects.requireNonNull(currency);
		if (value <= 0.0)
			throw new IllegalArgumentException("Value of the money should be positive");
		return new Money(value, currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return currency == other.currency && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}

	public boolean lessThan(Money other) {
		return this.currency.equals(other.currency) && this.value < other.getValue();
	}

	public static Money valueOf(int value) {
		return valueOf(value, FiatCurrency.TL);
	}

	public Money multiply(double factor) {
		return Money.valueOf(value*factor, currency);
	}

}
