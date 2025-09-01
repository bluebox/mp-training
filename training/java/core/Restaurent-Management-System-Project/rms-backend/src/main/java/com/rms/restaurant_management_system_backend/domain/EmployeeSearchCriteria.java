package com.rms.restaurant_management_system_backend.domain;

import java.sql.Date;
import java.util.List;

import com.rms.restaurant_management_system_backend.constant.EmployeeStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchCriteria {
	private Integer empId;

	private String name;

	private String email;

	private String phone;

	private List<EmployeeStatus> statuses;

	private Date join_date;

	private Date leaving_date;
}
