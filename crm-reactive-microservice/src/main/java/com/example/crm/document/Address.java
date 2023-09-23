package com.example.crm.document;

import java.util.List;

import lombok.Data;

@Data
public class Address {
	private String country;
	private String city;
	private List<String> lines;
	private AddressType type;
}
