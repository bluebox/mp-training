package utils;

public class SQLQueries {

	private SQLQueries() {

	}

	public static final String INSERT_MEMBERSHIP_PLAN = "INSERT INTO membershipplan (plan_name, duration_months, fee) VALUES (?, ?, ?)";

	public static final String SELECT_MEMBERSHIP_PLAN_BY_ID = "SELECT * FROM membershipplan WHERE id = ?";

	public static final String SELECT_ALL_MEMBERSHIP_PLANS = "SELECT * FROM membershipplan";

	public static final String UPDATE_MEMBERSHIP_PLAN = "UPDATE membershipplan SET plan_name = ?, duration_months = ?, fee = ? WHERE id = ?";

	public static final String DELETE_MEMBERSHIP_PLAN = "DELETE FROM membershipplan WHERE id = ?";

	public static final String INSERT_PERSON = "INSERT INTO member (name, age, contact_details) VALUES (?, ?, ?)";

	public static final String SELECT_PERSON_BY_ID = "SELECT * FROM member WHERE id = ?";

	public static final String SELECT_ALL_PERSONS = "SELECT * FROM member";

	public static final String UPDATE_PERSON = "UPDATE member SET name = ?, age = ?, contact_details = ? WHERE id = ?";

	public static final String DELETE_PERSON = "DELETE FROM member WHERE id = ?";

	public static final String INSERT_PERSON_MEMBERSHIP = "INSERT INTO membermembership (person_id, plan_id, joining_date, plan_start_date, plan_end_date) VALUES (?, ?, ?, ?, ?)";

	public static final String SELECT_PERSON_MEMBERSHIP_BY_ID = "SELECT * FROM membermembership WHERE id = ?";

	public static final String SELECT_ALL_PERSON_MEMBERSHIPS = "SELECT * FROM membermembership";

	public static final String SELECT_ACTIVE_MEMBERSHIPS = "SELECT * FROM membermembership WHERE plan_end_date >= CURDATE()";

	public static final String DELETE_PERSON_MEMBERSHIP_BY_ID = "DELETE FROM membermembership WHERE person_id = ?";

	public static final String DELETE_EXPIRED_MEMBERSHIPS = "DELETE FROM membermembership WHERE plan_end_date < CURDATE()";

}