package com.lms.web.dao.impl;

import com.lms.web.dao.ExitRequestDao;
import com.lms.web.exceptions.DAOException;
import com.lms.web.model.ExitRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class  ExitRequestDaoImpl implements ExitRequestDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ExitRequestDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ExitRequest> rowMapper = new RowMapper<>() {
        @Override
        public ExitRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExitRequest request = new ExitRequest();
            request.setRequestId(rs.getInt("requestId"));
            request.setEmployeeId(rs.getInt("employeeId"));
            request.setExitDate(rs.getDate("exitDate"));
            request.setRequestStatus(rs.getString("requestStatus"));
            request.setRequestSubmittedAt(rs.getDate("requestSubmittedAt"));
            request.setRemarks(rs.getString("requestRemarks"));
            request.setAdminRemarks(rs.getString("adminRemarks"));
            return request;
        }
    };
@Override
    public int insert(ExitRequest request) {
	if(request==null) {
		throw new DAOException("Invalid Exit Request");
	}
        try {
            String sql = "INSERT INTO exit_requests (employeeId, exitDate, requestStatus, requestSubmittedAt, requestRemarks) " +
                         "VALUES (?, ?, ?, ?, ?)";
            System.out.println("Inserting ExitRequest: " + request);
            return jdbcTemplate.update(sql,
                    request.getEmployeeId(),
                    request.getExitDate(),
                    request.getRequestStatus(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    request.getRemarks());
        } catch (Exception e) {
            throw new DAOException("Failed to insert ExitRequest", e);
        }
    }
@Override
    public int update(ExitRequest request) {
	if(request==null) {
		throw new DAOException("Invalid Exit Request");
	}
        try {
            String sql = "UPDATE exit_requests SET requestStatus = ?, adminRemarks = ? WHERE requestId = ?";
            return jdbcTemplate.update(sql,
                    request.getRequestStatus(),   
                    request.getAdminRemarks(),    
                    request.getRequestId()        
            );
        } catch (Exception e) {
            throw new DAOException("Failed to update ExitRequest with ID " + request.getRequestId(), e);
        }
    }


   @Override
    public String findRequestStatusById(int requestId) {
        try {
            String sql = "SELECT requestStatus FROM exit_requests WHERE requestId = ?";
            return jdbcTemplate.queryForObject(sql, String.class, requestId);
        } catch (EmptyResultDataAccessException e) {
            return null; 
        } catch (Exception e) {
            throw new DAOException("Failed to fetch request status for Request ID: " + requestId, e);
        }
    }

    @Override
    public int updateRequestStatusByEmployee(int requestId, String newStatus) {
        try {
            String sql = "UPDATE exit_requests SET requestStatus = ? WHERE requestId = ?";
            return jdbcTemplate.update(sql, newStatus, requestId);
        } catch (Exception e) {
            throw new DAOException("Failed to update request status for Request ID: " + requestId, e);
        }
    }


  @Override
    public LocalDate findDOJByEmployeeId(int employeeId) {
        try {
            String sql = "SELECT employeeDOJ FROM employees WHERE employeeId = ?";
            return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> rs.getDate("employeeDOJ").toLocalDate(),
                employeeId
            );
        } catch (Exception e) {
            throw new DAOException("Failed to fetch Date of Joining for Employee ID " + employeeId, e);
        }
    }
  @Override
  public List<ExitRequest> findExitRequests(Optional<Integer> employeeId) throws DAOException {
      try {
          StringBuilder sql = new StringBuilder(
              "SELECT requestId, employeeId, exitDate, requestStatus, requestSubmittedAt, " +
              "requestRemarks, adminRemarks FROM exit_requests"
          );

          Object[] params = new Object[]{};
          if (employeeId.isPresent()) {
              sql.append(" WHERE employeeId = ?");
              params = new Object[]{employeeId.get()};
          }

          return jdbcTemplate.query(sql.toString(), rowMapper, params);

      } catch (Exception e) {
          String message = employeeId.isPresent() 
                  ? "Failed to fetch exit requests for employeeId " + employeeId.get() 
                  : "Failed to fetch all exit requests";
          throw new DAOException(message, e);
      }
  }
  
  @Override
  public List<Integer> getexitEmployeeIds(){
  	try {
  		String getexitEmployeeIds = "select exit_requests.employeeId as employees from leave_requests Inner Join exit_requests on exit_requests.employeeId = leave_requests.employeeId where exit_requests.requestStatus in ('ACCEPTED')";
  		RowMapper<Integer> mapper = (r, rownum) -> {
  			int employee = r.getInt("employees");
  	            return employee;
  	        };
  		List<Integer> employees = jdbcTemplate.query(getexitEmployeeIds, mapper);
  		return employees;
  	}
  	 catch (Exception e) {
           throw new DAOException("Failed to fetch all ExitRequests", e);
       }
  }
  
  @Override
  public boolean hasActiveExitRequest(int employeeId) {
      try {
          String sql = "SELECT COUNT(*) FROM exit_requests " +
                       "WHERE employeeId = ? AND requestStatus IN ('PENDING', 'ACCEPTED')";
          Integer count = jdbcTemplate.queryForObject(sql, Integer.class, employeeId);
          return count != null && count > 0;
      } catch (Exception e) {
          throw new DAOException("Failed to check active exit requests for employeeId " + employeeId, e);
      }
  }

  
 
 }