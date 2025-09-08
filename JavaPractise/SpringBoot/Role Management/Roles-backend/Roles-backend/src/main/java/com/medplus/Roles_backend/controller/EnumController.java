package com.medplus.Roles_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.Roles_backend.dto.AllEnumsResponse;
import com.medplus.Roles_backend.dto.ApiResponse;
import com.medplus.Roles_backend.dto.EnumResponse;
import com.medplus.Roles_backend.enums.ActiveStatus;
import com.medplus.Roles_backend.enums.ApprovalStatus;
import com.medplus.Roles_backend.enums.Gender;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class EnumController {

	@GetMapping("/api/enums")
	public ApiResponse<AllEnumsResponse> getAllEnums() {
		Map<String, List<EnumResponse>> result = new LinkedHashMap<>();

		result.put("ActiveStatus", Arrays.stream(ActiveStatus.values())
				.map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription())).collect(Collectors.toList()));

		result.put("ApprovalStatus", Arrays.stream(ApprovalStatus.values())
				.map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription())).collect(Collectors.toList()));

		result.put("Gender", Arrays.stream(Gender.values())
				.map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription())).collect(Collectors.toList()));

		AllEnumsResponse enumsResponse = new AllEnumsResponse(result);

		return new ApiResponse<>(true, "Enums fetched successfully", enumsResponse);
	}
}
