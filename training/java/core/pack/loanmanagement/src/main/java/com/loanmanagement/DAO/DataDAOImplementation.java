package com.loanmanagement.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.loanmanagement.Exceptions.GeneralException;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.Exceptions.NoMemberException;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Payments;

@Repository
public class DataDAOImplementation implements DataDAO{
  
	@Autowired
	private  JdbcTemplate jdbcTemplate; 
	
	@Override 
	public void issueLoan(LoanRequestDTO request)throws SQLException{
		String sql = "INSERT INTO Data (MemberId,LoanId,LoanType,Principle,IntrestRate,Tenure,"
				+ "StartDate,EMI,AmountPaid,OutstandingBalance,DueDate,LateFee,Status)"
				+ " VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";
		try {
		jdbcTemplate.update(sql,request.getPersonId(),request.getLoanId(),request.getLoanType(),
				request.getPrinciple(),request.getRate(),request.getTenureInDays(),request.getStartDate()
				,Math.round(request.getEmi()*100.0)/100.0 , Math.round(request.getAmountPaid()*100.0)/100.0,
				Math.round(request.getOutstandingBalance()*100.0)/100.0,
				request.getDueDate(),Math.round(request.getLateFee()*100.0)/100.0,request.getStatus());
		
	}
		catch (DataAccessException ex) {
	        throw new RuntimeException("Database error while issuing the loan", ex);
	    }
	    catch(Exception e) {
	    	throw new GeneralException("Found the Exception "+e.getMessage());
	    }
	}
	
	@Override
	public void payAmount(Payments payment) throws SQLException{
		String sql="insert into Payments (MemberId,LoanId,AmountPaid,PaymentType,Time,MemberName) "
				+ "values(?,?,?,?,?,?)";
		 Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
		 try {
		jdbcTemplate.update(sql,payment.getMemberId(),payment.getLoanId(),payment.getAmountPaid(),payment.getType(),currentTimestamp,payment.getMemberName());
	}
		 catch (DataAccessException ex) {
		        throw new RuntimeException("Database error while paying the loan", ex);
		    }
		    catch(Exception e) {
		    	throw new GeneralException("Found the Exception "+e.getMessage());
		    }
	}
	
	
	@Override
	public List<LoanRequestDTO> getIssueById(int memberId) throws SQLException {
	    String sql = "SELECT * FROM Data WHERE MemberId = ?";

	    try {
	        List<LoanRequestDTO> results = jdbcTemplate.query(sql, new RowMapper<LoanRequestDTO>() {
	            @Override
	            public LoanRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	                LoanRequestDTO dto = new LoanRequestDTO(
	                    rs.getInt("MemberId"),
	                    rs.getInt("LoanId"),
	                    rs.getDate("StartDate")
	                );
	                dto.setLoanType(rs.getString("LoanType"));
	                dto.setPrinciple(rs.getDouble("Principle"));
	                dto.setRate(rs.getDouble("IntrestRate"));
	                dto.setTenureInDays(rs.getInt("Tenure"));
	                dto.setEmi(rs.getDouble("EMI"));
	                dto.setAmountPaid(rs.getDouble("AmountPaid"));
	                dto.setOutstandingBalance(rs.getDouble("OutstandingBalance"));
	                dto.setDueDate(rs.getDate("DueDate"));
	                dto.setLateFee(rs.getDouble("LateFee"));
	                dto.setStatus(rs.getString("Status"));
	                return dto;
	            }
	        }, memberId);

	        if (results.isEmpty()) {
	            throw new NoLoanException("No loan found for member ID " + memberId);
	        }

	        return results;

	    } catch (DataAccessException ex) {
	        throw new SQLException("Database error while fetching loan details", ex);
	    } catch (Exception e) {
	        throw new GeneralException( e.getMessage());
	    }
	}
	
	@Override
	public List<LoanRequestDTO> getIssueByLoan(int loanId) throws SQLException {
	    String sql = "SELECT * FROM Data WHERE LoanId = ?";

	    try {
	        List<LoanRequestDTO> results = jdbcTemplate.query(sql, new RowMapper<LoanRequestDTO>() {
	            @Override
	            public LoanRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	                LoanRequestDTO dto = new LoanRequestDTO(
	                    rs.getInt("MemberId"),
	                    rs.getInt("LoanId"),
	                    rs.getDate("StartDate")
	                );
	                dto.setLoanType(rs.getString("LoanType"));
	                dto.setPrinciple(rs.getDouble("Principle"));
	                dto.setRate(rs.getDouble("IntrestRate"));
	                dto.setTenureInDays(rs.getInt("Tenure"));
	                dto.setEmi(rs.getDouble("EMI"));
	                dto.setAmountPaid(rs.getDouble("AmountPaid"));
	                dto.setOutstandingBalance(rs.getDouble("OutstandingBalance"));
	                dto.setDueDate(rs.getDate("DueDate"));
	                dto.setLateFee(rs.getDouble("LateFee"));
	                dto.setStatus(rs.getString("Status"));
	                return dto;
	            }
	        }, loanId);

	        if (results.isEmpty()) {
	            throw new NoLoanException("No loan  with ID: " + loanId +" is issued");
	        }

	        return results;

	    } catch (DataAccessException ex) {
	        throw new SQLException("Database error while fetching loan", ex);
	    }
	}
	@Override
	public LoanRequestDTO getIssueByBoth(int loanId,int memberId )throws SQLException{
		String sql = "SELECT * FROM Data WHERE LoanId = ? and MemberId=?";
	    try {
	        List<LoanRequestDTO> results = jdbcTemplate.query(sql, new RowMapper<LoanRequestDTO>() {
	            @Override
	            public LoanRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	                LoanRequestDTO dto = new LoanRequestDTO(
	                    rs.getInt("MemberId"),
	                    rs.getInt("LoanId"),
	                    rs.getDate("StartDate")
	                );
	                dto.setLoanType(rs.getString("LoanType"));
	                dto.setPrinciple(rs.getDouble("Principle"));
	                dto.setRate(rs.getDouble("IntrestRate"));
	                dto.setTenureInDays(rs.getInt("Tenure"));
	                dto.setEmi(rs.getDouble("EMI"));
	                dto.setAmountPaid(rs.getDouble("AmountPaid"));
	                dto.setOutstandingBalance(rs.getDouble("OutstandingBalance"));
	                dto.setDueDate(rs.getDate("DueDate"));
	                dto.setLateFee(rs.getDouble("LateFee"));
	                dto.setStatus(rs.getString("Status"));
	                return dto;
	            }
	        }, loanId,memberId);

	        if (results.isEmpty()) {
	            throw new NoLoanException("No such loan is issued ");
	        }

	        return results.get(0);

	    } catch (DataAccessException ex) {
	        throw new SQLException("Database error while fetching loan", ex);
	    
	}
	}
	
	@Override
	public List<LoanRequestDTO> getAllIssues() throws SQLException {
	    String sql = "SELECT * FROM Data";

	    try {
	        return jdbcTemplate.query(sql, new RowMapper<LoanRequestDTO>() {
	            @Override
	            public LoanRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	                LoanRequestDTO dto = new LoanRequestDTO(
	                    rs.getInt("MemberId"),
	                    rs.getInt("LoanId"),
	                    rs.getDate("StartDate")
	                );
	                dto.setLoanType(rs.getString("LoanType"));
	                dto.setPrinciple(rs.getDouble("Principle"));
	                dto.setRate(rs.getDouble("IntrestRate"));
	                dto.setTenureInDays(rs.getInt("Tenure"));
	                dto.setEmi(rs.getDouble("EMI"));
	                dto.setAmountPaid(rs.getDouble("AmountPaid"));
	                dto.setOutstandingBalance(rs.getDouble("OutstandingBalance"));
	                dto.setDueDate(rs.getDate("DueDate"));
	                dto.setLateFee(rs.getDouble("LateFee"));
	                dto.setStatus(rs.getString("Status"));
	                return dto;
	            }
	        });
	    } catch (DataAccessException ex) {
	        throw new SQLException("Database error while fetching all issued loans", ex);
	    } catch (Exception ex) {
	        throw new GeneralException("Unexpected error occurred while fetching loan issues: " + ex.getMessage());
	    }
	}
	
	@Override
	public List<Payments>viewPayments(int memberId)throws SQLException{
		String sql="select * from Payments where MemberId=?";
		 try {
		        List<Payments> results = jdbcTemplate.query(sql, new RowMapper<Payments>() {
		            @Override
		            public Payments mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Payments pay = new Payments();
		               pay.setMemberId(rs.getInt("MemberId"));
		               pay.setLoanId(rs.getInt("LoanId"));
		               pay.setAmountPaid(rs.getDouble("AmountPaid"));
		               pay.setType(rs.getString("PaymentType"));
		               pay.setTime(rs.getTimestamp("Time").toLocalDateTime());
		               pay.setMemberName(rs.getString("MemberName"));
		              
		               
		                return pay;
		            }
		        }, memberId);

		        if (results.isEmpty()) {
		            throw new NoMemberException("No payment found with ID: " + memberId);
		        }

		        return results;

		    } catch (DataAccessException ex) {
		        throw new SQLException("Database error while fetching loan", ex);
		    }
	}

	
	@Override
	public void updateEmiSummary(int personId, int loanId, double amountPaid, double outstandingBalance, String status, double principle,int tenure) throws SQLException {
	    String sql = "UPDATE Data SET AmountPaid = ?, OutstandingBalance = ?, Principle = ?, Status = ?,Tenure=? WHERE MemberId = ? AND LoanId = ?";
	    try {
	        jdbcTemplate.update(sql, amountPaid, outstandingBalance, principle, status, tenure, personId,loanId);
	    } catch (DataAccessException ex) {
	        throw new SQLException("Failed to update EMI summary for Loan ID: " + loanId + ", Member ID: " + personId, ex);
	    }
	}

	
@Override
public void addLateFee(int personId, int loanId, double fee) throws SQLException {
    String sql = "UPDATE Data SET LateFee = ? WHERE MemberId = ? AND LoanId = ?";
    try {
        jdbcTemplate.update(sql, fee, personId, loanId);
    } catch (DataAccessException ex) {
        throw new SQLException("Failed to update LateFee for Loan ID: " + loanId + ", Member ID: " + personId, ex);
    }
}

	
@Override
public void updateAfterPrinciple(double principle, double newEmi, int personId, int loanId) throws SQLException {
    String sql = "UPDATE Data SET Principle = ?, EMI = ? WHERE MemberId = ? AND LoanID = ?";
    try {
        jdbcTemplate.update(sql, principle, newEmi, personId, loanId);
    } catch (DataAccessException ex) {
        throw new SQLException("Failed to update principle and EMI for Loan ID: " + loanId + ", Member ID: " + personId, ex);
    }
}
	
	
}
