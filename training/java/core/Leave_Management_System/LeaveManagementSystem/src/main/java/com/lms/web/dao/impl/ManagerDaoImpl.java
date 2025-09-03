package com.lms.web.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lms.web.dao.ManagerDao;
import com.lms.web.exceptions.DAOException;

@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getTeamExitRequests(int managerId) {
        String sql = """
            SELECT er.requestId, e.employeeId, e.employeeName, er.exitDate, er.requestStatus, er.requestRemarks
            FROM exit_requests er
            JOIN employees e ON er.employeeId = e.employeeId
            WHERE e.reporterId = ?
            """;
        return jdbcTemplate.queryForList(sql, managerId);
    }

    @Override
    public List<Map<String, Object>> getPendingLeavesForTeam(int managerId) {
        String sql = """
            SELECT lr.requestId, e.employeeId, e.employeeName, lr.leaveType, lr.startDate, lr.endDate,
                   lr.leaveStatus, lr.remarks, lr.rejectionReason
            FROM leave_requests lr
            JOIN employees e ON lr.employeeId = e.employeeId
            WHERE e.reporterId = ? AND lr.leaveStatus = 'PENDING'
            """;
        return jdbcTemplate.queryForList(sql, managerId);
    }
    @Override
    public List<Map<String, Object>> getAllTeamAttendance(int managerId) {
        String sql = """
            SELECT a.id, e.employeeId, e.employeeName, a.date, a.checkIn, a.checkOut,
                   a.attendenceStatus, a.workingHrs
            FROM attendence a
            JOIN employees e ON a.employeeId = e.employeeId
            WHERE e.reporterId = ?
            """;
        return jdbcTemplate.queryForList(sql, managerId);
    }

    @Override
    public List<Map<String, Object>> getTeamAttendanceInRange(int managerId, Date from, Date to) {
    	if(from==null || to == null) {
    		throw new DAOException("From date and To Date cannot be null");
    	}
        String sql = """
            SELECT a.id, e.employeeId, e.employeeName, a.date, a.checkIn, a.checkOut,
                   a.attendanceStatus, a.workingHrs
            FROM attendance a
            JOIN employees e ON a.employeeId = e.employeeId
            WHERE e.reporterId = ? AND a.date BETWEEN ? AND ?
            ORDER BY a.date ASC
            """;
        return jdbcTemplate.queryForList(sql, managerId, from, to);
    }




}