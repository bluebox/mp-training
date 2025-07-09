package com.example.vehicle.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.enums.PolicyType;
import com.example.vehicle.model.Customer;
import com.example.vehicle.model.Policy;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.rowMappers.CustomerRowMapper;
import com.example.vehicle.rowMappers.PolicyRowMapper;
import com.example.vehicle.rowMappers.VehicleRowMapper;

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
	public Policy getPolicyById(int policyId) {
		return jdbcTemplate.queryForObject("select * from policy where policy_id=?", new PolicyRowMapper(), policyId);
	}
	public String updateEndDate(int policyId) {
		Policy p=getPolicyById(policyId);
		if(p.getStartDate().plusYears(p.getPolicyTerm()).isBefore(LocalDateTime.now())) {
			LocalDateTime endDate=jdbcTemplate.queryForObject("select end_date from policy where policy_id=?", LocalDateTime.class,policyId);
			int rowsEffected=jdbcTemplate.update("update policy set end_date=? where policy_id=?",endDate.plusYears(1),policyId);
			if(rowsEffected>0) {
				return "End date is updated";
			}
			else {
				return "Failed to update end date";
			}
		}
		else {
			return "The policy is expired. Please renew the policy";
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
		return jdbcTemplate.query("select * from policy where policy_status='R'",new PolicyRowMapper());
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
	public String updatePolicy(int policyId,int policyTerm,PolicyType policyType, int vehicleId, String approvedBy) {
		int rowsEffected=jdbcTemplate.update("update policy set policy_term=?,policy_type=?,premium_amount=?,policy_amount=?,start_date=?,end_date=?,policy_status=?,vehicle_id=?,approved_by=? where policy_id=?",policyTerm,policyType.getPtype(),policyType.getPremiumAmount(),policyType.getPolicyAmount(),'A',vehicleId,approvedBy,policyId);
		if(rowsEffected>0) {
			return "Policy is updated";
		}
		else {
			return "Failed to update policy";
		}
	}
	public List<Policy> getAllPolicies() {
		return jdbcTemplate.query("select * from policy",new PolicyRowMapper());
	}
	public List<Policy> getPolicyByVehicleId(int vehicleId) {
		return jdbcTemplate.query("select p.* from policy p,vehicles v where p.vehicle_id=v.vehicle_id and v.vehicle_id=? and v.status='A'",new PolicyRowMapper(),vehicleId);
	}
	public Policy getPolicyByVehicle(String regNum){
		return jdbcTemplate.queryForObject("select p.* from policy p,vehicles v where p.vehicle_id=v.vehicle_id and v.reg_num=? and v.status='A'",new PolicyRowMapper(),regNum);
	}
	public List<Policy> getPolicyByUser(String username){
		return jdbcTemplate.query("select p.* from policy p,vehicles v,user u,customers c where p.vehicle_id=v.vehicle_id and v.customer_id=c.customer_id and c.customer_id=u.customer_id and u.username=? and v.status='A'",new PolicyRowMapper(),username);
	}
	public ArrayList<Object> getPolicyReport(int policyId) {
		ArrayList<Object> policyDetails=new ArrayList<Object>();
		Customer c=jdbcTemplate.queryForObject("select c.* from policy p,vehicles v,customers c where p.vehicle_id=v.vehicle_id and v.customer_id=c.customer_id and p.policy_id=?", new CustomerRowMapper(),policyId);
		Vehicle v=jdbcTemplate.queryForObject("select v.* from policy p,vehicles v where p.vehicle_id=v.vehicle_id and p.policy_id=?", new VehicleRowMapper(),policyId);
		Policy p=getPolicyById(policyId);
		policyDetails.add(c);
		policyDetails.add(v);
		policyDetails.add(p);
		return policyDetails;
		
	}
}