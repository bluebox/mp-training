package com.lms.web.service;

import java.util.List;

import com.lms.web.exceptions.ServiceException;
import com.lms.web.model.Leave;
import com.lms.web.model.LeaveRequest;

public interface LeaveRequestService {

	public void applyLeave(LeaveRequest req);

	public void approveLeave(int requestId);

	public void rejectLeave(int requestId, String reason) throws ServiceException;

	public void cancelLeave(int requestId) throws ServiceException;

	public List<LeaveRequest> getLeavesById(int employeeId);

    public Leave getLeaveBalence(int employeeId);
    
}
