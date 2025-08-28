package com.VIMS.VIMSBackend.Service;

import com.VIMS.VIMSBackend.Model.IssueInsuranceModel;
import java.util.List;

public interface IssueInsuranceInterface {

    boolean issueInsurance(IssueInsuranceModel issue, int insuranceId,int adminid,int userid);

    List<IssueInsuranceModel> viewAllInsurances();

    boolean upgradeIssueInsurance(int insuranceId,int IssueInsuranceId);

    boolean updateClaim(String proof,double ClaimAmount,int issueInsuranceId);

    IssueInsuranceModel getIssueInsuranceById(int issueInsuranceId);
    
    
}