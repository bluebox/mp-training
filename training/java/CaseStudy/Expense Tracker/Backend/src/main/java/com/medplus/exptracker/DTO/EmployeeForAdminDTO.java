package com.medplus.exptracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForAdminDTO {
	String username;
	String id;
	int manager_id;
}
