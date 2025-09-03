package com.lms.web.service;

import com.lms.web.model.Attendance;
import java.sql.Date;
import java.util.List;

public interface AttendanceService {
	void punchAttendence(int employeeId);
    Attendance getAttendanceByEmployeeAndDate(int employeeId, Date date);
    List<Attendance> getAttendanceByEmployee(int employeeId);
	void updateAttendence(int employeeId, Date date, String status);
    
}
