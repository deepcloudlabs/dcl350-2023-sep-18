package com.example.hr.infrastructure;

public interface EventPublisher<E> {
	public void publishEvent(E e);
}
