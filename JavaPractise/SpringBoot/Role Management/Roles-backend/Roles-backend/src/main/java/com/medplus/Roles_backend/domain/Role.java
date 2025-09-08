package com.medplus.Roles_backend.domain;

import com.medplus.Roles_backend.enums.ActiveStatus;

import lombok.Data;

@Data
public class Role {
	private String roleId;
	private String roleName;
	private ActiveStatus status;
}
