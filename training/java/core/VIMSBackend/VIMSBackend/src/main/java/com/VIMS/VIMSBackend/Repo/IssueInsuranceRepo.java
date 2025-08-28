package com.VIMS.VIMSBackend.Repo;

import com.VIMS.VIMSBackend.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Repository
public class IssueInsuranceRepo implements IssueInsuranceInterface {

    private ClaimFormModel claimmodel;

    @Autowired
    private JdbcTemplate template;

    @Override
    public boolean issueinsurence(IssueInsuranceModel issueinsurance) {
        String issuesql = "insert into issueinsurance (InsuranceId, UserId, StartDate, EndDate, EstimationDate, AmountPaid, RemainingAmount, IssueStatus, PaymentType, AdminName, AdminId, ChasisNumber, RegistrationNumber, remainingperiod, UpgradeRequestDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int issue = template.update(issuesql,
                issueinsurance.getInsuranceId(),
                issueinsurance.getUserId(),
                issueinsurance.getStartDate(),
                issueinsurance.getEndDate(),
                issueinsurance.getEstimationDate(),
                issueinsurance.getAmountPaid(),
                issueinsurance.getRemainingAmount(),
                issueinsurance.getIssueStatus().getType(),
                issueinsurance.getPaymentType().getType(),
                issueinsurance.getAdminName(),
                issueinsurance.getAdminId(),
                issueinsurance.getChasisNumber(),
                issueinsurance.getRegistrationNumber(),
                issueinsurance.getRemainingperiod(),
                Date.valueOf(LocalDate.now())
        );
        return issue > 0;
    }

    @Override
    public List<IssueInsuranceModel> viewallinsurences() {
        String viewsql = "select IssueInsuranceId, InsuranceId, UserId, StartDate, EndDate, EstimationDate, AmountPaid, RemainingAmount, IssueStatus, PaymentType, AdminName, AdminId, ChasisNumber, RegistrationNumber, ModifiedbyId, ModifiedDate, remainingperiod, UpgradeRequestDate,upgradeRequestStatus FROM issueinsurance";
        List<IssueInsuranceModel> viewallissue = template.query(viewsql, new IssueinsuranceRowMapper());
        return viewallissue;
    }

    public static final class IssueinsuranceRowMapper implements RowMapper<IssueInsuranceModel> {
        @Override
        public IssueInsuranceModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            IssueInsuranceModel issue = new IssueInsuranceModel();
            issue.setIssueInsuranceId(rs.getInt("IssueInsuranceId"));
            issue.setInsuranceId(rs.getInt("InsuranceId"));
            issue.setUserId(rs.getInt("UserId"));
            issue.setStartDate(rs.getDate("StartDate"));
            issue.setEndDate(rs.getDate("EndDate"));
            issue.setEstimationDate(rs.getDate("EstimationDate"));
            issue.setAmountPaid(rs.getDouble("AmountPaid"));
            issue.setRemainingAmount(rs.getDouble("RemainingAmount"));
            issue.setIssueStatus(IssueInsuranceStatus.getstatus(rs.getString("IssueStatus")));
            issue.setPaymentType(IssuePayment.getstatus(rs.getString("PaymentType")));
            issue.setAdminName(rs.getString("AdminName"));
            issue.setAdminId(rs.getInt("AdminId"));
            issue.setChasisNumber(rs.getString("ChasisNumber"));
            issue.setRegistrationNumber(rs.getString("RegistrationNumber"));
            issue.setModifiedbyId(rs.getInt("ModifiedbyId"));
            issue.setModifiedDate(rs.getDate("ModifiedDate"));
            issue.setRemainingperiod(rs.getInt("remainingperiod"));
            issue.setUpgradeRequestDate(rs.getDate("UpgradeRequestDate"));
            issue.setUpgradeRequestStatus(upgradeRequestStatus.getstatus(rs.getString("upgradeRequestStatus")));
            return issue;
        }
    }
    

    @Override
    public boolean Upgradeissueinsurance(IssueInsuranceModel issue, int InsuranceId) {
            String insertLog ="insert into issueinsurance_log (IssueInsuranceId, InsuranceId, UserId, StartDate, EndDate, EstimationDate, AmountPaid, RemainingAmount, IssueStatus, PaymentType, AdminName, AdminId, ChasisNumber, RegistrationNumber, ModifiedbyId, ModifiedDate, remainingperiod, UpgradeRequestDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            template.update(insertLog, issue.getIssueInsuranceId(), InsuranceId, issue.getUserId(), issue.getStartDate(), issue.getEndDate(), issue.getEstimationDate(), issue.getAmountPaid(), issue.getRemainingAmount(), issue.getIssueStatus().getType(), issue.getPaymentType().getType(), issue.getAdminName(), issue.getAdminId(), issue.getChasisNumber(), issue.getRegistrationNumber(), issue.getModifiedbyId(), issue.getModifiedDate(), issue.getRemainingperiod(), Date.valueOf(LocalDate.now()));

            String updateSql = "update issueinsurance set InsuranceId=?, AmountPaid=?, RemainingAmount=?, ModifiedDate=?, remainingperiod=?,upgradeRequestStatus=? WHERE IssueInsuranceId=?";
            int result = template.update(updateSql, InsuranceId, issue.getAmountPaid(), issue.getRemainingAmount(), issue.getModifiedDate(), issue.getRemainingperiod(),issue.getUpgradeRequestStatus().getType(),issue.getIssueInsuranceId());
            return result > 0;
        
             
        
    }

    @Override
    public boolean claimupdate(IssueInsuranceModel issue, String proof, double ClaimAmount) {
        String insertLog = "insert into issueinsurance_log (IssueInsuranceId, InsuranceId, UserId, StartDate, EndDate, EstimationDate, AmountPaid, RemainingAmount, IssueStatus, PaymentType, AdminName, AdminId, ChasisNumber, RegistrationNumber, ModifiedbyId, ModifiedDate, remainingperiod, UpgradeRequestDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(insertLog, issue.getIssueInsuranceId(), issue.getInsuranceId(), issue.getUserId(), issue.getStartDate(), issue.getEndDate(), issue.getEstimationDate(), issue.getAmountPaid(), issue.getRemainingAmount(), issue.getIssueStatus().getType(), issue.getPaymentType().getType(), issue.getAdminName(), issue.getAdminId(), issue.getChasisNumber(), issue.getRegistrationNumber(), issue.getModifiedbyId(), issue.getModifiedDate(), issue.getRemainingperiod(), issue.getUpgradeRequestDate());

        String updateSql = "update issueinsurance set RemainingAmount=?, ModifiedDate=? WHERE IssueInsuranceId=?";
        template.update(updateSql, issue.getRemainingAmount(), issue.getModifiedDate(), issue.getIssueInsuranceId());

        String claimSql = "insert into claimform ( UserId, InsuranceId, Proof, ClaimAmount, ClaimedDate, AcceptedDate, IssueInsuranceId, claimstatus) VALUES (?,?,?,?,?,?,?,?)";
        template.update(claimSql, issue.getUserId(), issue.getInsuranceId(), proof, ClaimAmount, Date.valueOf(LocalDate.now()), claimmodel.getAcceptedDate(), issue.getIssueInsuranceId(), ClaimFormStatus.PENDING.getType());

        return true;
    }

    @Override
    public IssueInsuranceModel getIssueInsuranceById(int IssueInsuranceId) {
        String query = "select IssueInsuranceId, InsuranceId, UserId, StartDate, EndDate, EstimationDate, AmountPaid, RemainingAmount, IssueStatus, PaymentType, AdminName, AdminId, ChasisNumber, RegistrationNumber, ModifiedbyId, ModifiedDate, remainingperiod, UpgradeRequestDate,upgradeRequestStatus FROM issueinsurance WHERE IssueInsuranceId=?";
        return template.queryForObject(query, new IssueinsuranceRowMapper(), IssueInsuranceId);
    }

    @Override
    public boolean UpdateIssueInsurance(IssueInsuranceModel issue) {
        String insertLog = "insert into issueinsurance_log (IssueInsuranceId, InsuranceId, UserId, StartDate, EndDate, EstimationDate, AmountPaid, RemainingAmount, IssueStatus, PaymentType, AdminName, AdminId, ChasisNumber, RegistrationNumber, ModifiedbyId, ModifiedDate, remainingperiod, UpgradeRequestDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(insertLog, issue.getIssueInsuranceId(), issue.getInsuranceId(), issue.getUserId(), issue.getStartDate(), issue.getEndDate(), issue.getEstimationDate(), issue.getAmountPaid(), issue.getRemainingAmount(), issue.getIssueStatus().getType(), issue.getPaymentType().getType(), issue.getAdminName(), issue.getAdminId(), issue.getChasisNumber(), issue.getRegistrationNumber(), issue.getModifiedbyId(), issue.getModifiedDate(), issue.getRemainingperiod(), issue.getUpgradeRequestDate());

        String updateSql = "update issueinsurance set IssueStatus=?, ModifiedDate=? WHERE IssueInsuranceId=?";
        int result = template.update(updateSql, issue.getIssueStatus().getType(), issue.getModifiedDate(), issue.getIssueInsuranceId());

        return result > 0;
    }
    @Override
    public IssueInsuranceModel getUniquebyids(int adminId,int insuranceId,int userId) {
    	String query="select IssueInsuranceId, InsuranceId,UserId,StartDate,StartDate,EstimationDate,AmountPaid,RemainingAmount,IssueStatus,PaymentType,AdminName,AdminId,ChasisNumber,RegistrationNumber,ModifiedbyId,ModifiedDate,remainingperiod,UpgradeRequestDate,upgradeRequestStatus from issueinsurance where adminId=? and insuranceId=? and userId=?";
    	IssueInsuranceModel issue=template.queryForObject(query, new IssueinsuranceRowMapper1(),adminId,insuranceId,userId);
    	return issue;
   
    }
    
    public static final class IssueinsuranceRowMapper1 implements RowMapper<IssueInsuranceModel> {
        @Override
        public IssueInsuranceModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            IssueInsuranceModel issue = new IssueInsuranceModel();
            issue.setIssueInsuranceId(rs.getInt("IssueInsuranceId"));
            issue.setInsuranceId(rs.getInt("InsuranceId"));
            issue.setUserId(rs.getInt("UserId"));
            issue.setStartDate(rs.getDate("StartDate"));
            issue.setEndDate(rs.getDate("EndDate"));
            issue.setEstimationDate(rs.getDate("EstimationDate"));
            issue.setAmountPaid(rs.getDouble("AmountPaid"));
            issue.setRemainingAmount(rs.getDouble("RemainingAmount"));
            issue.setIssueStatus(IssueInsuranceStatus.getstatus(rs.getString("IssueStatus")));
            issue.setPaymentType(IssuePayment.getstatus(rs.getString("PaymentType")));
            issue.setAdminName(rs.getString("AdminName"));
            issue.setAdminId(rs.getInt("AdminId"));
            issue.setChasisNumber(rs.getString("ChasisNumber"));
            issue.setRegistrationNumber(rs.getString("RegistrationNumber"));
            issue.setModifiedbyId(rs.getInt("ModifiedbyId"));
            issue.setModifiedDate(rs.getDate("ModifiedDate"));
            issue.setRemainingperiod(rs.getInt("remainingperiod"));
            issue.setUpgradeRequestDate(rs.getDate("UpgradeRequestDate"));
            issue.setUpgradeRequestStatus(upgradeRequestStatus.getstatus(rs.getString("upgradeRequestStatus")));
            return issue;
        }
    }

}