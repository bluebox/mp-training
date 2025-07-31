package com.medplus.marketing.domain;

import lombok.Data;

@Data
public class ResponseObject {

	private String message;
	private StatusCode statusCode;
	private Object dataObject;
	
	public ResponseObject(StatusCode statusCode, String message) {
		this(statusCode, message, null);
	}
	
	public ResponseObject(StatusCode statusCode, String message, Object dataObject) {
		this.statusCode = statusCode;
		this.message = message;
		this.dataObject = dataObject;
	}
	
}
