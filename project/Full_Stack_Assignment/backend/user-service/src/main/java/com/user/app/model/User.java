package com.user.app.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long userId;
	private String username;
	private String email;
	private String fullName;
	private String phone;
	private String gender;
	private Integer age;
	private String password;
	private String role;
	private LocalDateTime createdAt;

}