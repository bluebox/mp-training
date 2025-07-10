package com.example.vehicle.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return policyService.addPolicy(p.getPolicyTerm(), (p.getPolicyType().equals("gold"))?PolicyType.Gold: (p.getPolicyType().equals("silver"))?PolicyType.Silver:PolicyType.Platinum, LocalDateTime.now(), LocalDateTime.now().plusYears(1),p.getVehicleId(),p.getApprovedBy());
	}
	@GetMapping("/showAll")
	public List<Policy> getAllPolicies() throws Exception {
		return policyService.getAllPolicies();
	}
	@PutMapping("/dueDate")
	public String dueDate(@RequestParam int policyId) throws Exception {
		return policyService.dueDate(policyId);
	}
	@PutMapping("/payDue")
	public String payDueAmount(@RequestParam int policyId) throws SQLException {
		return policyService.payPolicyAmount(policyId);
	}
	@GetMapping("/allRequestedPolicies")
	public List<Policy> getAllRequestedPolicies() throws Exception {
		return policyService.showAllRequestedPolicies();
	}
	@PutMapping("/updatePolicyRequested")
	public String updatedRequestedPolicy(@RequestParam int policyId,@RequestParam char status) throws Exception {
		return policyService.updatePolicyStatus(policyId, status);
	}
	@PutMapping("/renew")
	public String renewPolicy(@RequestParam int policyId,@RequestParam int policyTerm,@RequestParam String approvedBy) throws Exception {
		return policyService.renewPolicy(policyId, policyTerm, approvedBy);
	}
	@PostMapping("/update")
	public String updatePolicy(@RequestBody Policy p) throws Exception {
		return policyService.updatePolicy(p.getPolicyId(), p.getPolicyTerm(), (p.getPolicyType().equals("gold"))?PolicyType.Gold: (p.getPolicyType().equals("silver"))?PolicyType.Silver:PolicyType.Platinum, p.getApprovedBy());
	}
	@GetMapping("/showById")
	public Policy getPolicyById(@RequestParam int policyId) throws Exception {
		return policyService.getPolicyById(policyId);
	}
	@GetMapping("/showByUser")
	public List<Policy> getPolicyByUser(@RequestParam String username) throws Exception {
		return policyService.getPolicyByUser(username);
	}
	@GetMapping("/showByVehicleId")
	public Policy getPolicyByVehicleId(@RequestParam int vehicleId) throws Exception {
		return policyService.getPolicyByVehicles(vehicleId);
	}
	@GetMapping("/showByRegNum")
	public Policy getPolicyByRegNum(@RequestParam String regNum) throws Exception {
		return policyService.getPolicyByRegNum(regNum);
	}
	@GetMapping("report")
	public List<Object> getPolicyReport(@RequestParam int policyId) throws Exception {
		return policyService.getPolicyReport(policyId);
	}
}
//{
//    "policyTerm": 10,
//   "policyType": "Gold",
//   "vehicleId": 4,
//   "approvedBy": "Bhanu"
//}