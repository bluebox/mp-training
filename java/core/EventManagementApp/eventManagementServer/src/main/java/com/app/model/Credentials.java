package com.app.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Credentials {
    @NotNull(message="User Id not to be Null!")
	private int userId;
    @NotBlank(message="User name not to be Null!")
	@Size(min = 2, message = "Event name has atleast 3 characters")
	private String userName;
    @NotBlank(message="password not to be Null!")
	@Size(min = 3, message = "password has atleast 3 characters")
	private String password;
	private String role;
}

