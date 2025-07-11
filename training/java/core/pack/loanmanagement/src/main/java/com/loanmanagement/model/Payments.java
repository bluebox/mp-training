package com.loanmanagement.model;

import java.time.LocalDateTime;

public class Payments {
	private int loanId;
	private int memberId;
	private String memberName;
	private double amountPaid;
	private String type;
	private LocalDateTime time;

	public Payments() {
		
	}
	public Payments(int loanId, int memberId, double amountPaid,String type) {

		this.loanId = loanId;
		this.memberId = memberId;
		this.amountPaid = amountPaid;
		this.type=type;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
