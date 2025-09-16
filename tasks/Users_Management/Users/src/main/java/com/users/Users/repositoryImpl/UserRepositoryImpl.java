package com.users.Users.repositoryImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.users.Users.model.UserRequest;
import com.users.Users.repository.interfaces.UserRepository;
import com.users.Users.rowmapper.UserRowMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserRequest> getAllUserRequests() {
        String sqlString = "SELECT request_id,username,email,first_name,last_name,gender,phone_number,country,state,city,postal_code,status,approvedStatus,created_at,updated_at FROM UserRequests";
 
            return jdbcTemplate.query(sqlString, new UserRowMapper());
  
    }

    public List<UserRequest> getUserRequestById(int request_id) {
        String sqlString = "SELECT request_id,username,email,first_name,last_name,gender,phone_number,country,state,city,postal_code,status,approvedStatus,created_at,updated_at FROM UserRequests WHERE request_id = :request_id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("request_id", request_id);
        
         return namedParameterJdbcTemplate.query(sqlString, param, new UserRowMapper());
       
        
    }

    public void addUserRequest(UserRequest user) {
        String sqlString = "INSERT INTO UserRequests (username, email, first_name, last_name, phone_number, country, state, city, postal_code, status, created_at, updated_at,gender) "
                + "VALUES (:username, :email, :first_name, :last_name, :phone_number, :country, :state, :city, :postal_code, :status, :created_at, :updated_at, :gender)";

        MapSqlParameterSource param = userRequestParam(user, "add");

        namedParameterJdbcTemplate.update(sqlString, param);
    }

    public boolean updateUserRequest(UserRequest user) {
        String sqlString = "UPDATE UserRequests SET "
                + "username = :username, email = :email, first_name = :first_name, "
                + "last_name = :last_name, phone_number = :phone_number, country = :country, state = :state, "
                + "city = :city, postal_code = :postal_code, status = :status, approvedStatus = :aprovedStatus, "
                + "created_at = :created_at, updated_at = :updated_at , gender= :gender "
                + "WHERE request_id = :requestId";

        MapSqlParameterSource param = userRequestParam(user, "update");
        		
        int rowsAffected = namedParameterJdbcTemplate.update(sqlString, param);
        return rowsAffected > 0;
    }
    
    @Override
    public boolean updateUserRequestStatus(int requestid, String status) {
        String sql = "UPDATE UserRequests SET status = :status, updated_at = :updated_at WHERE request_id = :requestid";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("status", status.toUpperCase());
        param.addValue("requestid", requestid);
        param.addValue("updated_at", Timestamp.valueOf(LocalDateTime.now()));
        int rows = namedParameterJdbcTemplate.update(sql, param);
        return rows > 0;
    }

    private MapSqlParameterSource userRequestParam(UserRequest user, String flag) {
    	
    	MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("username", user.getUsername());
        param.addValue("email", user.getEmail());
        param.addValue("first_name", user.getFirstName());
        param.addValue("last_name", user.getLastName());
        param.addValue("gender", user.getGender().name());
        param.addValue("phone_number", user.getPhoneNumber());
        param.addValue("country", user.getCountry());
        param.addValue("state", user.getState());
        param.addValue("city", user.getCity());
        param.addValue("postal_code", user.getPostalCode());
        param.addValue("status", user.getStatus() != null ? user.getStatus().name() : null);

        LocalDateTime created = user.getCreated_at() != null ? user.getCreated_at() : LocalDateTime.now();
        LocalDateTime updated = user.getUpdated_at() != null ? user.getUpdated_at() : LocalDateTime.now();

        param.addValue("created_at", Timestamp.valueOf(created));
        param.addValue("updated_at", Timestamp.valueOf(updated));
        
        if(flag.equals("update")) {
            param.addValue("requestId", user.getRequestId());
            param.addValue("aprovedStatus", user.getAprovedStatus().name());
        }

		return param;
	}


}
