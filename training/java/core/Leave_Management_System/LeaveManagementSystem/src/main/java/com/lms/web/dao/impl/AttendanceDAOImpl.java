package com.lms.web.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lms.web.dao.AttendanceDAO;
import com.lms.web.exceptions.DAOException;
import com.lms.web.model.Attendance;
import com.lms.web.model.AttendanceStatus;



@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

	private JdbcTemplate template;
	private RowMapper<Attendance> mapper;

	public JdbcTemplate getTemplate() {
		return template;
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	@Override
	public void punchAttendance(int employeeId) {
    	Attendance punch=null;
        Date today = Date.valueOf(LocalDate.now());
        Time now = new Time(System.currentTimeMillis());
        String checkpunch="SELECT id, employeeId, date, checkIn, checkOut, workingHRS, attendenceStatus  FROM attendence WHERE employeeId=? AND date=?";
        String checkIn = "INSERT INTO attendence(employeeId, date, checkIn, attendenceStatus) VALUES (?,?,?,?)";
        String checkOut = "UPDATE attendence SET checkOut=?, attendenceStatus=? WHERE id=?";
        mapper = (rs, rowNum) -> {
        	Attendance punchdata = new Attendance(rs.getInt("id")
        			,rs.getInt("employeeId")
        			,rs.getDate("date")
        			,rs.getTime("checkIn")
        			,rs.getTime("checkOut")
        			,rs.getBigDecimal("workingHrs")
        			,rs.getString("attendenceStatus")
        			);
			return punchdata;
		};
		try {
			 punch=template.queryForObject(checkpunch,mapper,employeeId,today);
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("First Punch today");
		}
        if(punch==null) {
        	int rowsAffected = template.update(checkIn,employeeId,today,now,String.valueOf(AttendanceStatus.ABSENT));
        	try {
    			if(rowsAffected==0) {
    				throw new SQLException("SQL ERROR: CheckIn Not Done");
    			}
    		}
    		catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        }
        else {
        	Duration duration = Duration.between(now.toLocalTime(), punch.getCheckIn().toLocalTime());
            long hrs = duration.toHours();
            String status;
            if (hrs >= 8) {
                status = String.valueOf(AttendanceStatus.PRESENT);
            } else if (hrs >= 4) {
                status = String.valueOf(AttendanceStatus.HALF_DAY);
            } else {
                status = String.valueOf(AttendanceStatus.ABSENT);
            }
        	int rowsAffected = template.update(checkOut,now,status,punch.getId());
        	try {
    			if(rowsAffected==0) {
    				throw new SQLException("SQL ERROR: CheckIn Not Done");
    			}
    		}
    		catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        }
    }    
	        
	@Override
	public Attendance getAttendanceForDay(int employeeId, Date date) {
		if(date==null) {
			throw new DAOException("Date cannot be null");
		}
		String getAttendence = "SELECT id, employeeId, date, checkIn, checkOut, workingHRS, attendenceStatus FROM attendence WHERE employeeId=? AND date=?";
		mapper = (rs, rowNum) -> {
			Attendance attendence = new Attendance(rs.getInt("id")
					,rs.getInt("employeeId")
					,rs.getDate("date")
					,rs.getTime("checkIn")
					,rs.getTime("checkOut")
					,rs.getBigDecimal("workingHrs")
					,rs.getString("attendenceStatus")
					);
			return attendence;
		};
		Attendance attendence = template.queryForObject(getAttendence,mapper,employeeId,date);
		try {
			if(attendence == null) {
				throw new SQLException("Employee Is Absent on "+date);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return attendence;
	}	
		
	@Override
	public List<Attendance> getAllAttendanceByEmployee(int employeeId) {
		String getEmployeeAttendence = "SELECT id, employeeId, date, checkIn, checkOut, workingHRS, attendenceStatus FROM attendence WHERE employeeId=?";
		mapper = (rs, rowNum) -> {
			Attendance attendence = new Attendance(rs.getInt("id")
					,rs.getInt("employeeId")
					,rs.getDate("date")
					,rs.getTime("checkIn")
					,rs.getTime("checkOut")
					,rs.getBigDecimal("workingHrs")
					,rs.getString("attendenceStatus")
					);
			return attendence;
		};
		List<Attendance> attendenceList = template.query(getEmployeeAttendence, mapper, employeeId);
		return attendenceList;
	}	
	
	@Override
	public List<Map<Integer,Integer>> getAllAttendenceByYear(int year,int employeeId){
		String getYearlyAttendence = "SELECT Month(date) AS month, count(attendenceStatus) as days from attendence where Year(date)=? AND attendenceStatus='PRESENT' AND employeeId=? group by Month(date) order by month";
		RowMapper<Map<Integer,Integer>> integerMapper = (rs, rowNum) -> {
			Map<Integer,Integer> employeeYearlyReport = new HashMap<>();
			employeeYearlyReport.put(rs.getInt("month"), rs.getInt("days"));
			return employeeYearlyReport;
		};
		List<Map<Integer,Integer>> working_days = template.query(getYearlyAttendence, integerMapper, year, employeeId);
		return working_days;
	}
	@Override
	public List<Integer> getAllEmployeeIds() {
	    String sql = "SELECT employeeId FROM employees";
	    List<Integer> employeeIds = template.queryForList(sql, Integer.class);
	    return employeeIds;
	}

	@Override
	public void forceMarkAbsent(int employeeId, Date date) {
		if(date==null) {
			throw new DAOException("Date cannot be null");
		}
	    String sql = "INSERT INTO attendence (employeeId, date, attendenceStatus) VALUES (?, ?, ?)";
	    template.update(sql, employeeId, date, "Absent");
	}

	@Override
	public void updateStatus(int employeeId, Date date, String status) {
	    String sql = "UPDATE attendence SET attendenceStatus=? WHERE employeeId=? AND date=?";
	    template.update(sql, status, employeeId, date);
	}
	
	
	
	@Override
	public Map<Integer, List<Date>> getAbsentsBetween(Date startDate, Date endDate, List<Date> nonWorkingDays) {
	    String sql = "SELECT employeeId, `date` as workDate " +
	                 "FROM attendence " +
	                 "WHERE attendenceStatus = 'ABSENT' " +
	                 "AND `date` BETWEEN :startDate AND :endDate " +
	                 "AND `date` NOT IN (:nonWorkingDays)";

	    Map<String, Object> params = new HashMap<>();
	    params.put("startDate", startDate);
	    params.put("endDate", endDate);
	    params.put("nonWorkingDays", nonWorkingDays);

	try {    NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(template.getDataSource());

	    List<Map<String, Object>> rows = namedTemplate.queryForList(sql, params);

	    Map<Integer, List<Date>> absentsMap = new HashMap<>();

	    for (Map<String, Object> row : rows) {
	        Integer employeeId = (Integer) row.get("employeeId");
	        Date workDate = (Date) row.get("workDate");

	        absentsMap.computeIfAbsent(employeeId, k -> new ArrayList<>()).add(workDate);
	    }

	    return absentsMap;}
	catch(Exception e) {
		throw new DAOException("error fetching the absentees");
	}
	}
	@Override
	public int getAbsentCountBetweenFromAndToDates(int employeeId,Date fromDate,Date ToDate){
		String getAbsentDays = "select COUNT(id) as absentDays from attendence where employeeId = ? and date between ? and ? and attendenceStatus = ?";
		RowMapper<Integer> integerMapper = (rs, rowNum) -> {
			int noOfDays = rs.getInt("absentDays");
			return noOfDays;
		};
		int nonWorking_days = 0 ;
		nonWorking_days = template.queryForObject(getAbsentDays, integerMapper, employeeId,fromDate,ToDate,String.valueOf(AttendanceStatus.ABSENT));	
		return nonWorking_days;
	}

	

	}
		
	



