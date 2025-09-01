package com.rms.restaurant_management_system_backend.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@Service
public class EmployeesClientServiceImpl {

	private final WebClient webClient;

	public EmployeesClientServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	public CustomResponse addEmployee(String jwtToken, Employees employee) {
		return webClient.post().uri("/api/employees/add").bodyValue(employee)
				.header("Authorization", "Bearer " + jwtToken).retrieve().bodyToMono(CustomResponse.class).block();

	}

	public CustomResponse getEmployees(String jwtToken) {
		return webClient.get().uri("/api/employees").header("Authorization", "Bearer " + jwtToken).retrieve()
				.bodyToMono(CustomResponse.class).block();

	}

	public CustomResponse updateEmployee(String jwtToken, int id, Employees updateBody) {
		return webClient.put().uri("/api/employees/update/" + String.valueOf(id)).bodyValue(updateBody)
				.header("Authorization", "Bearer " + jwtToken).retrieve().bodyToMono(CustomResponse.class).block();

	}

	public CustomResponse deleteEmployee(String jwtToken, int id) {
		return webClient.delete().uri("/api/employees/delete/" + String.valueOf(id))
				.header("Authorization", "Bearer " + jwtToken).retrieve().bodyToMono(CustomResponse.class).block();
	}

}