package com.VIMS.VIMSBackend.Repo;
import java.util.List;

import com.VIMS.VIMSBackend.Model.IssueInsuranceModel;
	public interface IssueInsuranceInterface {

	    boolean issueinsurence(IssueInsuranceModel issueinsurance);

	    List<IssueInsuranceModel> viewallinsurences();

	    boolean Upgradeissueinsurance(IssueInsuranceModel issue,int InsuranceId);

	    boolean claimupdate(IssueInsuranceModel issue,String proof,double ClaimAmount);

	    IssueInsuranceModel getIssueInsuranceById(int issueInsuranceId);
		 boolean UpdateIssueInsurance(IssueInsuranceModel issue);
		 IssueInsuranceModel getUniquebyids(int adminId,int insuranceId,int userId);

	}
