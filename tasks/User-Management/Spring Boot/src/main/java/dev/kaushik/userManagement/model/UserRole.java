package dev.kaushik.userManagement.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRole  {
	private int userRoleId;
	private int roleId;
	private String userName;
	
	private String country;
	private String state;
	private String city; 
	
	LocalDateTime createdAt;
	String createdBy; 
	LocalDateTime updatedAt;
	String updatedBy;
}
