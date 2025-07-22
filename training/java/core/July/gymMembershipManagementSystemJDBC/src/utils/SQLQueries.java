package utils;

public class SQLQueries {

	private SQLQueries() {

	}

	public static final String INSERT_MEMBERSHIP_PLAN = "INSERT INTO membership_plan (plan_name, duration_months, fee) VALUES (?, ?, ?)";

	public static final String SELECT_MEMBERSHIP_PLAN_BY_ID = "SELECT * FROM membership_plan WHERE id = ?";

	public static final String SELECT_ALL_MEMBERSHIP_PLANS = "SELECT * FROM membership_plan";

	public static final String UPDATE_MEMBERSHIP_PLAN = "UPDATE membership_plan SET plan_name = ?, duration_months = ?, fee = ? WHERE id = ?";

	public static final String DELETE_MEMBERSHIP_PLAN = "DELETE FROM membership_plan WHERE id = ?";

	public static final String INSERT_PERSON = "INSERT INTO person (name, age, contact_details) VALUES (?, ?, ?)";

	public static final String SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";

	public static final String SELECT_ALL_PERSONS = "SELECT * FROM person";

	public static final String UPDATE_PERSON = "UPDATE person SET name = ?, age = ?, contact_details = ? WHERE id = ?";

	public static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";

	public static final String INSERT_PERSON_MEMBERSHIP = "INSERT INTO personmembership (person_id, plan_id, joining_date, plan_start_date, plan_end_date) VALUES (?, ?, ?, ?, ?)";

	public static final String SELECT_PERSON_MEMBERSHIP_BY_ID = "SELECT * FROM personmembership WHERE id = ?";

	public static final String SELECT_ALL_PERSON_MEMBERSHIPS = "SELECT * FROM personmembership";

	public static final String SELECT_ACTIVE_MEMBERSHIPS = "SELECT * FROM personmembership WHERE plan_end_date >= CURDATE()";

	public static final String DELETE_PERSON_MEMBERSHIP_BY_ID = "DELETE FROM personmembership WHERE person_id = ?";

	public static final String DELETE_EXPIRED_MEMBERSHIPS = "DELETE FROM personmembership WHERE plan_end_date < CURDATE()";

}