package com.VIMS.VIMSBackend.Model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssueInsuranceModel {
	@NotNull(message="issueinsurance id should not be null")

	private int IssueInsuranceId;
	@NotNull(message="user id should not be null")

	private int UserId;
	@NotNull(message="insurance id should not be null")

	private int InsuranceId;
	@NotNull(message="start date should not be null")
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date StartDate;
	@DateTimeFormat(pattern="yyyy-mm-dd")

	private Date EndDate;
	@NotNull(message="estimation date should not be null")
	@DateTimeFormat(pattern="yyyy-mm-dd")

	private Date EstimationDate;
	private double RemainingAmount;
	@NotNull(message="start Amount paid should not be null")

	private double AmountPaid;
	@NotNull(message="issue status should not be null")

	private IssueInsuranceStatus IssueStatus;
	@NotNull(message="start date should not be null")
	
	private IssuePayment PaymentType;
	@NotEmpty(message="admin name should not be null")
	
	private String AdminName;
	@NotNull(message="admin id should not be null")
	private int AdminId;
	@NotEmpty(message="chasis number should not be null")
	private String ChasisNumber;
	@NotEmpty(message="resgistration number should not be null")
	private String RegistrationNumber;
	private int ModifiedbyId;
	@DateTimeFormat(pattern="yyyy-mm-dd")

	private Date ModifiedDate;
	private int remainingperiod;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date UpgradeRequestDate;
}
