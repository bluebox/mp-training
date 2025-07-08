package com.example.vehicle.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.enums.PolicyType;
import com.example.vehicle.model.Policy;

@Repository
public class PolicyDao {
	public JdbcTemplate jdbcTemplate; 
	@Autowired
	public PolicyDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public String addPolicy(int policyTerm, PolicyType policyType, LocalDateTime startDate,LocalDateTime endDate, int vehicleId, String approvedBy) {
		int rowsEffected=jdbcTemplate.update("insert into policy values(?,?,?,?,?,?,?,?,?)",policyTerm,policyType.getPtype(),policyType.getPremiumAmount(),policyType.getPolicyAmount(),startDate,endDate,'R',vehicleId,approvedBy);
		if(rowsEffected>0) {
			return "Inserted";
		}
		else {
			return "Falied to insert";
		}
	}
	public String updateEndDate(int policyId) {
		LocalDateTime endDate=jdbcTemplate.queryForObject("select end_date from policy where policy_id=?", LocalDateTime.class,policyId);
		int rowsEffected=jdbcTemplate.update("update policy set end_date=? where policy_id=?",endDate.plusYears(1),policyId);
		if(rowsEffected>0) {
			return "End date is updated";
		}
		else {
			return "Failed to update end date";
		}
	}
	public String updateStatus(int policyid) {
		char policyStatus=jdbcTemplate.queryForObject("select policy_status from policy where policy_id=?", Character.class,policyid);
		int rowsEffected=jdbcTemplate.update("update policy set policy_status=? where policy_id=?",(policyStatus=='A')?'I':'A',policyid);
		if(rowsEffected>0) {
			return "Status is updated";
		}
		else {
			return "Failed to update status";
		}
	}
	public List<Policy> getPoliciesRequested() {
		return jdbcTemplate.queryForList("select * from policy where policy_status='R'",Policy.class);
	}
	public String updateStatus(int policyid,char policyStatus) {
		int rowsEffected=jdbcTemplate.update("update policy set policy_status=? where policy_id=?",policyStatus,policyid);
		if(rowsEffected>0) {
			if(policyStatus=='A'){
				return "Policy is accepted";
			}
			else {
				return "Policy is rejected";
			}
		}
		else {
			return "Failed to update status";
		}
//		char prevPolicyStatus=jdbcTemplate.queryForObject("select policy_status from policy where policy_id=?", Character.class,policyid);
//		if(prevPolicyStatus=='R') {
//			int rowsEffected=jdbcTemplate.update("update policy set policy_status=? where policy_id=?",policyStatus,policyid);
//			if(rowsEffected>0) {
//				if(policyStatus=='A'){
//					return "Policy is accepted";
//				}
//				else {
//					return "Policy is rejected";
//				}
//			}
//			else {
//				return "Failed to update status";
//			}
//		}
//		else if(prevPolicyStatus=='A'){
//			return "Policy is already accepted";
//		}
//		else {
//			return "Policy is already rejected";
//		}
	}
	public String renewPolicy(int policyId,int policyTerm,PolicyType policyType, int vehicleId, String approvedBy) {
		int rowsEffected=jdbcTemplate.update("update policy set policy_term=?,policy_type=?,premium_amount=?,policy_amount=?,start_date=?,end_date=?,policy_status=?,vehicle_id=?,approved_by=? where policy_id=?",policyTerm,policyType.getPtype(),policyType.getPremiumAmount(),policyType.getPolicyAmount(),'A',vehicleId,approvedBy,policyId);
		if(rowsEffected>0) {
			return "Policy is updated";
		}
		else {
			return "Failed to update policy";
		}
	}
	public List<Policy> getAllPolicies() {
		return jdbcTemplate.queryForList("select * from policy",Policy.class);
	}
	public Policy getPolicyById(int policyId) {
		return jdbcTemplate.queryForObject("select * from policy where policy_id=?", Policy.class, policyId);
	}
	public List<Policy> getPolicyByVehicle(String regNum){
		return jdbcTemplate.queryForList("select p from policy p,vehicles v where p.vehicle_id=v.vehicle_id and v.reg_num=?",Policy.class,regNum);
	}
	public List<Policy> getPolicyByUser(String username){
		return jdbcTemplate.queryForList("select p from policy p,vehicles v,user u,customers c where p.vehicle_id=v.vehicle_id and v.customer_id=c.customer_id and c.customer_id=u.customer_id and u.username=?",Policy.class,username);
	}
}
