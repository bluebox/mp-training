package com.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Response {
	private String statusCode;
	private String statusMsg;
}
