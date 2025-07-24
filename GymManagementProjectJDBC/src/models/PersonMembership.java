package models;

import java.time.LocalDate;

public class PersonMembership {
	private int id;
	private int personId;
	private int planId;
	private LocalDate joiningDate;
	private LocalDate planStartedDate;
	private LocalDate planEndDate;

	public PersonMembership(int id, int personId, int planId, LocalDate joiningDate, LocalDate planStartedDate,
			LocalDate planEndDate) {

		this.id = id;
		this.personId = personId;
		this.planId = planId;
		this.joiningDate = joiningDate;
		this.planStartedDate = planStartedDate;
		this.planEndDate = planEndDate;
	}

	public PersonMembership(int personId, int planId, LocalDate joiningDate, LocalDate planStartedDate,
			LocalDate planEndDate) {

		this.personId = personId;
		this.planId = planId;
		this.joiningDate = joiningDate;
		this.planStartedDate = planStartedDate;
		this.planEndDate = planEndDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDate getPlanStartedDate() {
		return planStartedDate;
	}

	public void setPlanStartedDate(LocalDate planStartedDate) {
		this.planStartedDate = planStartedDate;
	}

	public LocalDate getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}

	@Override
	public String toString() {
		return "PersonMembership [id=" + id + ", personId=" + personId + ", planId=" + planId + ", joiningDate="
				+ joiningDate + ", planStartedDate=" + planStartedDate + ", planEndDate=" + planEndDate + "]";
	}

}
