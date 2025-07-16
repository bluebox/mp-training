package com.example.vehicle.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.enums.PolicyStatus;
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
		this.jdbcTemplate = jdbcTemplate;
	}

	public String addPolicy(int policyTerm, PolicyType policyType, LocalDateTime startDate, LocalDateTime endDate,
			int vehicleId, String approvedBy) {
		int vehicles = jdbcTemplate.queryForObject("select count(vehicle_id) from vehicles where vehicle_id=?",
				Integer.class, vehicleId);
		if (vehicles == 0) {
			return "There is no vehicle with this Id to give Policy";
		}

		if (jdbcTemplate.queryForObject("select count(policy_id) from policy where vehicle_id=? and policy_status='R'",
				Integer.class, vehicleId) > 0) {
			return "There is a Policy requested Already for this Vehicle Id";
		}

		if (jdbcTemplate.queryForObject("select status from vehicles where vehicle_id=?", Character.class, vehicleId)
				.equals('I')) {
			return "There is no vehicle";

		}

		if (!validate(approvedBy)) {
			return "No approvedBy reference in Admin or Users";
		}

		int rowsEffected = jdbcTemplate.update(
				"insert into policy(policy_term,policy_type,premium_amount,policy_amount,start_date,end_date,policy_status,vehicle_id,approved_by) values(?,?,?,?,?,?,?,?,?)",
				policyTerm, policyType.getPtype(), policyType.getPremiumAmount(), policyType.getPolicyAmount(),
				startDate, endDate, "R", vehicleId, approvedBy);
		if (rowsEffected > 0) {
			return "Inserted";
		} else {
			return "Falied to insert";
		}
	}
	public String dueDate(int policyId) {
		if(jdbcTemplate.queryForObject("select policy_status from policy where policy_id=?", Character.class,policyId)==0) {
			return "Policy not found";
		}
		char policyStatus=jdbcTemplate.queryForObject("select policy_status from policy where policy_id=?", Character.class,policyId);
		int rowsEffected=jdbcTemplate.update("update policy set policy_status=? where policy_id=?",(policyStatus=='R')?"R":"I",policyId);
		if(rowsEffected>0) {
			return "Status is updated";
		}
		else {
			return "Failed to update status";
		}
	}
	public Policy getPolicyById(int policyId) {
		return jdbcTemplate.queryForObject(
				"select policy_id,policy_term,policy_type,premium_amount,policy_amount,start_date,end_date,policy_status,vehicle_id,approved_by from policy where policy_id=?",
				new PolicyRowMapper(), policyId);
	}

	public String updateEndDate(int policyId) {
		Policy p = getPolicyById(policyId);
		if (p.getStartDate().plusYears(p.getPolicyTerm()).isAfter(LocalDateTime.now())) {
			LocalDateTime endDate = jdbcTemplate.queryForObject("select end_date from policy where policy_id=?",
					LocalDateTime.class, policyId);
			int rowsEffected = jdbcTemplate.update("update policy set end_date=? where policy_id=?",
					endDate.plusYears(1), policyId);
			if (rowsEffected > 0) {
				return "End date is updated";
			} else {
				return "Failed to update end date";
			}
		} else {
			return "The policy is expired. Please renew the policy";
		}
	}

	public String updateStatus(int policyId) {
		char policyStatus = jdbcTemplate.queryForObject("select policy_status from policy where policy_id=?",
				Character.class, policyId);
		int rowsEffected = jdbcTemplate.update("update policy set policy_status=? where policy_id=?",
				(policyStatus == 'A') ? "I" : (policyStatus == 'I') ? "A" : "R",policyId);
		if (rowsEffected > 0) {
			return "Status is updated";
		} else {
			return "Failed to update status";
		}
	}

	public Boolean validate(String approvedBy) {
		List<String> adminUsernames = jdbcTemplate.queryForList("select username from admin", String.class);
		List<String> userUsernames = jdbcTemplate.queryForList("select username from users", String.class);
		boolean adminBool = adminUsernames.contains(approvedBy);
		boolean userBool = userUsernames.contains(approvedBy);

		return adminBool || userBool;
	}

	public String updateStatus(int policyid, char policyStatus, String approvedBy) {

		if (!validate(approvedBy)) {
			return "No approvedBy reference in Admin or Users";
		}

		int rowsEffected = jdbcTemplate.update("update policy set policy_status=?,end_date=?,approved_by=? where policy_id=?",
				String.valueOf(policyStatus),LocalDateTime.now().plusYears(1), approvedBy, policyid);
		if (rowsEffected > 0) {
			return "Status is updated";
		} else {
			return "Failed to update status";
		}
	}

	public List<Policy> getPoliciesRequested() {
		return jdbcTemplate.query(
				"select policy_id,policy_term,policy_type,premium_amount,policy_amount,start_date,end_date,policy_status,vehicle_id,approved_by from policy where policy_status='R'",
				new PolicyRowMapper());
	}

	public String updateStatus(int policyid, char policyStatus) {
		int rowsEffected = jdbcTemplate.update("update policy set policy_status=? where policy_id=?",
				Character.toString(policyStatus), policyid);
		if (rowsEffected > 0) {
			if (policyStatus == 'A') {
				return "Policy is accepted";
			} else {
				return "Policy is rejected";
			}
		} else {
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

	public String updatePolicy(int policyId, int policyTerm, PolicyType policyType, int vehicleId, String approvedBy) {

		if (!validate(approvedBy)) {
			return "No approvedBy reference in Admin or Users";
		}

		int rowsEffected = jdbcTemplate.update(
				"update policy set policy_term=?,policy_type=?,premium_amount=?,policy_amount=?,start_date=?,end_date=?,policy_status=?,vehicle_id=?,approved_by=? where policy_id=?",
				policyTerm, policyType.getPtype(), policyType.getPremiumAmount(), policyType.getPolicyAmount(),LocalDateTime.now(),LocalDateTime.now().plusYears(1),"A",
				vehicleId, approvedBy, policyId);
		if (rowsEffected > 0) {
			return "Policy is updated";
		} else {
			return "Failed to update policy";
		}
	}

	public List<Policy> getAllPolicies() {
		return jdbcTemplate.query(
				"select policy_id,policy_term,policy_type,premium_amount,policy_amount,start_date,end_date,policy_status,vehicle_id,approved_by from policy",
				new PolicyRowMapper());
	}

	public Policy getPolicyByVehicleId(int vehicleId) throws Exception {
		if (jdbcTemplate.queryForObject("select count(policy_id) from policy where  vehicle_id=? and policy_status='A'",
				Integer.class, vehicleId) == 0) {
			return new Policy();
		}
		return jdbcTemplate.queryForObject(
				"select policy_id,policy_term,policy_type,premium_amount,policy_amount,start_date,end_date,policy_status,vehicle_id,approved_by from policy where  vehicle_id=? and policy_status='A'",
				new PolicyRowMapper(), vehicleId);
	}

	public Policy getPolicyByVehicle(String regNum) {
		if (jdbcTemplate.queryForObject(
				"select count(p.policy_id) from policy p,vehicles v where p.vehicle_id=v.vehicle_id and v.reg_num=? and v.status='A' and p.policy_status='A'",
				Integer.class, regNum) == 0) {
			return new Policy();
		}
		return jdbcTemplate.queryForObject(
				"select p.policy_id,p.policy_term,p.policy_type,p.premium_amount,p.policy_amount,p.start_date,p.end_date,p.policy_status,p.vehicle_id,p.approved_by from policy p,vehicles v where p.vehicle_id=v.vehicle_id and v.reg_num=? and v.status='A' and p.policy_status='A'",
				new PolicyRowMapper(), regNum);
	}

	public int getPolicyByVehicleIdCount(int vehicleId) throws Exception {
		return jdbcTemplate.queryForObject(
				"select count(policy_id) from policy where vehicle_id=? and policy_status='A'", Integer.class,
				vehicleId);
	}

	public List<Policy> getPolicyByUser(String username) {
		return jdbcTemplate.query(
				"select p.policy_id,p.policy_term,p.policy_type,p.premium_amount,p.policy_amount,p.start_date,p.end_date,p.policy_status,p.vehicle_id,p.approved_by from policy p,vehicles v,users u,customers c where p.vehicle_id=v.vehicle_id and v.customer_id=c.customer_id and c.customer_id=u.customer_id and u.username=? and v.status='A'",
				new PolicyRowMapper(), username);
	}

	public ArrayList<Object> getPolicyReport(int policyId) {
		if (jdbcTemplate.queryForObject("select count(policy_id) from policy where policy_id=?", Integer.class,
				policyId) <= 0) {
			return new ArrayList<>();
		}
		ArrayList<Object> policyDetails = new ArrayList<Object>();
		Customer c = jdbcTemplate.queryForObject(
				"select c.customer_id,c.name,c.email,c.contact,c.gender,c.age,c.occupation,c.income,c.address,c.status,c.customer_updated_on,c.customer_updated_by,c.created_by from policy p,vehicles v,customers c where p.vehicle_id=v.vehicle_id and v.customer_id=c.customer_id and p.policy_id=?",
				new CustomerRowMapper(), policyId);
		Vehicle v = jdbcTemplate.queryForObject(
				"select v.vehicle_id,v.chasis_no,v.reg_num,v.vehicle_model,v.purchase_date,v.vehicle_updated_on,v.vehicle_updated_by,v.customer_id,v.created_by,v.status from policy p,vehicles v where p.vehicle_id=v.vehicle_id and p.policy_id=?",
				new VehicleRowMapper(), policyId);
		Policy p = getPolicyById(policyId);
		policyDetails.add(c);
		policyDetails.add(v);
		policyDetails.add(p);
		if (p.getPolicyStatus().equals(PolicyStatus.Active)) {
			return policyDetails;
		} else {
			return new ArrayList<>();
		}
	}

}