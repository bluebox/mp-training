package com.befit.app.rowmappers;

import com.befit.app.beans.MemberShip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberShipRowMapper implements RowMapper<MemberShip> {
	@Override
	public MemberShip mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberShip memberShip = new MemberShip();
		memberShip.setId(rs.getInt("id"));
		memberShip.setMembershipName(rs.getString("memberShipName"));
		memberShip.setCost(rs.getFloat("cost"));
		memberShip.setStartDate(rs.getDate("startDate"));
		memberShip.setEndDate(rs.getDate("endDate"));
		memberShip.setStatus(rs.getString("status"));
		return memberShip;
	}
}