package com.VIMS.VIMSBackend.Repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.VIMS.VIMSBackend.Model.InsuranceModel;
import com.VIMS.VIMSBackend.Model.InsuranceStatus;
import com.VIMS.VIMSBackend.Model.VehicleType;


public class InsuranceRowMapper implements RowMapper<InsuranceModel>{

	@Override
	public InsuranceModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InsuranceModel insurance=new InsuranceModel();
		insurance.setInsuranceId(rs.getInt("InsuranceId"));
		insurance.setPeriodLength(rs.getInt("PeriodLength"));
		insurance.setInsuranceAmount(rs.getDouble("InsuranceAmount"));
		insurance.setVehicleType(VehicleType.getVehicleType(rs.getString("VehicleType")));
		insurance.setMonthlyEMI(rs.getDouble("MonthlyEMI"));
		insurance.setInsuranceStatus(InsuranceStatus.getIssueStatus(rs.getString("InsuranceStatus")));
		insurance.setValidPeriod(rs.getInt("ValidPeriod"));
		insurance.setModifiedBy(rs.getInt("ModifiedBy"));
		insurance.setCreatedBy(rs.getInt("CreatedBy"));
		insurance.setCreatedDate(rs.getDate("CreatedDate"));
		insurance.setModifiedDate(rs.getDate("ModifiedDate"));
		insurance.setNCB(rs.getInt("NCBPercentage"));
		return insurance;
	}

}
