package dev.kaushik.userManagement.dao.impl;

import dev.kaushik.userManagement.dao.UserRequestDao;
import dev.kaushik.userManagement.model.UserRequest;
import dev.kaushik.userManagement.model.enums.Approval;
import dev.kaushik.userManagement.model.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRequestDaoImpl implements UserRequestDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public UserRequestDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int createUser(UserRequest userReq) {
		String sql = "INSERT INTO user_requests (firstName, lastName, email, phoneNumber, gender, country, state, city, pinCode, approval) "
				+ "VALUES (:firstName, :lastName, :email, :phoneNumber, :gender, :country, :state, :city, :pinCode, :approval)";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("firstName", userReq.getFirstName())
				.addValue("lastName", userReq.getLastName())
				.addValue("email", userReq.getEmail())
				.addValue("phoneNumber", userReq.getPhoneNumber())
				.addValue("gender", String.valueOf(userReq.getGender().getCode()))
				.addValue("country", userReq.getCountry())
				.addValue("state", userReq.getState())
				.addValue("city", userReq.getCity())
				.addValue("pinCode", userReq.getPinCode())
				.addValue("approval", String.valueOf(userReq.getApproval().getCode()));

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"requestId"});
		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
	}

	@Override
	public List<UserRequest> getAllRequests() {
		RowMapper<UserRequest> requestRowMapper = (rs, rowNum) -> {
			UserRequest request = new UserRequest();
			request.setRequestId(rs.getInt("requestId"));
			request.setFirstName(rs.getString("firstName"));
			request.setLastName(rs.getString("lastName"));
			request.setEmail(rs.getString("email"));
			request.setPhoneNumber(rs.getLong("phoneNumber"));
			request.setGender(Gender.fromCode(rs.getString("gender").charAt(0)));
			request.setCountry(rs.getString("country"));
			request.setState(rs.getString("state"));
			request.setCity(rs.getString("city"));
			request.setPinCode(rs.getInt("pinCode"));
			request.setApproval(Approval.fromCode(rs.getString("approval").charAt(0)));
			request.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
			request.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
			return request;
		};
		String sql = "SELECT * FROM user_requests";
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), requestRowMapper);
	}

	@Override
	public boolean updateRequest(UserRequest userReq) {
		String updatedBy = "ADMIN";

		String sql = "UPDATE user_requests SET firstName=:firstName, lastName=:lastName, email=:email, phoneNumber=:phoneNumber, "
				+ "gender=:gender, country=:country, state=:state, city=:city, pinCode=:pinCode, approval=:approval, "
				+ "updated_by=:updated_by, updated_at=CURRENT_TIMESTAMP WHERE requestId=:requestId";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", userReq.getRequestId())
				.addValue("firstName", userReq.getFirstName())
				.addValue("lastName", userReq.getLastName())
				.addValue("email", userReq.getEmail())
				.addValue("phoneNumber", userReq.getPhoneNumber())
				.addValue("gender", String.valueOf(userReq.getGender().getCode()))
				.addValue("country", userReq.getCountry())
				.addValue("state", userReq.getState())
				.addValue("city", userReq.getCity())
				.addValue("pinCode", userReq.getPinCode())
				.addValue("approval", String.valueOf(userReq.getApproval().getCode()))
				.addValue("updated_by", updatedBy);

		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		return rowsAffected > 0;
	}

	@Override
	public boolean rejectUser(int requestId) {
		String sql = "UPDATE user_requests SET approval='R', updated_at=CURRENT_TIMESTAMP WHERE requestId=:requestId";
		MapSqlParameterSource params = new MapSqlParameterSource("requestId", requestId);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}
}