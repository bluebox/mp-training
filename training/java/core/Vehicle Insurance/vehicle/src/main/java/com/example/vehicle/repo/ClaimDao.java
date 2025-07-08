package com.example.vehicle.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vehicle.model.Claim;

@Repository
public class ClaimDao {
	public JdbcTemplate jdbcTemplate; 
	@Autowired
	public ClaimDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public String claimInsurance(double reqAmount,String damageType,int policyId) {
		int rowsEffected=jdbcTemplate.update("insert into claim values(?,?,?,?,?)",reqAmount,damageType,'I',LocalDateTime.now(),policyId);
		if(rowsEffected>0) {
			return "Wait for insurance to claim";
		}
		else {
			return "Failed to provide insurance";
		}
	}
	public boolean isEligible(int policyId) {
		LocalDateTime lastClaimDate=jdbcTemplate.queryForObject("select Max(c.claim_date) from claim c,policy p where c.policy_id=p.policy_id and c.policy_id=? and p.policy_status='A' and c.claim_status='A' ", LocalDateTime.class,policyId);
		return (lastClaimDate.plusMonths(6)).isBefore(LocalDateTime.now()) ;
	}
	public String updateStatus(int claimId,double amount,char status) {
		int x=jdbcTemplate.update("update claim set req_amount=? ,claim_status=?,claim_date=? where claim_id=?",amount,status,LocalDateTime.now());
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
	public List<Claim> getAllClaims(){
		return jdbcTemplate.queryForList("select * from claim",Claim.class);
	}
	public Claim getClaimById(int claimId) {
		return jdbcTemplate.queryForObject("select * from claim where claim_id=?", Claim.class,claimId);
	}
	public List<Claim> getClaimByUser(String username){
		return jdbcTemplate.queryForList("select c from claim c,policy p,vehicles v,customer cu,user u where c.policy_id=p.policy_id,p.vehicle_id=v.vehicle_id,v.customer_id=cu.customer_id,cu.customer_id=u.customer_id and u.username=?",Claim.class,username);
	}
}
