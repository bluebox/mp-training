package com.lms.web.dao;

import com.lms.web.model.Attendance;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AttendanceDAO {
	void punchAttendance(int employeeId);
	Attendance getAttendanceForDay(int employeeId, Date date) ;
    List<Attendance> getAllAttendanceByEmployee(int employeeId) ;
    List<Map<Integer,Integer>> getAllAttendenceByYear(int year,int employeeId);
    List<Integer> getAllEmployeeIds();
    void forceMarkAbsent(int employeeId, Date date);
    void updateStatus(int employeeId, Date date, String status);
    

	 int getAbsentCountBetweenFromAndToDates(int employeeId,Date fromDate,Date ToDate);
	 Map<Integer, List<Date>> getAbsentsBetween(Date startDate, Date endDate, List<Date> nonWorkingDays);

}
