package com.example.vehicle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehicle.model.Claim;
import com.example.vehicle.repo.ClaimDao;

@Service
public class ClaimService {
	@Autowired
	private ClaimDao repo;
	public String claimInsurance(double reqAmount,String damageType,int policyId) throws Exception {
		if(repo.isEligible(policyId)) {
			return repo.claimInsurance(reqAmount, damageType, policyId);
		}
		else {
			return "You are not eligible to get this policy";
		}
	}
	public List<Claim> getAllIntiatedClaims() throws Exception {
		return repo.getAllIntiatedClaims();
	}
	public String approveClaims(int claimId,double claimAmount, char claimStatus,String approvedBy) throws Exception {
		return repo.updateStatus(claimId, claimAmount, claimStatus,approvedBy);
	}
	public List<Claim> getAllClaims() throws Exception {
		return repo.getAllClaims();
	}
	public Claim getClaimById(int claimId) throws Exception {
		return repo.getClaimById(claimId);
	}
	public Claim getClaimByUser(String username) throws Exception {
		return repo.getClaimByUser(username);
	}
	public ArrayList<Object> getClaimReport(int claimId) throws Exception {
		return repo.getClaimReport(claimId);
	}
}
