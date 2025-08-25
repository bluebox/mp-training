package com.VIMS.VIMSBackend.Service;

import com.VIMS.VIMSBackend.Model.*;
import com.VIMS.VIMSBackend.Repo.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueInsuranceService implements IssueInsuranceInterface {

    private Timer timer;

    @Autowired
    private UserRepo userrepo;

    @Autowired
    private IssueInsuranceRepo repo;

    @Autowired
    private InsuranceRepo insuranceRepo;

    
    private ClaimFormModel claimmodel;

    public IssueInsuranceService() {
        this.timer = new Timer();
    }

    @Override
    public boolean issueInsurance(IssueInsuranceModel issue, int insuranceId, int adminid, int userid) {
    	IssueInsuranceModel model=(repo.getUniquebyids(adminid, insuranceId, userid));
    	if (model != null && model.getIssueStatus() == IssueInsuranceStatus.NOTACCEPTED) {
        UserModel adminbyid = userrepo.getUserById(adminid);
        InsuranceModel insurance = insuranceRepo.getInsuranceById(insuranceId);
        issue.setUserId(userid);
        issue.setAdminId(adminid);

        LocalDate sdate = LocalDate.now();
        Date startDate = Date.valueOf(sdate);

        LocalDate edate = sdate.plusYears(insurance.getValidPeriod());
        Date estimationDate = Date.valueOf(edate);

        issue.setInsuranceId(insurance.getInsuranceId());
        issue.setStartDate(startDate);
        issue.setEstimationDate(estimationDate);
        issue.setAmountPaid(insurance.getMonthlyEMI());
        int remainingperiod = (insurance.getValidPeriod() * 12) / (insurance.getPeriodLength());
        issue.setRemainingperiod(remainingperiod - 1);
        issue.setIssueStatus(IssueInsuranceStatus.ACTIVE);
        issue.setRemainingAmount(insurance.getInsuranceAmount());
        issue.setAdminName(adminbyid.getUserFirstname());

        boolean issuedornot = repo.issueinsurence(issue);
        return issuedornot;
    	}
    	UserModel adminbyid = userrepo.getUserById(adminid);
        InsuranceModel insurance = insuranceRepo.getInsuranceById(insuranceId);
        issue.setUserId(userid);
        issue.setAdminId(adminid);
        issue.setUpgradeRequestDate(Date.valueOf(LocalDate.now()));
        issue.setIssueStatus(IssueInsuranceStatus.NOTACCEPTED);

        boolean issuedornot = repo.issueinsurence(issue);
        return issuedornot;
        
    	
    	
    	
        
    }

    @Override
    public List<IssueInsuranceModel> viewAllInsurances() {
        List<IssueInsuranceModel> issues = repo.viewallinsurences();

        for (IssueInsuranceModel issue : issues) {
            TimerTask task = UpdateIssueInsurance(issue);
            Date scheduleDate = issue.getEstimationDate();
            if (scheduleDate != null && scheduleDate.after(new Date(System.currentTimeMillis()))) {
                timer.schedule(task, scheduleDate);
            }
        }

        return issues;
    }

    @Override
    public boolean upgradeIssueInsurance( int InsuranceId,int IssueInsuranceId) {
    	IssueInsuranceModel issueInsurance=repo.getIssueInsuranceById(IssueInsuranceId);
    	issueInsurance.setInsuranceId(InsuranceId);
        InsuranceModel insurance = insuranceRepo.getInsuranceById(InsuranceId);
        issueInsurance.setIssueStatus(IssueInsuranceStatus.ACTIVE);
        double amountPaid = insurance.getMonthlyEMI() - issueInsurance.getAmountPaid();
        issueInsurance.setAmountPaid(amountPaid + issueInsurance.getAmountPaid());
        issueInsurance.setRemainingAmount(insurance.getInsuranceAmount() - amountPaid + issueInsurance.getAmountPaid());
        int remainingperiod = (insurance.getValidPeriod() * 12) / (insurance.getPeriodLength());
        issueInsurance.setRemainingperiod(remainingperiod - 1);

        LocalDate startDate = issueInsurance.getStartDate().toLocalDate();
        LocalDate estimation = startDate.plusYears(insurance.getValidPeriod());
        issueInsurance.setEstimationDate(Date.valueOf(estimation));
        issueInsurance.setModifiedDate(Date.valueOf(LocalDate.now()));

        return repo.Upgradeissueinsurance(issueInsurance, InsuranceId);
    }

    @Override
    public boolean updateClaim( String proof, double ClaimAmount,int issueInsuranceId) {
        if (!proof.isBlank()) {
        	IssueInsuranceModel issue=repo.getIssueInsuranceById(issueInsuranceId);
            double remainingAmount = issue.getRemainingAmount() - ClaimAmount;
            issue.setRemainingAmount(remainingAmount);
            issue.setModifiedDate(Date.valueOf(LocalDate.now()));

            claimmodel.setAcceptedDate(Date.valueOf(LocalDate.now()));
            claimmodel.setClaimstatus(ClaimFormStatus.COMPLETED);

            return repo.claimupdate(issue, proof, ClaimAmount);
        }
        return false;
    }

    @Override
    public IssueInsuranceModel getIssueInsuranceById(int issueInsuranceId) {
        return repo.getIssueInsuranceById(issueInsuranceId);
    }

    public TimerTask UpdateIssueInsurance(IssueInsuranceModel issue) {
        return new TimerTask() {
            @Override
            public void run() {
                if (issue.getRemainingperiod() == 0) {
                    issue.setIssueStatus(IssueInsuranceStatus.INACTIVE);
                }

                LocalDate today = LocalDate.now();
                LocalDate estimation = issue.getEstimationDate().toLocalDate();
                if (today.isAfter(estimation)) {
                    issue.setIssueStatus(IssueInsuranceStatus.PENDING);
                }

                repo.UpdateIssueInsurance(issue);
            }
        };
    }

    public List<IssueInsuranceModel> getallIssueInsurancesbystatus(IssueInsuranceStatus status) {
        return repo.viewallinsurences()
                .stream()
                .filter(issue -> issue.getIssueStatus().equals(status))
                .collect(Collectors.toList());
    }
    
    
    
    public boolean updateIssueInsurance(IssueInsuranceModel issue) {
        return repo.UpdateIssueInsurance(issue); 
    }

    public List<IssueInsuranceModel> getNotAcceptedRequestsByAdmin(int adminId) {
        return repo.viewallinsurences()
                .stream()
                .filter(issue -> issue.getAdminId() == adminId && issue.getIssueStatus() == IssueInsuranceStatus.NOTACCEPTED)
                .collect(Collectors.toList());
    }

}
