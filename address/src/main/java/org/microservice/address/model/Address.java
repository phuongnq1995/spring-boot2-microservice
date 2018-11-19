package org.microservice.address.model;

public class Address {

	private int number;
	private String name;
	private int port;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Address(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

}
