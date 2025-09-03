package com.lms.web.service;

import com.lms.web.model.ExitRequest;
import com.lms.web.exceptions.ServiceException;

import java.util.Map;

public interface ExitRequestService {

 //   Map<String, Object> createExitRequest(ExitRequest request) throws ServiceException;
	
	Map<String, Object> createExitRequest(ExitRequest request, boolean cancelFutureLeaves);

    Map<String, Object> updateExitRequest(ExitRequest request) throws ServiceException;


    Map<String, Object> getExitRequest(int employeeId) throws ServiceException;

    Map<String, Object> getAllExitRequests() throws ServiceException;


	Map<String, Object> cancelExitRequest(int requestId);
	boolean hasActiveExitRequest(int employeeId);

}