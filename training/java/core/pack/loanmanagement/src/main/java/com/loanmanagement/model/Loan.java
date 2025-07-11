package com.loanmanagement.model;

import java.sql.Date;

public class Loan {
 
	
	 public Loan() {
		 
	 }
	public Loan(String loanType, double principle, double rateOfIntrest, int tenureInDays) {
	
		this.loanType = loanType;
		this.principle = principle;
		this.rateOfIntrest = rateOfIntrest;
		this.tenureInDays = tenureInDays;
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

	public double getRateOfIntrest() {
		return rateOfIntrest;
	}

	public void setRateOfIntrest(double rateOfIntrest) {
		this.rateOfIntrest = rateOfIntrest;
	}

	public int getTenureInDays() {
		return tenureInDays;
	}

	public void setTenureInDays(int tenureInDays) {
		this.tenureInDays = tenureInDays;
	}
	public void setLoanId(int loanId) {
		this.loanId=loanId;
	}
	public int getLoanId() {
		return loanId;
	}
    private int loanId;
	private String loanType;
	private double principle;
	private double rateOfIntrest;
	private int tenureInDays;

}
