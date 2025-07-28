package com.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.CredentialsMapper;
import com.app.model.Credentials;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CredentialsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addCredentials(Credentials cred) {

		try {
			String sql = "INSERT INTO credentials (user_id, user_name, password, role) " + "VALUES (?, ?, ?, ?)";
			return jdbcTemplate.update(sql, cred.getUserId(), cred.getUserName(), cred.getPassword(), cred.getRole());

		} catch (Exception e) {
			log.info(e.getMessage());
			return 0;
		}
	}

	public Credentials getCredentialsByUsername(String userName) {
		try {
			String sql = "SELECT user_id, user_name, password, role FROM credentials WHERE user_name = ?";
			return jdbcTemplate.queryForObject(sql, new CredentialsMapper(), userName);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}
}

