package org.microservice.employee.model;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String field;

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public ErrorDetails(Date timestamp, String message, String field) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.field = field;
	}

}
