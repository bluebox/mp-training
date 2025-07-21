package models;

import java.time.LocalDate;

public class PersonMembership {
	private int id;
	private int personId;
	private int planId;
	private LocalDate joiningDate;
	private LocalDate planStartDate;
	private LocalDate planEndDate;

	public PersonMembership(int id, int personId, int planId, LocalDate joiningDate, LocalDate planStartDate,
			LocalDate planEndDate) {
		this.id = id;
		this.personId = personId;
		this.planId = planId;
		this.joiningDate = joiningDate;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
	}

	public PersonMembership(int personId, int planId, LocalDate joiningDate, LocalDate planStartDate,
			LocalDate planEndDate) {
		this(-1, personId, planId, joiningDate, planStartDate, planEndDate);
	}

	public PersonMembership(int personId, LocalDate joiningDate) {
		this(personId, -1, joiningDate, null, null);
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

	public LocalDate getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
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
				+ joiningDate + ", planStartDate=" + planStartDate + ", planEndDate=" + planEndDate + "]";
	}

}
