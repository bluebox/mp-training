package com.app.model;

import lombok.Data;

@Data
public class Credentials {
	private int userId;
	private String userName;
	private String password;
	private String role;

}
