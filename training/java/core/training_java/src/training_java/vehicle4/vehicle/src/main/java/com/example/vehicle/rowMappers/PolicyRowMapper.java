package com.example.vehicle.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.vehicle.enums.PolicyStatus;
import com.example.vehicle.model.Policy;

public class PolicyRowMapper implements RowMapper<Policy> {

	@Override
	public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {
		Policy policy = new Policy();
		policy.setApprovedBy(rs.getString("approved_by"));
		policy.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
		policy.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
		String status = rs.getString("policy_status");
		if ("I".equals(status)) {
			policy.setPolicyStatus(PolicyStatus.InActive);
		} else if ("A".equals(status)) {
			policy.setPolicyStatus(PolicyStatus.Active);
		} else {
			policy.setPolicyStatus(PolicyStatus.Requested);
		}
		policy.setPolicyAmount(rs.getDouble("policy_amount"));
		policy.setPremiumAmount(rs.getDouble("premium_amount"));
		policy.setPolicyId(rs.getInt("policy_id"));
		policy.setPolicyTerm(rs.getInt("policy_term"));
		policy.setVehicleId(rs.getInt("vehicle_id"));
		policy.setPolicyType(rs.getString("policy_type"));

		return policy;
	}

}
