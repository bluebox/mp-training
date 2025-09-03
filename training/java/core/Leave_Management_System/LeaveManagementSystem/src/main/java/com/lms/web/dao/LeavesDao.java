package com.lms.web.dao;

import com.lms.web.model.Leave;

public interface LeavesDao {
	void addLeave(Leave leave);
	Leave getLeavesByEmployeeId(int EmployeeId);
	void updateLeavesById(int employeeId,String leaveType,int days);
	void removeLeavesByEmployee(int employeeId);
}
