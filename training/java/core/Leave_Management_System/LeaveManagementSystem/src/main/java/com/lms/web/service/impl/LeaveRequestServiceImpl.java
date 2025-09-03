package com.lms.web.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.web.dao.AttendanceDAO;
import com.lms.web.dao.LeaveRequestsDao;
import com.lms.web.exceptions.DAOException;
import com.lms.web.exceptions.ServiceException;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.Leave;
import com.lms.web.model.LeaveRequest;
import com.lms.web.model.LeaveRequestStatus;
import com.lms.web.model.Leaves;
import com.lms.web.service.LeaveRequestService;
import com.lms.web.utils.Validate;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService{

	@Autowired
	private LeaveRequestsDao dao;
    @Autowired private WorkdayService workdayService;
    @Autowired
    private Validate validate;
    
    @Autowired
    private AttendanceDAO attendacedao;
    
    private Map<String, String> holidays;

    
    @Override
    @Transactional
    public void applyLeave(LeaveRequest req) {
    	if(req==null) {
    		throw new ServiceException("Invalid Leave Request");
    	}
        try {
        	validate.validateLeaveRequest(req);
            Leave balance = dao.getLeaveBalance(req.getEmployeeId());
            LocalDate start = req.getStartDate().toLocalDate();
            LocalDate end   = req.getEndDate().toLocalDate();
            
            if (end.isBefore(start))
                throw new ServiceException("End date cannot be before start date");
            if(start.getMonthValue()!=end.getMonthValue()) {
            	throw new ServiceException("Leave Cannot be applied in different month cycles");
            }
            List<LeaveRequest> surroundingCasualLeaves = new ArrayList<>();
            List<LeaveRequest> surroundingOptionalLeaves = new ArrayList<>();
            List<LeaveRequest> surrounding = dao.getSorroundingRequests(req.getEmployeeId(), start, end);
            for (LeaveRequest lr : surrounding) {
                LocalDate s = lr.getStartDate().toLocalDate();
                LocalDate e = lr.getEndDate().toLocalDate();
                boolean overlaps = !(end.isBefore(s) || start.isAfter(e));
                if (overlaps) throw new ServiceException("Overlapping leave request exists with start date " + lr.getStartDate());
                if ((!overlaps) && lr.getLeaveType().equals(String.valueOf(Leaves.CASUAL_LEAVE)))
                	surroundingCasualLeaves.add(lr);
                if((!overlaps) && lr.getLeaveType().equals(String.valueOf(Leaves.OPTIONAL_LEAVE)))
                	surroundingOptionalLeaves.add(lr);
            }
            
            long leavedays = ChronoUnit.DAYS.between(start, end) + 1;
            long workingDays = workdayService.countWorkingDays(start, end);

            switch (req.getLeaveType()) {
                case "CASUAL_LEAVE":
                    if (workingDays == 0) 
                    	throw new ServiceException("No working days in selected CL range");
                    if (workingDays > 3) 
                    	throw new ServiceException("CL cannot exceed 3 working days");
                    if (balance.getCASUAL_LEAVE() < workingDays)
                        throw new ServiceException("Insufficient Casual Leave balance");
                    if(surrounding.size()-surroundingOptionalLeaves.size()>0) 
                    	throw new ServiceException("Casual Leaves Cannot be clubbed with Sick Leaves and Earned Leaves");
                    break;
                    
                case "SICK_LEAVE":
                    if (leavedays <= 0) throw new ServiceException("Invalid SL range");
                    if (balance.getSICK_LEAVE() < leavedays)
                        throw new ServiceException("Insufficient Sick Leave balance");
                    if(surroundingCasualLeaves.size()>0) throw new ServiceException("Sick Leave Cannot Be clubbed with CL");
                    break;

                case "EARNED_LEAVE":
                    if (workingDays == 0) throw new ServiceException("No working days in selected EL range");
                    if (workingDays > 15) throw new ServiceException("EL cannot exceed 15 working days");
                    if (balance.getEARNED_LEAVE() < workingDays)
                        throw new ServiceException("Insufficient Earned Leave balance");
                    if(surroundingCasualLeaves.size()>0) throw new ServiceException("Earned Leave Cannot Be clubbed with CL");
                    break;

                case "OPTIONAL_LEAVE":
                    if (leavedays <= 0) throw new ServiceException("Invalid OP range");
                    if (balance.getOPTIONAL_LEAVE() < leavedays)
                        throw new ServiceException("Insufficient Optional Leave balance (max 2 allowed)");
                    break;

                default:
                    throw new ServiceException("Unsupported leave type: " + req.getLeaveType());
            }
            int absentDays = 0;
            LocalDate myEndDate = LocalDate.now().minusDays(1); ;
            if(end.isAfter(myEndDate) && start.isBefore(LocalDate.now())) {
            	 absentDays = attendacedao.getAbsentCountBetweenFromAndToDates(req.getEmployeeId(), Date.valueOf(start), Date.valueOf(myEndDate));
            }
            else {
            	absentDays = attendacedao.getAbsentCountBetweenFromAndToDates(req.getEmployeeId(), Date.valueOf(start), Date.valueOf(end));
            }
            if(req.getLeaveType().equalsIgnoreCase(String.valueOf(Leaves.SICK_LEAVE))) {
            	if(end.isBefore(LocalDate.now()) && absentDays<leavedays || end.isAfter(myEndDate) && start.isBefore(LocalDate.now()) && absentDays<ChronoUnit.DAYS.between(start, myEndDate) + 1) {
            		throw new ServiceException("You have Marked attendence in between the selected days!");
            	}
            	dao.applyLeave(req,leavedays);
            }else {
            	if(end.isBefore(LocalDate.now()) && absentDays<leavedays || end.isAfter(myEndDate) && start.isBefore(LocalDate.now()) && absentDays<ChronoUnit.DAYS.between(start, myEndDate) + 1) {
            		throw new ServiceException("You have Marked attendence in between the selected days!");
            	}
            	dao.applyLeave(req,workingDays);
            }

        } catch (DAOException | ValidationException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }    
    @Override
    public void approveLeave(int requestId)  {
    	
		try {
			validate.validateId(requestId);
			LeaveRequest request=dao.getRequestById(requestId);
			if(request.getLeaveStatus().equals(String.valueOf(LeaveRequestStatus.PENDING))) {
				request.setLeaveStatus(String.valueOf(LeaveRequestStatus.ACCEPTED));
				long calDays = ChronoUnit.DAYS.between(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate()) + 1;
	            long workingDays = workdayService.countWorkingDays(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate());
				dao.updateLeaveStatus(request,request.getLeaveType().equals("SICK_LEAVE")?calDays:workingDays);
			}
			else {
				throw new DAOException("LeaveRequest "+request.getRequestId()+" is already "+request.getLeaveStatus());
			}
		}catch(DAOException | ValidationException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
    @Override
	 public void rejectLeave(int requestId, String reason) throws ServiceException {
    	if(reason==null) {
    		throw new ServiceException("Reason is mandatory For Rejection");
    	}
    	
		 try {
			 validate.validateId(requestId);
			 LeaveRequest request=dao.getRequestById(requestId);
			 if(request.getLeaveStatus().equals(String.valueOf(LeaveRequestStatus.PENDING))) {
				request.setLeaveStatus(String.valueOf(LeaveRequestStatus.REJECTED));
				request.setRejectionReason(reason);
				long calDays = ChronoUnit.DAYS.between(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate()) + 1;
		        long workingDays = workdayService.countWorkingDays(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate());
				dao.updateLeaveStatus(request,request.getLeaveType().equalsIgnoreCase(String.valueOf(Leaves.SICK_LEAVE))?calDays:workingDays);		
			 }
			else {
				throw new DAOException("LeaveRequest "+request.getRequestId()+" is already "+request.getLeaveStatus());
			}
			} catch (DAOException |ValidationException ex) {
				throw new ServiceException(ex.getMessage());
			}
	    }
    @Override
	 public void cancelLeave(int requestId) throws ServiceException {
		 try {
			validate.validateId(requestId);
			LeaveRequest request=dao.getRequestById(requestId);
			if(request.getLeaveStatus().equals(String.valueOf(LeaveRequestStatus.PENDING))) {
				request.setLeaveStatus(String.valueOf(LeaveRequestStatus.CANCELLED));
				long calDays = ChronoUnit.DAYS.between(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate()) + 1;
		        long workingDays = workdayService.countWorkingDays(request.getStartDate().toLocalDate(), request.getEndDate().toLocalDate());
				dao.updateLeaveStatus(request,request.getLeaveType().equalsIgnoreCase(String.valueOf(Leaves.SICK_LEAVE))?calDays:workingDays);		
		        
			}
			else {
				throw new DAOException("LeaveRequest "+request.getRequestId()+" is already "+request.getLeaveStatus());
			}
			} catch (DAOException |ValidationException ex) {
				throw new ServiceException(ex.getMessage());
			}
	    }

    @Override
	    public List<LeaveRequest> getLeavesById(int employeeId){
	    	try {
	    		validate.validateId(employeeId);
	    		return dao.getLeaveRequestsByEmployeeId(employeeId);
	    	}catch(DAOException |ValidationException ex) {
	    		throw new ServiceException(ex.getMessage());
	    	}
	    }
    
    
    @Override
    public Leave getLeaveBalence(int employeeId){
    	
    	try {
    		validate.validateId(employeeId);
	        return dao.getLeaveBalance(employeeId);
	    	}catch(DAOException |ValidationException ex) {
	    		throw new ServiceException(ex.getMessage());
	    	}
    }

}
