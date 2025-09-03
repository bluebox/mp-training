package com.lms.web.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lms.web.exceptions.DAOException;
import com.lms.web.model.Leave;
import com.lms.web.model.LeaveRequest;
import com.lms.web.model.LeaveRequestStatus;

public interface LeaveRequestsDao {
	 List<LeaveRequest> getSorroundingRequests(int empId, LocalDate start, LocalDate end) throws DAOException;
	 void applyLeave(LeaveRequest request,long days);
	 void updateLeaveStatus(LeaveRequest request,long days);
	 LeaveRequest getRequestById(int id);
	 List<LeaveRequest> getLeaveRequestsByEmployeeId(int employeeId);
	 Leave getLeaveBalance(int employeeId);
	 void updateLeavesById(int employeeId,String leaveType,int days);
	 void removeLeavesByEmployee(int employeeId);
	 void cancelFutureLeavesOnExit(int employeeId);
	 boolean hasApprovedFutureLeaves(int employeeId);
	 List<LeaveRequest> getApprovedFutureLeaves(int employeeId);
	 void restoreLeaveBalance(int employeeId, String leaveType, long days);
	 boolean existsApprovedLeaveForDate(int empId, Date date);
	 void autoApprovePendingLeaves(Date startDate,Date endDate);
	 Map<Integer, List<Date>> exitsApprovedLeaves(Date startDate,Date endDate);
	 void markLopforAbsents(List<LeaveRequest> lopRequests); 
	 
	

}
