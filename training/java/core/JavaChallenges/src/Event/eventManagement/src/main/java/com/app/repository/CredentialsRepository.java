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


//
//@Repository
//public class CredentialsRepository {
//
//	private final JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	public CredentialsRepository(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//
//	public int addCredentials(Credentials cred) throws Exception {
//		String credentialSql = "INSERT INTO credentials(user_id,user_name,password) "
//				+ "values (?, ?, ?)";
//		int rowsAffected = jdbcTemplate.update(credentialSql, cred.getUser_id(),cred.getUserName(), cred.getPassword()
//				);
//		if (rowsAffected > 0) {
//			System.out.println("User credentials created Successfully!");
//		} else {
//			throw new Exception("User credentials are not created!");
//		}
//
//		return rowsAffected;
//	}
//
//	public int updateCredentials(Credentials cred) throws Exception {
//		String credentialSql = "UPDATE credentials SET password=?,updated_by=?,updated_at=? WHERE user_id=?) "
//				+ "values (?, ?, ?, ?)";
//		int rowsAffected = jdbcTemplate.update(credentialSql, cred.getPassword(), cred.getUpdated_by(),
//				cred.getUpdated_at(), cred.getUser_id());
//		if (rowsAffected > 0) {
//			System.out.println("User credentials updated Successfully!");
//		} else {
//			throw new Exception("User credentials are not updated!");
//		}
//
//		return rowsAffected;
//	}
//	public Credentials getCredentialsbyId(int user_id) {
//		String sql = "select * from credentials where user_id=?";
//		return jdbcTemplate.queryForObject(sql, new CredentialsMapper(), user_id);
//	}
//
//	public Credentials getCredentialsbyUserName(String userName) {
//		String sql = "select * from credentials where user_name=?";
//		return jdbcTemplate.queryForObject(sql, new CredentialsMapper(), userName);
//	}
//
//}