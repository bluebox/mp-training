package com.VIMS.VIMSBackend.Repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.VIMS.VIMSBackend.Model.InsuranceModel;

@Repository
public class InsuranceRepo implements InsuranceRepoInterface {

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public int CreateInsurance(InsuranceModel insurance) {
		String query="INSERT INTO insurance (PeriodLength, InsuranceAmount, VehicleType, MonthlyEMI, InsuranceStatus, ValidPeriod, CreatedBy, CreatedDate, NCBPercentage) VALUES (?,?,?,?,?,?,?,?,?)";
		int value=jdbcTemplate.update(query,insurance.getPeriodLength(),insurance.getInsuranceAmount(),insurance.getVehicleType().getType(),insurance.getMonthlyEMI(),insurance.getInsuranceStatus().getType(),insurance.getValidPeriod(),insurance.getCreatedBy(),insurance.getCreatedDate(),insurance.getNCB());
		if(value>0) {
			return value;
		}
		return 0;
	}

	@Override
	public int updateInsurance(InsuranceModel insurance) {
		String query="update insurance set PeriodLength=?,InsuranceAmount=?,VehicleType=?,MonthlyEMI=?,ValidPeriod=?,CreatedBy=?,CreatedDate=?,ModifiedBy=?,ModifiedDate=?,NCBPercentage=? where InsuranceId=?";
		String query_log="insert into insurance_log (InsuranceId,PeriodLength,InsuranceAmount,VehicleType,MonthlyEMI,InsuranceStatus,ValidPeriod,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,NCBPercentage) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		int value=jdbcTemplate.update(query,insurance.getPeriodLength(),insurance.getInsuranceAmount(),insurance.getVehicleType().getType(),insurance.getMonthlyEMI(),insurance.getValidPeriod(),insurance.getCreatedBy(),insurance.getCreatedDate(),insurance.getModifiedBy(),insurance.getModifiedDate(),insurance.getNCB(),insurance.getInsuranceId());
		int valuelog=jdbcTemplate.update(query_log,insurance.getInsuranceId(),insurance.getPeriodLength(),insurance.getInsuranceAmount(),insurance.getVehicleType().getType(),insurance.getMonthlyEMI(),insurance.getInsuranceStatus().getType(),insurance.getValidPeriod(),insurance.getCreatedBy(),insurance.getCreatedDate(),insurance.getModifiedBy(),insurance.getModifiedDate(),insurance.getNCB());
				if(valuelog >0 && value>0) {
					return 1;
				}
				return 0;
	}

	@Override
	public int inactiveInsurance(InsuranceModel insurance) { // in service layer get 
		String query="update insurance set InsuranceStatus=?,ModifiedBy=?,ModifiedDate=? where InsuranceId=?";
		String query_log="insert into insurance_log (InsuranceId,PeriodLength,InsuranceAmount,VehicleType,MonthlyEMI,InsuranceStatus,ValidPeriod,NCBPercentage,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		int value=jdbcTemplate.update(query,insurance.getInsuranceStatus().getType(),insurance.getModifiedBy(),insurance.getModifiedDate(),insurance.getInsuranceId());
		int valuelog=jdbcTemplate.update(query_log,insurance.getInsuranceId(),insurance.getPeriodLength(),insurance.getInsuranceAmount(),insurance.getVehicleType().getType(),insurance.getMonthlyEMI(),insurance.getInsuranceStatus().getType(),insurance.getValidPeriod(),insurance.getNCB(),insurance.getCreatedBy(),insurance.getCreatedDate(),insurance.getModifiedBy(),insurance.getModifiedDate());
		if(valuelog>0 && value>0) {
			return 1;
		}
		return 0;
	}

	@Override
	public InsuranceModel getInsuranceById(int InsuranceId) {
		String query="select InsuranceId,PeriodLength,InsuranceAmount,VehicleType,MonthlyEMI,InsuranceStatus,ValidPeriod,NCBPercentage,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate from insurance where InsuranceId=?";
	    InsuranceModel insurance =jdbcTemplate.queryForObject(query,new InsuranceRowMapper(),InsuranceId);
	    if(insurance != null)return insurance;
		return null;
	}
	
	@Override
	public List<InsuranceModel> getAllInsurances(){
		String query="select InsuranceId,PeriodLength,InsuranceAmount,VehicleType,MonthlyEMI,InsuranceStatus,ValidPeriod,NCBPercentage,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate from insurance";
	    List<InsuranceModel> insurances =jdbcTemplate.query(query,new InsuranceRowMapper());
	    if(insurances != null) {
	    	return insurances;
	    }
	    return null;
	}

}