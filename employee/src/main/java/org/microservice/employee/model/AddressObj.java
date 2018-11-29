package org.microservice.employee.model;

public class AddressObj {
	
	private int number;
	private String name;
	private int port;
	private Integer employeeId;

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

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public AddressObj(int number, String name, int port) {
		super();
		this.number = number;
		this.name = name;
		this.port = port;
	}

	public AddressObj() {}
}
