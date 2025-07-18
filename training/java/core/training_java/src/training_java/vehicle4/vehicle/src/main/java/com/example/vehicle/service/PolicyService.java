package com.example.vehicle.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.dao.PolicyDao;
import com.example.vehicle.enums.PolicyType;
import com.example.vehicle.model.Policy;

@Service
@Transactional
public class PolicyService {
	@Autowired
	private PolicyDao repo;
	public String addPolicy(int policyTerm, PolicyType policyType, LocalDateTime startDate,LocalDateTime endDate, int vehicleId, String approvedBy) throws Exception {
		if(repo.getPolicyByVehicleIdCount(vehicleId)>0) {
		return "There is a policy already for this vehicle";
		}
		return repo.addPolicy(policyTerm, policyType, startDate, endDate, vehicleId, approvedBy);
		}

	public String dueDate(int policyId) {
		return repo.updateStatus(policyId);
	}
	
	public String payPolicyAmount(int policyId) {
		if(!repo.getPolicyById(policyId).getEndDate().isAfter(LocalDateTime.now())) {
			repo.updateStatus(policyId);
			return repo.updateEndDate(policyId);
		}
		else if(repo.getPolicyById(policyId).getPolicyStatus().getC().equals("R")||repo.getPolicyById(policyId).getPolicyStatus().getC().equals("I")) {
			return "Your Policy with Id "+policyId+" is Not Active To Pay";
		}
		else {
			return "There is no due bill on this policy";
		}
	}
	public List<Policy> showAllRequestedPolicies(){
		return repo.getPoliciesRequested();
	}
	public String updatePolicyStatus(int policyId,char policyStatus,String approvedBy) {
		return repo.updateStatus(policyId,policyStatus,approvedBy);
	}
	public String renewPolicy(int policyId,int policyTerm, String approvedBy) {
		Policy p=repo.getPolicyById(policyId);
		return repo.updatePolicy(policyId, policyTerm, (p.getPolicyType()=="silver")?PolicyType.Silver:(p.getPolicyType()=="gold")?PolicyType.Gold:PolicyType.Platinum, p.getVehicleId(), approvedBy);
	}
	public String updatePolicy(int policyId,int policyTerm,PolicyType policyType, String approvedBy) {
		Policy p=repo.getPolicyById(policyId);
		return repo.updatePolicy(policyId, policyTerm, policyType, p.getVehicleId(), approvedBy);
	}
	public Policy getPolicyById(int policyId) {
		return repo.getPolicyById(policyId);
	}
	
	public String updateStatus(int policyId) throws Exception {
		return repo.updateStatus(policyId);
	}
	
	public List<Policy> getAllPolicies(){
		return repo.getAllPolicies();
	}
	public Policy getPolicyByVehicles(int vehicleId) throws Exception {
		return repo.getPolicyByVehicleId(vehicleId);
	}
	public Policy getPolicyByRegNum(String regnum) {
		return repo.getPolicyByVehicle(regnum);
	}
	public List<Policy> getPolicyByUser(String username){
		return repo.getPolicyByUser(username);
	}
	public List<Object> getPolicyReport(int policyId){
		return repo.getPolicyReport(policyId);
	}
}
