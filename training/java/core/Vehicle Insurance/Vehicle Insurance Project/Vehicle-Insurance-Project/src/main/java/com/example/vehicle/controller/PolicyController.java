package com.example.vehicle.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.enums.PolicyType;
import com.example.vehicle.model.Policy;
import com.example.vehicle.service.PolicyService;

@RestController
@CrossOrigin("*")
@RequestMapping("/policy")
public class PolicyController {
	private final PolicyService policyService;
	@Autowired
	public PolicyController(PolicyService policyService) {
		this.policyService=policyService;
	}
	@PostMapping("/add")
	public String addPolicy(@RequestBody Policy p) throws Exception {
		return policyService.addPolicy(p.getPolicyTerm(), PolicyType.Gold, LocalDateTime.now(), LocalDateTime.now(), 1, "Bhanu");
	}
	@GetMapping("/showAll")
	public List<Policy> getAllPolicies() throws Exception {
		return policyService.getAllPolicies();
	}
}
