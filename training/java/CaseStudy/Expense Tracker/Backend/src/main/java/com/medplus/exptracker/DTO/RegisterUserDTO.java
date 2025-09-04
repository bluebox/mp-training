package com.medplus.exptracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
	private String username;
	private String password;
	private Integer manager_id;
	private Integer role_id;
}
