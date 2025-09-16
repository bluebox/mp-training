package com.users.Users.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
	private Integer roleId;
	private String rolecode;
	private String roleName;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
