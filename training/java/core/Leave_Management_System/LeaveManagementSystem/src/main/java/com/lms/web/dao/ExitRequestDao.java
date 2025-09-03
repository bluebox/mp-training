package com.lms.web.dao;

import com.lms.web.model.ExitRequest;
import com.lms.web.model.LeaveRequest;
import com.lms.web.exceptions.DAOException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExitRequestDao {

    int insert(ExitRequest request) throws DAOException;

    int update(ExitRequest request) throws DAOException;


    LocalDate findDOJByEmployeeId(int employeeId) throws DAOException;

    public List<ExitRequest> findExitRequests(Optional<Integer> employeeId) throws DAOException ;
    
	String findRequestStatusById(int requestId);

	int updateRequestStatusByEmployee(int requestId, String newStatus);
	
	List<Integer> getexitEmployeeIds();
	
	boolean hasActiveExitRequest(int employeeId) throws DAOException;


}