package com.VIMS.VIMSBackend.Model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ClaimFormModel {
	private int ClaimId;
	private int UserId;
	private int InsuranceId;
	private String Proof;
	private double ClaimAmount;
	private Date ClaimedDate;
	private Date AcceptedDate;
	private int IssueInsuranceId;
	private ClaimFormStatus claimstatus;
	
}
