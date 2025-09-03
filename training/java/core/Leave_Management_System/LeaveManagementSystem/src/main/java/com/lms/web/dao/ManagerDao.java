package com.lms.web.dao;


import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ManagerDao {

    List<Map<String, Object>> getTeamExitRequests(int managerId);
    List<Map<String, Object>> getPendingLeavesForTeam(int managerId);

    List<Map<String, Object>> getAllTeamAttendance(int managerId);

    List<Map<String, Object>> getTeamAttendanceInRange(int managerId, Date from, Date to);
    
   
}