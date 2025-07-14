package com.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.CredentialsMapper;
import com.app.model.Credentials;

@Repository
public class CredentialsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addCredentials(Credentials cred) {
        String sql = "INSERT INTO credentials (user_id, user_name, password, role) " +
                     "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            cred.getUser_id(), 
            cred.getUserName(), 
            cred.getPassword(), 
            cred.getRole()
            );
    }

    public Credentials getCredentialsByUsername(String userName) {
        String sql = "SELECT user_id, user_name, password, role FROM credentials WHERE user_name = ?";
        return jdbcTemplate.queryForObject(sql, new CredentialsMapper(), userName);
    }
}

