package com.example.vehicle.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.vehicle.enums.ClaimStatus;
import com.example.vehicle.model.Claim;

public class ClaimRowMapper implements RowMapper<Claim> {

	@Override
	public Claim mapRow(ResultSet rs, int rowNum) throws SQLException {
		Claim claim =new Claim();
		claim.setApprovedBy(rs.getString("approved_by"));
		claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
		claim.setClaimId(rs.getInt("claim_id"));
		String status=rs.getString("claim_status");
		if("I".equals(status)) {
			claim.setClaimStatus(ClaimStatus.Initaited);
		}
		else if("A".equals(status)) {
			claim.setClaimStatus(ClaimStatus.Accepted);
		}
		else {
			claim.setClaimStatus(ClaimStatus.Rejected);
		}
		claim.setDamageType(rs.getString("damage_type"));
		claim.setPolicyId(rs.getInt("policy_id"));
		claim.setReqAmount(rs.getDouble("req_amount"));
		
	
		return claim;
	}
	
}

