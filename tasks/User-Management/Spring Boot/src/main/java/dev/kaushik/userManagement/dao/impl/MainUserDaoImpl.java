package dev.kaushik.userManagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import dev.kaushik.userManagement.dao.MainUserDao;
import dev.kaushik.userManagement.model.MainUser;
import dev.kaushik.userManagement.model.enums.Gender;
import dev.kaushik.userManagement.model.enums.Status;

@Repository
public class MainUserDaoImpl implements MainUserDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public MainUserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, PasswordEncoder passwordEncoder) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public boolean addMainuser(MainUser mainUser) {
        String sql = "INSERT INTO users (userName, password, firstName, lastName, email, phoneNumber, "
        		+ "gender, country, state, city, pinCode, status, created_by) "
                + "VALUES (:userName, :password, :firstName, :lastName, :email, :phoneNumber, "
                + ":gender, :country, :state, :city, :pinCode, :status, 'ADMIN')";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userName", mainUser.getUserName())
                .addValue("password", passwordEncoder.encode(mainUser.getPassword())) 
                .addValue("firstName", mainUser.getFirstName())
                .addValue("lastName", mainUser.getLastName())
                .addValue("email", mainUser.getEmail())
                .addValue("phoneNumber", mainUser.getPhoneNumber())
                .addValue("gender", String.valueOf(mainUser.getGender().getCode()))
                .addValue("country", mainUser.getCountry())
                .addValue("state", mainUser.getState())
                .addValue("city", mainUser.getCity())
                .addValue("pinCode", mainUser.getPinCode())
                .addValue("status", String.valueOf(mainUser.getStatus().getCode()));

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params); 
        return rowsAffected > 0;
    }
	
	@Override
	public boolean updateMainUser(MainUser mainUser) {
		String updatedBy = "ADMIN";

		String sql = "UPDATE users SET firstName=:firstName, lastName=:lastName, email=:email, phoneNumber=:phoneNumber, "
				+ "gender=:gender, country=:country, state=:state, city=:city, pinCode=:pinCode, status=:status, "
				+ "updated_by=:updated_by, updated_at=CURRENT_TIMESTAMP WHERE userName=:userName";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("userName", mainUser.getUserName())
				.addValue("firstName", mainUser.getFirstName())
				.addValue("lastName", mainUser.getLastName())
				.addValue("email", mainUser.getEmail())
				.addValue("phoneNumber", mainUser.getPhoneNumber())
				.addValue("gender", String.valueOf(mainUser.getGender().getCode()))
				.addValue("country", mainUser.getCountry())
				.addValue("state", mainUser.getState())
				.addValue("city", mainUser.getCity())
				.addValue("pinCode", mainUser.getPinCode())
				.addValue("status", String.valueOf(mainUser.getStatus().getCode()))
				.addValue("updated_by", updatedBy);

		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		return rowsAffected > 0;
	}

	@Override
	public boolean updateMainUserWithPassword(MainUser mainUser) {
		String updatedBy = "USER";

		String sql = "UPDATE users SET password=:password, firstName=:firstName, lastName=:lastName, email=:email, "
				+ "phoneNumber=:phoneNumber, gender=:gender, country=:country, state=:state, city=:city, pinCode=:pinCode, "
				+ "status=:status, updated_by=:updated_by, updated_at=CURRENT_TIMESTAMP WHERE userName=:userName";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("password", passwordEncoder.encode(mainUser.getPassword()))
				.addValue("userName", mainUser.getUserName())
				.addValue("firstName", mainUser.getFirstName())
				.addValue("lastName", mainUser.getLastName())
				.addValue("email", mainUser.getEmail())
				.addValue("phoneNumber", mainUser.getPhoneNumber())
				.addValue("gender", String.valueOf(mainUser.getGender().getCode()))
				.addValue("country", mainUser.getCountry())
				.addValue("state", mainUser.getState())
				.addValue("city", mainUser.getCity())
				.addValue("pinCode", mainUser.getPinCode())
				.addValue("status", String.valueOf(mainUser.getStatus().getCode()))
				.addValue("updated_by", updatedBy);

		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		return rowsAffected > 0;
	}
	
	@Override
	public List<MainUser> getAllMainUsers() {
		RowMapper<MainUser> mainUserRowMapper = (rs, rowNum) -> {
			MainUser mainUser = new MainUser();
			mainUser.setUserName(rs.getString("userName"));
			mainUser.setPassword(rs.getString("password"));
			mainUser.setFirstName(rs.getString("firstName"));
			mainUser.setLastName(rs.getString("lastName"));
			mainUser.setEmail(rs.getString("email"));
			mainUser.setPhoneNumber(rs.getLong("phoneNumber"));
			mainUser.setGender(Gender.fromCode(rs.getString("gender").charAt(0)));
			mainUser.setCountry(rs.getString("country"));
			mainUser.setState(rs.getString("state"));
			mainUser.setCity(rs.getString("city"));
			mainUser.setPinCode(rs.getInt("pinCode"));
			mainUser.setStatus(Status.fromCode(rs.getString("status").charAt(0)));
			mainUser.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
			mainUser.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
			return mainUser;
		};
		String sql = "SELECT * FROM users";
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), mainUserRowMapper);
	}
	
}