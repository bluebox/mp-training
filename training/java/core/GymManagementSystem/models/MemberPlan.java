package GymManagementSystem.models;

import java.time.LocalDate;

public class MemberPlan {
	private int memberId;
	private int planId;
	private LocalDate startDate;

	public MemberPlan(int memberId, int planId, LocalDate startDate) {
		this.memberId = memberId;
		this.planId = planId;
		this.startDate = startDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public String toString() {
		return "Member ID: " + memberId +
	               ", Plan ID: " + planId +
	               ", Start Date: " + startDate;
	}
}
