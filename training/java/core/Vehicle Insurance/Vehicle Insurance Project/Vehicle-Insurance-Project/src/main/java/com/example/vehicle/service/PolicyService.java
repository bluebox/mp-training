package com.example.vehicle.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.enums.PolicyType;
import com.example.vehicle.model.Policy;
import com.example.vehicle.repo.PolicyDao;

@Service
@Transactional
public class PolicyService {
	@Autowired
	private PolicyDao repo;
	public String addPolicy(int policyTerm, PolicyType policyType, LocalDateTime startDate,LocalDateTime endDate, int vehicleId, String approvedBy) throws Exception {
		if(repo.getPolicyByVehicleIdCount(vehicleId)>0) {
			System.out.println("Policy already existed for this Vehicle");
			return "There is a policy already for this vehicle";
		}
		return repo.addPolicy(policyTerm, policyType, startDate, endDate, vehicleId, approvedBy);
	}
	public String dueDate(int policyId) throws Exception {
		return repo.dueDate(policyId);
	}
	public String payPolicyAmount(int policyId) throws SQLException {
		if(!repo.getPolicyById(policyId).getEndDate().isAfter(LocalDateTime.now())) {
			repo.updateStatus(policyId);
			return repo.updateEndDate(policyId);
		}
		else {
			return "There is no due bill on this policy";
		}
	}
	public String updateStatus(int policyId) throws Exception {
		return repo.updateStatus(policyId);
	}
	public List<Policy> showAllRequestedPolicies() throws Exception {
		return repo.getPoliciesRequested();
	}
	public String updatePolicyStatus(int policyId,char policyStatus,String approvedBy) throws Exception {
		return repo.updateStatus(policyId, policyStatus,approvedBy);
	}
	public String renewPolicy(int policyId,int policyTerm, String approvedBy) throws Exception {
		Policy p=repo.getPolicyById(policyId);
		return repo.updatePolicy(policyId, policyTerm, (p.getPolicyType()=="silver")?PolicyType.Silver:(p.getPolicyType()=="gold")?PolicyType.Gold:PolicyType.Platinum, p.getVehicleId(), approvedBy);
	}
	public String updatePolicy(int policyId,int policyTerm,PolicyType policyType, String approvedBy) throws Exception {
		Policy p=repo.getPolicyById(policyId);
		return repo.updatePolicy(policyId, policyTerm, policyType, p.getVehicleId(), approvedBy);
	}
	public Policy getPolicyById(int policyId) throws Exception {
		return repo.getPolicyById(policyId);
	}
	public List<Policy> getAllPolicies() throws Exception {
		return repo.getAllPolicies();
	}
	public Policy getPolicyByVehicles(int vehicleId) throws Exception {
		return repo.getPolicyByVehicleId(vehicleId);
	}
	public Policy getPolicyByRegNum(String regnum) throws Exception {
		return repo.getPolicyByVehicle(regnum);
	}
	public List<Policy> getPolicyByUser(String username) throws Exception {
		return repo.getPolicyByUser(username);
	}
	public List<Object> getPolicyReport(int policyId) throws Exception {
		return repo.getPolicyReport(policyId);
	}
	@Scheduled(cron = "0 0 12 * * ?")
	public void dueDate() {
		System.out.println(repo.dueDate());
	}
}
