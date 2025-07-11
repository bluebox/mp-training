package com.loanmanagement.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.loanmanagement.Exceptions.GeneralException;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.Exceptions.NoMemberException;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanRequestDTO;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class LoanDAOImplementation implements LoanDAO{

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	

	@Override
	public void addLoan(Loan loan)throws SQLException {
		
		String sql="insert into Loan (type,Principle,IntrestRate,Tenure) values(?,?,?,?)";
		try {
		jdbcTemplate.update(sql,loan.getLoanType(),loan.getPrinciple(),loan.getRateOfIntrest(),loan.getTenureInDays());
	}
		catch (DataAccessException ex) {
	        throw new RuntimeException("Database error while adding the loan", ex);
	    }
	    catch(Exception e) {
	    	throw new GeneralException("Found the Exception "+e.getMessage());
	    }
	}
	@Override
	public Loan getLoanById(int id)throws SQLException{
		
		String sql="select LoanId as loanId,type as loanType,Principle as principle,IntrestRate as rateOfIntrest,Tenure as tenureInDays from Loan where LoanId=?";
		try {
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Loan.class),id);
	}
		catch (EmptyResultDataAccessException ex) {
	        throw new NoLoanException(" invalid  Loan ");
	    } catch (DataAccessException ex) {
	        throw new RuntimeException("Database error while fetching Loan", ex);
	    }
	    catch(Exception e) {
	    	throw new GeneralException("Found the Exception "+e.getMessage());
	    }
	}
	@Override
	public List<Loan>getAllLoans()throws SQLException{
		 String sql = "SELECT LoanId AS loanId, Type AS loanType, Principle AS principle, IntrestRate AS rateOfIntrest, Tenure AS tenureInDays FROM Loan";
		 try {  
		 return jdbcTemplate.query(sql, getLoanRowMapper());
	}
		 catch (EmptyResultDataAccessException ex) {
		        throw new NoLoanException(" No Loans found ");
		    } catch (DataAccessException ex) {
		        throw new RuntimeException("Database error while fetching loans", ex);
		    }
		    catch(Exception e) {
		    	throw new GeneralException("Found the Exception "+e.getMessage());
		    }
	}
	@Override
	public List<Loan>getLoanByType( String type) throws SQLException{
		String sql="SELECT LoanId AS loanId, Type AS loanType, Principle AS principle, IntrestRate AS rateOfIntrest, Tenure AS tenureInDays FROM Loan "
				+ "where Type=?";
		try {
			return jdbcTemplate.query(sql, getLoanRowMapper(),type);
		}
		catch (EmptyResultDataAccessException ex) {
	        throw new NoLoanException(" No Loans found ");
	    } catch (DataAccessException ex) {
	        throw new RuntimeException("Database error while fetching loans", ex);
	    }
	    catch(Exception e) {
	    	throw new GeneralException("Found the Exception "+e.getMessage());
	    }
		
	}
	
	private RowMapper<Loan> getLoanRowMapper() {
	    return (rs, rowNum) -> {
	        Loan loan = new Loan();
	        loan.setLoanId(rs.getInt("loanId"));
	        loan.setLoanType(rs.getString("loanType"));
	        loan.setPrinciple(rs.getDouble("principle"));
	        loan.setRateOfIntrest(rs.getDouble("rateOfIntrest"));
	        loan.setTenureInDays(rs.getInt("tenureInDays"));
	        return loan;
	    };
	}

	}

