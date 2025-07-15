package com.example.vehicle.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.model.Claim;
import com.example.vehicle.rowMappers.ClaimRowMapper;

@Repository
public class ClaimDao {
	private final JdbcTemplate jdbcTemplate;
	private final PolicyDao repo;
	@Autowired
	public ClaimDao(JdbcTemplate jdbcTemplate,PolicyDao repo) {
		this.jdbcTemplate = jdbcTemplate;
		this.repo=repo;
	}

	public String claimInsurance(double reqAmount, String damageType, int policyId,String approvedBy) {
		
		if(!validate(approvedBy)) {
			return "No approvedBy reference in Admin or Users";
		}
		
		String status=jdbcTemplate.queryForObject("select policy_status from policy where policy_id=?",String.class,policyId);
		if(status.equals("R")||status.equals("I")) {
			return "Your Policy is Not Active to Claim Insurance";
		}
		
		int rowsEffected = jdbcTemplate.update("insert into claim(req_amount,damage_type,claim_status,claim_date,policy_id,approved_by) values(?,?,?,?,?,?)", reqAmount, damageType, "I", LocalDateTime.now(), policyId,approvedBy);
		if (rowsEffected > 0) {
			return "Wait for insurance to claim";
		} else {
			return "Failed to provide insurance";
		}
	}

	public boolean isEligible(int policyId) {
		LocalDateTime lastClaimDate = jdbcTemplate.queryForObject(
				"select Max(c.claim_date) from claim c,policy p where c.policy_id=p.policy_id and c.policy_id=? and p.policy_status='A' and (c.claim_status='A' or c.claim_status='I')",
				LocalDateTime.class, policyId);
		if(lastClaimDate==null) {
			return true;
		}
		return (lastClaimDate.plusMonths(6)).isBefore(LocalDateTime.now());
	}

	public String updateStatus(int claimId, double amount, char status, String approvedBy) {
		
		if(!validate(approvedBy)) {
			return "No approvedBy reference in Admin or Users";
		}
		
		double premiumAmount=jdbcTemplate.queryForObject("select p.premium_amount from policy p,claim c where p.policy_id=c.policy_id and c.claim_id=?", Double.class,claimId);
		if(premiumAmount<amount) {
			amount=premiumAmount;
		}
		int rowsAffected = jdbcTemplate.update(
				"update claim set req_amount=? ,claim_status=?,claim_date=?,approved_by=? where claim_id=?", amount,
				Character.toString(status), LocalDateTime.now(), approvedBy, claimId);
		if (rowsAffected > 0) {
			if (status == 'A') {
				return "Accepted";
			} else {
				return "Rejected";
			}
		} else {
			return "Failed to update status";
		}
	}

	public Boolean validate(String approvedBy) {
		List<String> adminUsernames=jdbcTemplate.queryForList("select username from admin",String.class);
		List<String> userUsernames=jdbcTemplate.queryForList("select username from users",String.class);
		boolean adminBool=adminUsernames.contains(approvedBy);
		boolean userBool=userUsernames.contains(approvedBy);
		
		return adminBool||userBool;
	}
	
	public List<Claim> getAllClaims() {
		return jdbcTemplate.query("select claim_id,req_amount,damage_type,claim_date,policy_id,approved_by from claim", new ClaimRowMapper());
	}

	public Claim getClaimById(int claimId) {
		return jdbcTemplate.queryForObject("select claim_id,req_amount,damage_type,claim_date,policy_id,approved_by from claim where claim_id=?", new ClaimRowMapper() , claimId);
	}
	public List<Claim> getClaimByPolicyId(int policyId){
		return jdbcTemplate.query("select c.claim_id,c.req_amount,c.damage_type,c.claim_date,c.policy_id,c.approved_by from claim c,policy p where c.policy_id=p.policy_id and p.policy_id=? and p.policy_status=?", new ClaimRowMapper(),policyId);
	}
	public List<Claim> getClaimByVehicleId(int vehiceId){
		return jdbcTemplate.query("select c.claim_id,c.req_amount,c.damage_type,c.claim_date,c.policy_id,c.approved_by from claim c,policy p where c.policy_id=p.policy_id and p.vehicle_id=? and p.policy_status=?", new ClaimRowMapper(),vehiceId);
	}
	public List<Claim> getClaimByUser(String username) {
		return jdbcTemplate.query(
				"select c.claim_id,c.req_amount,c.damage_type,c.claim_date,c.policy_id,c.approved_by from claim c,policy p,vehicles v,customers cu,users u where c.policy_id=p.policy_id and p.vehicle_id=v.vehicle_id and v.customer_id=cu.customer_id and cu.customer_id=u.customer_id and u.username=?",
				new ClaimRowMapper(), username);
	}
	public List<Claim> getAllIntiatedClaims() throws SQLException {
		return jdbcTemplate.query("select claim_id,req_amount,damage_type,claim_date,policy_id,approved_by from claim where claim_status='I'",new ClaimRowMapper());
	}
	public ArrayList<Object> getClaimReport(int claimId) throws SQLException {
		if(jdbcTemplate.queryForObject("select count(claim_id) from claim where claim_id=?", Integer.class,claimId)<=0) {
			return new ArrayList<>();
		}
		int policyId=jdbcTemplate.queryForObject("select policy_id from claim c where claim_id=?", Integer.class,claimId);
		ArrayList<Object> claimReport = repo.getPolicyReport(policyId);
		if(claimReport.equals(new ArrayList<>())) {
			return claimReport;
		}
		claimReport.add(jdbcTemplate.queryForObject("select claim_id,req_amount,damage_type,claim_date,policy_id,approved_by from claim where claim_id=?", new ClaimRowMapper(),claimId));
		return claimReport;
	}
}
