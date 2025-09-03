package com.lms.web.service.impl;

import com.lms.web.dao.ExitRequestDao;
import com.lms.web.dao.LeaveRequestsDao;
import com.lms.web.exceptions.ServiceException;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.ExitRequest;
import com.lms.web.model.ExitRequestStatus;
import com.lms.web.model.LeaveRequest;
import com.lms.web.service.ExitRequestService;
import com.lms.web.utils.Validate;
import com.lms.web.model.LeaveRequestStatus;
import com.lms.web.model.Leaves;
import com.lms.web.service.impl.WorkdayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class  ExitRequestServiceImpl implements ExitRequestService {

	@Autowired
    private ExitRequestDao dao;
	@Autowired
	private LeaveRequestsDao leavesRequestDao;
	
	@Autowired
	private WorkdayService workdayService;
	
	@Autowired
	private Validate Validate;
	

	@Override
	@Transactional
	public Map<String, Object> createExitRequest(ExitRequest request, boolean cancelFutureLeaves) {
	    if (request == null) {
	        throw new ServiceException("Invalid Exit Request");
	    }
	    try {
	        Validate.validateExitRequest(request);

	        boolean hasFutureLeaves = leavesRequestDao.hasApprovedFutureLeaves(request.getEmployeeId());

	        if (hasFutureLeaves && !cancelFutureLeaves) {
	            throw new ServiceException("You have approved future leaves. Submitting exit will cancel them");
	        }

	        LocalDate exitDate = request.getExitDate().toLocalDate();
	        if (workdayService.isWeekend(exitDate)) {
	            throw new ServiceException("Exit date cannot be on a weekend");
	        }
	        if (workdayService.isHoliday(exitDate)) {
	            throw new ServiceException("Exit date cannot be on a holiday");
	        }

	        boolean hasActive = dao.hasActiveExitRequest(request.getEmployeeId());
	        if (hasActive) {
	            throw new ServiceException("You already have an active exit request.");
	        }

	        LocalDate doj = dao.findDOJByEmployeeId(request.getEmployeeId());
	        LocalDate today = LocalDate.now();
	        if (doj.plusMonths(6).isAfter(today)) {
	            throw new ServiceException("You cannot resign during the probation period (6 months).");
	        }

	        request.setRequestStatus(String.valueOf(ExitRequestStatus.PENDING));
	        request.setRequestSubmittedAt(new java.sql.Date(System.currentTimeMillis()));

	        if (hasFutureLeaves && cancelFutureLeaves) {
	            List<LeaveRequest> futureLeaves = leavesRequestDao.getApprovedFutureLeaves(request.getEmployeeId());
	            for (LeaveRequest lr : futureLeaves) {
	                long calDays = ChronoUnit.DAYS.between(
	                        lr.getStartDate().toLocalDate(),
	                        lr.getEndDate().toLocalDate()
	                ) + 1;
	                long workingDays = workdayService.countWorkingDays(
	                        lr.getStartDate().toLocalDate(),
	                        lr.getEndDate().toLocalDate()
	                );

	                leavesRequestDao.restoreLeaveBalance(
	                        lr.getEmployeeId(),
	                        lr.getLeaveType(),
	                        lr.getLeaveType().equalsIgnoreCase(String.valueOf(Leaves.SICK_LEAVE)) ? calDays : workingDays
	                );

	                leavesRequestDao.cancelFutureLeavesOnExit(request.getEmployeeId());
	            }
	        }

	        int changes = dao.insert(request);
	        if (changes == 0) {
	            throw new ServiceException("Failed to create exit request. Please try again.");
	        }

	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Exit request created successfully");
	        response.put("futureLeavesCancelled", hasFutureLeaves && cancelFutureLeaves);
	        return response;

	    } catch (ValidationException ie) {
	        throw new ServiceException(ie.getMessage());
	    } catch (Exception e) {
	        throw new ServiceException(e.getMessage());
	    }
	}
	@Override
    public Map<String, Object> updateExitRequest(ExitRequest request) {
	if(request==null) {
		throw new ServiceException("Invalid Exit Request");
	}
        try {
            Validate.validateExitRequest(request);

        	int changes = dao.update(request);
            
            
            if (changes == 0) {
                throw new ServiceException("Exit request not found with id " + request.getRequestId());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exit request updated successfully");
            response.put("exitRequest", request);
            return response;

        } catch(ValidationException ie) {
        				throw new ServiceException(ie.getMessage());
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


@Override
@Transactional
public Map<String, Object> cancelExitRequest(int requestId) {
    try {
        Validate.validateId(requestId);

        String currentStatus = dao.findRequestStatusById(requestId);
        if (currentStatus == null) {
            throw new ServiceException("No exit request found with ID: " + requestId);
        }
        
        

        if (!String.valueOf(ExitRequestStatus.PENDING).equalsIgnoreCase(currentStatus)) {
            throw new ServiceException(
                "Exit request with ID " + requestId + " cannot be cancelled as its status is: " + currentStatus
            );
        }

        int updated = dao.updateRequestStatusByEmployee(requestId, String.valueOf(ExitRequestStatus.CANCELLED));
        if (updated == 0) {
            throw new ServiceException("Failed to update exit request status for ID: " + requestId);
        }

        return Map.of("message", "Exit request cancelled successfully by employee");

    } catch (ValidationException ie) {
        throw new ServiceException(ie.getMessage());
    } catch (Exception e) {
        throw new ServiceException("Failed to cancel exit request with ID: " + requestId);
    }
}
	


	@Override
    public Map<String, Object> getExitRequest(int employeeId) {
        try {
            Validate.validateId(employeeId);
            
        	List<ExitRequest> exitRequests = dao.findExitRequests(Optional.of(employeeId));


            if (exitRequests.isEmpty()) {
                throw new ServiceException("No exit requests found for employee with id " + employeeId);
            }

            return Map.of(
                    "message", "Exit request(s) fetched successfully",
                    "exitRequests", exitRequests
            );

        }catch(ValidationException ie) {
						throw new ServiceException( ie.getMessage());
		} 
        catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
	@Override
    public Map<String, Object> getAllExitRequests() {
        try {
        	List<ExitRequest> allRequests = dao.findExitRequests(Optional.empty());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "All exit requests fetched successfully");
            response.put("exitRequests", allRequests);

            return response;

        } catch (Exception e) {
            throw new ServiceException("Failed to fetch all exit requests");
        }
    }
	
	@Override
	public boolean hasActiveExitRequest(int employeeId) {
	    try {
	    	
	    	Validate.validateId(employeeId);
	        return dao.hasActiveExitRequest(employeeId);
	    } catch (Exception e) {
	        throw new ServiceException("Failed to check active exit request for employeeId " + employeeId);
	    }
	}

}