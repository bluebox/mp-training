package com.lms.web.service.impl;

import com.lms.web.dao.AttendanceDAO;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.Attendance;
import com.lms.web.model.AttendanceStatus;
import com.lms.web.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
    private AttendanceDAO attendanceDAO;


    @Override
	public void punchAttendence(int employeeId) {
    	if(employeeId == 0) {
    		throw new ValidationException("Error in fetching employeeId");
    	}
    	attendanceDAO.punchAttendance(employeeId);
	}

    @Override
    public Attendance getAttendanceByEmployeeAndDate(int employeeId, Date date) {
    	if(employeeId==0)
    		throw new ValidationException("Please provide employeeId");
    	else if(date == null)
    		throw new ValidationException("Please provide Date to fetch employee attendence");
        return attendanceDAO.getAttendanceForDay(employeeId, date);
    }

    @Override
    public List<Attendance> getAttendanceByEmployee(int employeeId) {
    	if(employeeId==0)
    		throw new ValidationException("Please provide employeeId");
        return attendanceDAO.getAllAttendanceByEmployee(employeeId);
    }
    @Override
    public void updateAttendence(int employeeId, Date date,String status) {
    	if(employeeId == 0) {
    		throw new ValidationException("Error in fetching employeeId");
    	}
    	if(!(status.equalsIgnoreCase(String.valueOf(AttendanceStatus.PRESENT))|| status.equalsIgnoreCase(String.valueOf(AttendanceStatus.ABSENT)) || status.equalsIgnoreCase(String.valueOf(AttendanceStatus.HALF_DAY)))){
    		throw new ValidationException("Attendence status is not valid");
    	}
    	if(date == null) {
    		throw new ValidationException("Date cannot be empty");
    	}
    	attendanceDAO.updateStatus(employeeId, date, status);
	}
}
