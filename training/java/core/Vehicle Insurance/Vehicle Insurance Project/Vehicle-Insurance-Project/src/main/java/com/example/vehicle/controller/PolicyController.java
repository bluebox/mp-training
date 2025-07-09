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
		System.out.println(p.getApprovedBy());
		return policyService.addPolicy(p.getPolicyTerm(), (p.getPolicyType().equals("gold"))?PolicyType.Gold: (p.getPolicyType().equals("silver"))?PolicyType.Silver:PolicyType.Platinum, LocalDateTime.now(), LocalDateTime.now().plusYears(1),p.getVehicleId(),p.getApprovedBy());
	}
	@GetMapping("/showAll")
	public List<Policy> getAllPolicies() throws Exception {
		return policyService.getAllPolicies();
	}
}
//{
//    "policyTerm": 10,
//    "policyType": "Gold",
//    "startDate": "2025-07-10T01:25:48",
//    "endDate": "2026-07-10T01:25:48",
//    "policyStatus": "R",
//    "vehicleId": 1,
//    "approvedBy": "Bhanu"
//}