package dev.kaushik.userManagement.model;

import java.time.LocalDateTime;

import dev.kaushik.userManagement.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
	private int roleId;
	
	private String roleName;
	
	private Status status = Status.ACTIVE;
	
	LocalDateTime createdAt;
	String createdBy; 
	LocalDateTime updatedAt;
	String updatedBy;
}
