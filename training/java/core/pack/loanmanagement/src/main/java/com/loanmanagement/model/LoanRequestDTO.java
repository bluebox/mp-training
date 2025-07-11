package com.loanmanagement.model;

import java.sql.Date;

public class LoanRequestDTO {

	private int personId;
	private int loanId;
	private String loanType;
	private double principle;
	private double rate;
	private int tenureInDays;
	private double principlePortion;

	public double getPrinciplePortion() {
		return principlePortion;
	}
	public void setPrinciplePortion(double principlePortion) {
		this.principlePortion = principlePortion;
	}
	public int getPersonId() {
		return personId;
	}
	public LoanRequestDTO() {
		
	}
	public LoanRequestDTO(int personId, int loanId, Date startDate) {

		this.personId = personId;
		this.loanId = loanId;
		this.startDate = startDate;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public double getPrinciple() {
		return principle;
	}

	public void setPrinciple(double principle) {
		this.principle = principle;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getTenureInDays() {
		return tenureInDays;
	}

	public void setTenureInDays(int tenureInDays) {
		this.tenureInDays = tenureInDays;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Double getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(Double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getLateFee() {
		return lateFee;
	}

	public void setLateFee(Double lateFee) {
		this.lateFee = lateFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private Date startDate;
	private Double emi;
	private Double amountPaid;
	private Double outstandingBalance;
	private Date dueDate;
	private Double lateFee;
	private String status;

}
