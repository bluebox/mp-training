package com.medplus.Roles_backend.domain;

import lombok.Data;
import java.time.LocalDateTime;

import com.medplus.Roles_backend.enums.ActiveStatus;
import com.medplus.Roles_backend.enums.ApprovalStatus;
import com.medplus.Roles_backend.enums.Gender;

@Data
public class BaseUser {
	private String firstName;
	private String lastName;
	private String username;
	private Integer age;
	private String empId;
	private String mobile;
	private String email;
	private String country;
	private String state;
	private String city;
	private Gender gender;

	private ApprovalStatus approvalStatus;
	private ActiveStatus activeStatus;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private String createdBy;
	private String updatedBy;
}
