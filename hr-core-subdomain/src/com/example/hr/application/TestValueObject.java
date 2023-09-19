package com.example.hr.application;

import com.example.hr.domain.FullName;

public class TestValueObject {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var jack1 = FullName.of("jack", "bauer");
		var jack2 = FullName.of("jack", "bauer");
		//var jack3 = FullName.of("jack", null);
		var jack4 = new FullName("jack", null);
		System.out.println(jack1.firstName());
		System.out.println(jack1.lastName());
		System.out.println(jack1.toString());
		System.out.println(jack1.hashCode());
		System.out.println(jack2.hashCode());
		System.out.println(jack2.equals(jack1));

	}

}
