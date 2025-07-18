package com.example.vehicle.controller;

import java.util.ArrayList;
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

import com.example.vehicle.model.Claim;
import com.example.vehicle.service.ClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimController {
	private final ClaimService service;
	@Autowired
	public ClaimController(ClaimService service) {
		this.service=service;
	}
	@PostMapping("/add")
	public String requestClaim(@RequestBody Claim c) throws Exception {
		return service.claimInsurance(c.getReqAmount(), c.getDamageType(), c.getPolicyId(),c.getApprovedBy());
	}
	@GetMapping("/claimById")
	public Claim getClaimsById(@RequestParam int claimId) throws Exception {
		return service.getClaimById(claimId);
	}
	@GetMapping("/claimByPolicyId")
	public List<Claim> getClaimByPolicyId(@RequestParam int policyId) throws Exception {
		return service.getClaimByPolicyId(policyId);
	}
	@GetMapping("/claimByVehicleId")
	public List<Claim> getClaimByVehicleId(@RequestParam int vehicleId) throws Exception {
		return service.getClaimByVehicleId(vehicleId);
	}
	@GetMapping("/claimByUser")
	public List<Claim> getClaimByUser(@RequestParam String username) throws Exception {
		return service.getClaimByUser(username);
	}
	@GetMapping("/claimReports")
	public ArrayList<Object> claimReport(@RequestParam int claimId) throws Exception {
		return service.getClaimReport(claimId);
	}
//	@PreAuthorize(value = "hasRole('admin')")
	@GetMapping("/allClaims")
	public List<Claim> getAllClaims() throws Exception {
		return service.getAllClaims();
	}
//	@PreAuthorize(value="hasRole('admin')")
	@GetMapping("/allIntiatedClaims")
	public List<Claim> getAllIntiatedClaims() throws Exception {
		return service.getAllIntiatedClaims();
	}
//	@PreAuthorize(value = "hasRole('admin')")
	@PutMapping("/approveClaim")
	public String approveClaim(@RequestParam int claimId,@RequestParam double claimAmount,@RequestParam char status,@RequestParam String approvedBy) throws Exception {
		return service.approveClaims(claimId, claimAmount, status, approvedBy);
	}
}
//{
//    "reqAmount":10000,
//    "damageType":"accident",
//    "policyId":1,
//    "approvedBy":"Bhanu"
//}
