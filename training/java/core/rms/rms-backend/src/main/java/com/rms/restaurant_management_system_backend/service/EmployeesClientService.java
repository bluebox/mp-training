package com.rms.restaurant_management_system_backend.service;

import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

public interface EmployeesClientService {

	CustomResponse addEmployee(String jwtToken, Employees employee);

	CustomResponse getEmployees(String jwtToken);

	CustomResponse updateEmployee(String jwtToken, int id, Employees updateBody);

	CustomResponse deleteEmployee(String jwtToken, int id);

}
