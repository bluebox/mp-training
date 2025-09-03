package com.lms.web.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.lms.web.dao.LeaveRequestsDao;
import com.lms.web.exceptions.DAOException;
import com.lms.web.model.Leave;
import com.lms.web.model.LeaveRequest;
import com.lms.web.model.LeaveRequestStatus;

@Repository
public class LeaveRequestsDaoimpl implements LeaveRequestsDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final RowMapper<LeaveRequest> leaveRowMapper = new RowMapper<>() {
		
		

	
	@Override
    public LeaveRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            LeaveRequest lr = new LeaveRequest();
            lr.setRequestId(rs.getInt("requestId"));
            lr.setEmployeeId(rs.getInt("employeeId"));
            lr.setLeaveType(rs.getString("leaveType"));
            lr.setStartDate(rs.getDate("startDate"));
            lr.setEndDate(rs.getDate("endDate"));
            lr.setRemarks(rs.getString("remarks"));
            lr.setLeaveStatus(rs.getString("leaveStatus"));
            lr.setRejectionReason(rs.getString("rejectionReason"));
            return lr;
        }
    };
    
    
    
    
    
    
    
   
	@Override
    public List<LeaveRequest> getSorroundingRequests(int empId, LocalDate start, LocalDate end) throws DAOException {
    	if(start==null || end==null) {
    		throw new DAOException("Start Date and End Date cannot be null");
    	}
    	try {
            String getSorroundingRequestsQuery = "SELECT requestId, employeeId, leaveType, startDate, endDate, remarks, leaveStatus, rejectionReason FROM leave_requests WHERE employeeId = ? AND leaveStatus IN ('PENDING', 'ACCEPTED') AND ((startDate <= ? AND endDate >= ?) OR endDate = ? OR startDate = ?) ";

            List<LeaveRequest> surroundingLeaves = jdbcTemplate.query(getSorroundingRequestsQuery,leaveRowMapper,empId, Date.valueOf(end), Date.valueOf(start), Date.valueOf(start.minusDays(1)), Date.valueOf(end.plusDays(1)));
            return surroundingLeaves;
        } catch (DataAccessException ex) {
            throw new DAOException("Error fetching nearby requests", ex.getCause());
        }
    }
	@Override
	public void applyLeave(LeaveRequest request,long days) {
		if(request==null) {
			throw new DAOException("Invalid Leave Request");
		}
	    try {
	        String applyLeaveQuery = "INSERT INTO leave_requests (employeeId, leaveType, startDate, endDate, remarks, leaveStatus) VALUES (?,?,?,?,?,?)";
	        String upadateLeavesQuery="UPDATE leaves SET "+request.getLeaveType()+"="+request.getLeaveType()+"-? WHERE employeeId=?";
	        jdbcTemplate.update(applyLeaveQuery, request.getEmployeeId(), request.getLeaveType(),
	                request.getStartDate(), request.getEndDate(),
	                request.getRemarks(), String.valueOf(LeaveRequestStatus.PENDING));
	        jdbcTemplate.update(upadateLeavesQuery,days,request.getEmployeeId());
        } catch (Exception e) {
         throw new DAOException("Error applying leave", e.getCause());
     }
	}
	@Override
	    public void updateLeaveStatus(LeaveRequest request,long days) {
	    	if(request==null) {
				throw new DAOException("Invalid Leave Request");
			}
	        try {
	            String updateLeaveStatusQuery = "UPDATE leave_requests SET leaveStatus=?, rejectionReason=? WHERE requestId=?";
	            String updateLeavesTable="UPDATE leaves SET "+request.getLeaveType()+"="+request.getLeaveType()+"+? WHERE employeeId=?";
	            
	            jdbcTemplate.update(updateLeaveStatusQuery, request.getLeaveStatus(),request.getRejectionReason(), request.getRequestId());
	            if(request.getLeaveStatus().equalsIgnoreCase(String.valueOf(LeaveRequestStatus.REJECTED)) || request.getLeaveStatus().equalsIgnoreCase(String.valueOf(LeaveRequestStatus.CANCELLED))) {
		        jdbcTemplate.update(updateLeavesTable,days,request.getEmployeeId());
	            }
	        } catch (Exception e) {
	            throw new DAOException("Error updating leave status", e.getCause());
	        }
	    }
	    @Override
	    public LeaveRequest getRequestById(int id) {
	    	String getRequestByIdQuery="SELECT requestId, employeeId,leaveType,startDate,endDate,remarks,leaveStatus,rejectionReason FROM leave_requests where requestId=?";
	    	try {
	    		LeaveRequest request = jdbcTemplate.queryForObject(getRequestByIdQuery,leaveRowMapper,id);
	    		return request;
	    	}catch(Exception ex) {
	    		throw new DAOException(ex.getMessage());
	    	}
	    }
	    @Override
	    public List<LeaveRequest> getLeaveRequestsByEmployeeId(int employeeId) {
	        try {
	            String sql = "SELECT requestId, employeeId,leaveType,startDate,endDate,remarks,leaveStatus,rejectionReason FROM leave_requests WHERE employeeId=?";
	            List<LeaveRequest> employeeLeaveRequests = jdbcTemplate.query(sql, leaveRowMapper, employeeId);
	            return employeeLeaveRequests;
	        } catch (Exception e) {
	            throw new DAOException("Error fetching leaves", e.getCause());
	        }
	    }
	    @Override
	    public Leave getLeaveBalance(int employeeId){
	        try {
	            String getLeaveBalanceQuery = "SELECT employeeId, CASUAL_LEAVE, EARNED_LEAVE, SICK_LEAVE, OPTIONAL_LEAVE FROM leaves WHERE employeeId=?";
	            Leave leave = jdbcTemplate.queryForObject(getLeaveBalanceQuery, (rs, rowNum) -> {
	                Leave lb = new Leave();
	                lb.setEmployeeId(rs.getInt("employeeId"));
	                lb.setCASUAL_LEAVE(rs.getInt("CASUAL_LEAVE"));
	                lb.setEARNED_LEAVE(rs.getInt("EARNED_LEAVE"));
	                lb.setSICK_LEAVE(rs.getInt("SICK_LEAVE"));
	                lb.setOPTIONAL_LEAVE(rs.getInt("OPTIONAL_LEAVE"));
	                return lb;
	            }, employeeId);
	            return leave;
	        } catch (Exception e) {
	            throw new DAOException("Error fetching leave balance", e.getCause());
	        }
	    }
	    
//	    public void updateLeaveBalance(Leave balance){
//	        try {
//	            String sql = "UPDATE leaves SET CasualLeaves=?, EarnedLeaves=?, SickLeaves=?, OptionalLeaves=? WHERE employeeId=?";
//	            jdbcTemplate.update(sql, balance.getCasualLeaves(), balance.getEarnedLeaves(),
//	                    balance.getSickLeaves(), balance.getOptionalLeaves(), balance.getEmployeeId());
//	        } catch (Exception e) {
//	            throw new DAOException("Error updating leave balance", e);
//	        }
//	    }
	    @Override
	    public void updateLeavesById(int employeeId,String leaveType,int days) {
			String updateCasualLeaves = "UPDATE leaves SET CASUAL_LEAVE=? WHERE employeeId=?";
			String updateSickLeaves = "UPDATE leaves SET SICK_LEAVE=? WHERE employeeId=?";
			String updateEarnedLeaves = "UPDATE leaves SET EARNED_LEAVE=? WHERE employeeId=?";
			String updateOptionalLeaves = "UPDATE leaves SET OPTIONAL_LEAVE=? WHERE employeeId=?";
			try {
				int rowsAffected =0 ;
				switch (leaveType) {
				case "CASUAL_LEAVE" : rowsAffected = jdbcTemplate.update(updateCasualLeaves,days,employeeId);
								break;
				case "SICK_LEAVE" : rowsAffected = jdbcTemplate.update(updateEarnedLeaves,days,employeeId);
								break;
				case "EARNED_LEAVE" : rowsAffected = jdbcTemplate.update(updateSickLeaves,days,employeeId);
								break;
				case "OPTIONAL_LEAVE" : rowsAffected = jdbcTemplate.update(updateOptionalLeaves,days,employeeId);
								break;
				default : throw new SQLException("Leave Type Does Not Exists");
				}
				if(rowsAffected==0) {
					throw new SQLException("SQL ERROR: Leaves Not Updated to employee "+employeeId);
				}
			}
			catch (SQLException ex) {
				throw new DAOException(ex.getMessage());
			}
		}
		
		@Override
		public void removeLeavesByEmployee(int employeeId) {
			String removeLeavesQuery = "UPDATE leaves SET CASUAL_LEAVE=0, EARNED_LEAVE=0, SICK_LEAVE=0, OPTIONAL_LEAVE=0 WHERE employeeId=?";
			int rowsAffected = jdbcTemplate.update(removeLeavesQuery,employeeId);
			try {
				if(rowsAffected==0) {
					throw new SQLException("SQL ERROR: Leaves Not Removed to employee "+employeeId);
				}
			}
			catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		@Override
	    public void cancelFutureLeavesOnExit(int employeeId) {
	        try {
	            String cancelFutureLeavesOnExitQuery = "UPDATE leave_requests SET leaveStatus='CANCELLED' WHERE employeeId=? AND startDate > CURDATE()";
	            jdbcTemplate.update(cancelFutureLeavesOnExitQuery, employeeId);
	        } catch (Exception e) {
	            throw new DAOException("Error cancelling future leaves on exit", e);
	        }
	    }
	    @Override
	    public boolean hasApprovedFutureLeaves(int employeeId) {
	        try {
	            String sql = "SELECT COUNT(*) FROM leave_requests " +
	                         "WHERE employeeId=? AND leaveStatus='ACCEPTED' AND startDate > CURDATE()";
	            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, employeeId);
	            return count != null && count > 0;
	        } catch (Exception e) {
	            throw new DAOException("Error checking approved future leaves", e);
	        }
	    }

		    @Override
	    public List<LeaveRequest> getApprovedFutureLeaves(int employeeId) {
	        try {
	            String sql = "SELECT requestId, employeeId, leaveType, startDate, endDate, remarks, leaveStatus, rejectionReason " +
	                         "FROM leave_requests " +
	                         "WHERE employeeId=? AND leaveStatus='ACCEPTED' AND startDate > CURDATE()";
	            return jdbcTemplate.query(sql, leaveRowMapper, employeeId);
	        } catch (Exception e) {
	            throw new DAOException("Error fetching approved future leaves", e);
	        }
	    }

	    @Override
	    public void restoreLeaveBalance(int employeeId, String leaveType, long days) {
	        try {
	            String sql;
	            switch (leaveType.toUpperCase()) {
	                case "CASUAL_LEAVE":
	                    sql = "UPDATE leaves SET CASUAL_LEAVE = CASUAL_LEAVE + ? WHERE employeeId=?";
	                    break;
	                case "EARNED_LEAVE":
	                    sql = "UPDATE leaves SET EARNED_LEAVE = EARNED_LEAVE + ? WHERE employeeId=?";
	                    break;
	                case "SICK_LEAVE":
	                    sql = "UPDATE leaves SET SICK_LEAVE = SICK_LEAVE + ? WHERE employeeId=?";
	                    break;
	                case "OPTIONAL_LEAVE":
	                    sql = "UPDATE leaves SET OPTIONAL_LEAVE = OPTIONAL_LEAVE + ? WHERE employeeId=?";
	                    break;
	                default:
	                    throw new DAOException("Invalid leave type: " + leaveType);
	            }
	            jdbcTemplate.update(sql, days, employeeId);
	        } catch (Exception e) {
	            throw new DAOException("Error restoring leave balance for " + leaveType, e);
	        }
	    }
	    
	    
	    @Override
	    public boolean existsApprovedLeaveForDate(int employeeId, Date date) {
	        try {
	            String sql = "SELECT COUNT(*) FROM leave_requests " +
	                         "WHERE employeeId = ? " +
	                         "AND ? BETWEEN startDate AND endDate " +
	                         "AND leaveStatus = 'ACCEPTED'";  
	            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, employeeId, date);
	            return count != null && count > 0;
	        } catch (Exception e) {
	            throw new DAOException("Error checking approved leave for date", e);
	        }
	    }

	    @Override
	    public void markLopforAbsents(List<LeaveRequest> lopRequests) {
	   
	        String sql = "INSERT INTO leave_requests " +
	                     "(employeeId, leaveType, startDate, endDate, remarks, leaveStatus) " +
	                     "VALUES (?, ?, ?, ?, ?, ?)";

	        try {
	            jdbcTemplate.batchUpdate(sql, lopRequests, lopRequests.size(), (ps, lop) -> {
	                ps.setInt(1, lop.getEmployeeId());
	                ps.setString(2, lop.getLeaveType());
	                ps.setDate(3, lop.getStartDate());
	                ps.setDate(4, lop.getEndDate());
	                ps.setString(5, lop.getRemarks());
	                ps.setString(6, lop.getLeaveStatus());
	            });
	        } catch (Exception e) {
	            throw new DAOException("Error marking loss of pay for absents", e);
	        }
	    }
	    
		@Override
		public void autoApprovePendingLeaves(Date startDate, Date EndDate) {
			try {
				String acceptPendingLeaves="update leave_requests set leaveStatus='ACCEPTED' where leaveStatus='PENDING' and startDate>=? and endDate<=?";
				jdbcTemplate.update(acceptPendingLeaves,startDate,EndDate);
				
			}catch(Exception e) {
				throw new DAOException("Error autoAccepting the pending leaves",e);
			}		
		}
		
		
		@Override
		public Map<Integer, List<Date>> exitsApprovedLeaves(Date startDate, Date endDate) {
		    try {
		        String s1 = "SELECT employeeId, startDate, endDate FROM leave_requests " +
		                    "WHERE leaveStatus='ACCEPTED' AND startDate >= ? AND endDate <= ?";
		        List<Map<String, Object>> rows = jdbcTemplate.queryForList(s1, endDate, startDate);

		        Map<Integer, List<Date>> employeeLeaveMap = new HashMap<>();

		        for (Map<String, Object> row : rows) {
		            Integer employeeId = (Integer) row.get("employeeId");
		            Date start = (Date) row.get("startDate");
		            Date end = (Date) row.get("endDate");

		            List<Date> leaveDates = generateDates(start, end);

		            employeeLeaveMap.computeIfAbsent(employeeId, k -> new ArrayList<>()).addAll(leaveDates);
		        }
		        return employeeLeaveMap;

		    } catch (Exception e) {
		        throw new DAOException("Error fetching the existing approved leaves", e);
		    }
		}

		private List<Date> generateDates(Date startDate, Date endDate) {
		    LocalDate startLocalDate = startDate.toLocalDate();
		    LocalDate endLocalDate = endDate.toLocalDate();

		    return Stream.iterate(startLocalDate, date -> date.plusDays(1))
		            .limit(ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + 1)
		            .map(Date::valueOf)
		            .collect(Collectors.toList());
		}

		
}