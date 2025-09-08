package com.medplus.lms.controller;

import com.medplus.lms.domain.*;
import com.medplus.lms.dto.AllEnumsResponse;
import com.medplus.lms.dto.EnumResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class EnumController {

    @GetMapping("/api/enums")
    public AllEnumsResponse getAllEnums() {
        Map<String, List<EnumResponse>> result = new LinkedHashMap<>();

        result.put("Category", Arrays.stream(Category.values())
                .map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription()))
                .collect(Collectors.toList()));

        result.put("Status", Arrays.stream(Status.values())
                .map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription()))
                .collect(Collectors.toList()));

        result.put("AvailabilityStatus", Arrays.stream(AvailabilityStatus.values())
                .map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription()))
                .collect(Collectors.toList()));

        result.put("IssueStatus", Arrays.stream(IssueStatus.values())
                .map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription()))
                .collect(Collectors.toList()));

        result.put("Gender", Arrays.stream(Gender.values())
                .map(e -> new EnumResponse(e.name(), e.getCode(), e.getDescription()))
                .collect(Collectors.toList()));

        return new AllEnumsResponse(result);
    }
}
