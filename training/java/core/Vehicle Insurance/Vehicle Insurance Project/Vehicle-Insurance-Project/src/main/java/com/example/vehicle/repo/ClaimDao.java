package com.example.vehicle.repo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.model.Claim;

@Repository
public class ClaimDao {
	private final PolicyDao repo;
	public final JdbcTemplate jdbcTemplate; 
	@Autowired
	public ClaimDao(JdbcTemplate jdbcTemplate,PolicyDao repo) {
		this.jdbcTemplate=jdbcTemplate;
		this.repo=repo;
	}
	public String claimInsurance(double reqAmount,String damageType,int policyId) throws SQLException {
		int rowsEffected=jdbcTemplate.update("insert into claim values(?,?,?,?,?)",reqAmount,damageType,'I',LocalDateTime.now(),policyId);
		if(rowsEffected>0) {
			return "Wait for insurance to claim";
		}
		else {
			return "Failed to provide insurance";
		}
	}
	public boolean isEligible(int policyId) throws SQLException {
		if(repo.getPolicyById(policyId).getPolicyStatus()=='A') {
			LocalDateTime lastClaimDate=jdbcTemplate.queryForObject("select Max(c.claim_date) from claim c,policy p where c.policy_id=p.policy_id and c.policy_id=? and p.policy_status='A' and c.claim_status='A' ", LocalDateTime.class,policyId);
			return (lastClaimDate.plusMonths(6)).isBefore(LocalDateTime.now());
		}
		else {
			return false;
		}
	}
	public String updateStatus(int claimId,double amount,char status,String approvedBy) throws SQLException {
		int x=jdbcTemplate.update("update claim set req_amount=? ,claim_status=?,claim_date=?,approved_by=? where claim_id=?",amount,status,LocalDateTime.now(),approvedBy,claimId);
		if(x>0) {
			if(status=='A') {
				return "Accepted";
			}
			else {
				return "Rejected";
			}
		}
		else {
			return "Failed to update status";
		}
	}
	public List<Claim> getAllClaims() throws SQLException {
		return jdbcTemplate.queryForList("select * from claim",Claim.class);
	}
	public Claim getClaimById(int claimId) throws SQLException {
		return jdbcTemplate.queryForObject("select * from claim where claim_id=?", Claim.class,claimId);
	}
	public Claim getClaimByUser(String username) throws SQLException {
		return jdbcTemplate.queryForObject("select c from claim c,policy p,vehicles v,customer cu,user u where c.policy_id=p.policy_id,p.vehicle_id=v.vehicle_id,v.customer_id=cu.customer_id,cu.customer_id=u.customer_id and u.username=?",Claim.class,username);
	}
	public List<Claim> getAllIntiatedClaims() throws SQLException {
		return jdbcTemplate.queryForList("select * from claim where claim_status='I'",Claim.class);
	}
	public ArrayList<Object> getClaimReport(int claimId) throws SQLException {
		int policyId=jdbcTemplate.queryForObject("select policy_id from claim c where claim_id=?", Integer.class,claimId);
		ArrayList<Object> claimReport = repo.getPolicyReport(policyId);
		claimReport.add(jdbcTemplate.queryForObject("select * from claim where claim_id=?", Claim.class,claimId));
		return claimReport;
	}
}
