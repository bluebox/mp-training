package com.loanmanagement.DAO;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.loanmanagement.Exceptions.GeneralException;
import com.loanmanagement.Exceptions.NoMemberException;
import com.loanmanagement.model.Member;

@Repository
public class MemberDAOImplementation implements MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addMember(Member person) throws SQLException, DuplicateKeyException {
		String sql = "INSERT INTO Member (Name, Email, Mobile, Address,CreditScore) VALUES (?, ?, ?, ?,?)";
		try {
		jdbcTemplate.update(sql, person.getName(), person.getEmail(), person.getMobile(), person.getAddress(),person.getCreditScore());
		}
		
		     catch (DataAccessException ex) {
		        throw new RuntimeException("Database error while adding", ex);
		    }
		    catch(Exception e) {
		    	System.out.println("-------------checking 123-------------");
		    	throw new GeneralException("Found the Exception "+e.getMessage());
		    }
	}
     
	@Override
	public Member getMemberByEmail(String email) throws SQLException,DuplicateKeyException{
	    String sql = "SELECT Member_Id AS id, Name AS name, Email AS email, Mobile AS mobile, "
	               + "Address AS address, CreditScore AS creditScore FROM Member WHERE Email = ?";

	    try {
	        return jdbcTemplate.queryForObject(sql, getMemberRowMapper(), email);
	    } catch (EmptyResultDataAccessException ex) {
	        return null; // Member not found, return null
	    } catch (DataAccessException ex) {
	        throw new GeneralException("Database error while fetching member");
	    } catch (Exception e) {
	        throw new GeneralException("Unexpected error: " + e.getMessage());
	    }
	}
	
	
	
	
	
	
	
	
	
	@Override
	public Member getMemberById(int id) throws SQLException {
	    String sql = "SELECT Member_Id AS id, Name AS name, Email AS email, Mobile AS mobile, "
	               + "Address AS address, CreditScore AS creditScore FROM Member WHERE Member_Id = ?";

	    try {
	        return jdbcTemplate.queryForObject(sql, getMemberRowMapper(), id);
	    } catch (EmptyResultDataAccessException ex) {
	        throw new NoMemberException("Invalid member ID: " + id);
	    } catch (DataAccessException ex) {
	        throw new RuntimeException("Database error while fetching member", ex);
	    } catch (Exception e) {
	        throw new GeneralException("Unexpected error: " + e.getMessage());
	    }
	}
	@Override
	public List<Member> getAllMembers() throws SQLException{
		 String sql = "SELECT Member_Id AS id, Name AS name, Email AS email, Mobile AS mobile, Address AS address ,"
		 		+ "CreditScore as creditScore FROM Member";
		 try {  
		 return jdbcTemplate.query(sql, getMemberRowMapper());
		 }
		 catch (EmptyResultDataAccessException ex) {
		        throw new NoMemberException(" No Members found ");
		    } catch (DataAccessException ex) {
		        throw new RuntimeException("Database error while fetching member", ex);
		    }
		    catch(Exception e) {
		    	throw new GeneralException("Found the Exception "+e.getMessage());
		    }
	}

	private RowMapper<Member> getMemberRowMapper() {
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getInt("id"));
			member.setName(rs.getString("Name"));
			member.setEmail(rs.getString("Email"));
			member.setMobile(rs.getString("Mobile"));
			member.setAddress(rs.getString("Address"));
			member.setCreditScore(rs.getInt("CreditScore"));
			return member;
		};
	}

}

//
