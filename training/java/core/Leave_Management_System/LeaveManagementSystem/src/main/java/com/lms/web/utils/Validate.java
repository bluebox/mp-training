package com.lms.web.utils;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.web.dao.EmployeeDao;
import com.lms.web.dao.LeaveRequestsDao;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.Attendance;
import com.lms.web.model.Employee;
import com.lms.web.model.ExitRequest;
import com.lms.web.model.LeaveRequest;
import com.lms.web.model.Leaves;
import com.lms.web.model.User;

@Component
public class Validate {
	@Autowired
	LeaveRequestsDao leaveRequestDao;

	@Autowired
	EmployeeDao employeeDao;
	
	public void validateAttendence(Attendance attendence) {
		if (attendence.getEmployeeId() == 0) {
			throw new ValidationException("Employee Id cannot be empty");
        }
		/*if (attendence.getDate() == null) {
			throw new InvalidAttendenceRequestException("Date foR marking attendence cannot be empty");
		}
		
		if(attendence.getAttendenceStatus() == null || attendence.getAttendenceStatus().isBlank()) {
        	throw new IllegalArgumentException("Please enter a valid Status!");
        }
        */
	}
	
	public void validateUser(User user) {
		if(user.getUserEmail() == null || user.getUserEmail().isBlank() || 
				user.getUserPassword() == null || user.getUserPassword().isBlank() || 
				user.getUserRole() == null || user.getUserRole().isBlank()) {
			throw new ValidationException("Please fill all the fields!");
		}
		if(user.getUserEmail().matches("^[a-z]{2,}[a-z0-9]*@[a-z0-9.-]+\\.[a-z]{2,}")) {
			throw new ValidationException("Please enter a valid user email!");
		}
		/*if(user.getUserRole() == null || user.getUserRole().isBlank()) {
			throw new ValidationException("Please enter role!");
		}*/
	}
	public void validateId(int id) {
		if(id <= 0) {
			throw new ValidationException("Invalid Id");
		}
	}
	
	public void validateExitRequest(ExitRequest exitRequest) {
		if(exitRequest.getEmployeeId() == 0) {
			throw new ValidationException("Please provide employeeId!");
		}
		if(exitRequest.getExitDate() == null) {
        	throw new ValidationException("Please enter exit date!");
		}
		if(exitRequest.getRequestStatus() == null || exitRequest.getRequestStatus().toString().isBlank()) {
        	throw new ValidationException("Invalid RequestStatus!");
		}
		if(exitRequest.getRequestSubmittedAt() == null) {
			throw new ValidationException("Exit Date should not be empty!");
		}
		if(exitRequest.getRemarks() == null || exitRequest.getRemarks().isBlank()) {
			throw new ValidationException("Remarks should not be empty!");
		}
	}
	
	public void validateEmployee(Employee employee) {
		
		if(employee.getEmployeeName() == null || employee.getEmployeeName().isBlank() ||
				employee.getEmployeeEmail() == null || employee.getEmployeeEmail().isBlank() ||
				employee.getEmployeeRole() == null || employee.getEmployeeRole().isBlank() ||
				employee.getEmployeeMobileNo() == null || employee.getEmployeeMobileNo().isBlank() ||
				employee.getEmployeeGender() == null || employee.getEmployeeGender().isBlank() ||
				employee.getEmployeeAddress() == null || employee.getEmployeeAddress().isBlank() ||
				employee.getEmployeeDOB() == null || employee.getEmployeeDOJ() == null) {
			
		}
		if(!employee.getEmployeeName().matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$")) {
        	throw new ValidationException("Please enter a valid employee name!");
        }
		if (!employee.getEmployeeEmail().matches("^[a-z]{2,}[a-z0-9]*@[a-z0-9.-]+\\.[a-z]{2,}")) {
        	throw new ValidationException("Please enter a valid email address!");
        }
		if (!(employee.getEmployeeRole().toUpperCase().equals("ADMIN") ||employee.getEmployeeRole().toUpperCase().equals("MANAGER") || employee.getEmployeeRole().toUpperCase().equals("EMPLOYEE"))) {
            throw new ValidationException("Please Enter a  Must be ADMIN or MANAGER or EMPLOYEE");
        }
		if (!employee.getEmployeeMobileNo().matches("^[6-9][0-9]{9}$")) {
			throw new ValidationException("Please enter a valid mobile number!");
	    }
        if (!(employee.getEmployeeGender().equals("M") || employee.getEmployeeGender().equals("F"))) {
            throw new ValidationException("Invalid gender, Must be M or F");
        }
	}
	
	
	public void validateLeaveRequest(LeaveRequest leaveRequest){
		if (leaveRequest.getEmployeeId() == 0) {
			throw new ValidationException("Employee Id cannot be empty");
        }
		if(!(leaveRequest.getLeaveType().equalsIgnoreCase(String.valueOf(Leaves.CASUAL_LEAVE))||leaveRequest.getLeaveType().equals(String.valueOf(Leaves.EARNED_LEAVE)) || leaveRequest.getLeaveType().equals(String.valueOf(Leaves.OPTIONAL_LEAVE)) || leaveRequest.getLeaveType().equals(String.valueOf(Leaves.SICK_LEAVE)))) {
			throw new ValidationException("Leave Type cannot be empty");
		}
		if(leaveRequest.getStartDate() == null ) {
			throw new ValidationException("StartDate should not be empty");	
		}
		if(leaveRequest.getEndDate() == null ) {
			throw new ValidationException("EndDate should not be empty");	
		}
		if(leaveRequest.getStartDate().compareTo(leaveRequest.getEndDate())>0) {
			throw new ValidationException("StartDate should be less than EndDate");
		}
		if(leaveRequest.getRemarks() == null || leaveRequest.getRemarks().isBlank()) {
			throw new ValidationException("Remarks should not be empty");	
		}
		if(employeeDao.getEmployeesById(leaveRequest.getEmployeeId())==null) {
			throw new ValidationException("Invalid Employee Id");	
		}
		
		long days=ChronoUnit.DAYS.between(leaveRequest.getEndDate().toLocalDate(), leaveRequest.getStartDate().toLocalDate()) + 1;
		if(leaveRequest.getLeaveType().equals(String.valueOf(Leaves.CASUAL_LEAVE))) {
			
			if(leaveRequestDao.getLeaveBalance(leaveRequest.getEmployeeId()).getCASUAL_LEAVE() < days) {
				throw new ValidationException("You Have Insufficient Casual Leaves to Apply");
			}
		}
		else if(leaveRequest.getLeaveType().equals(String.valueOf(Leaves.EARNED_LEAVE))) {
			
			if(leaveRequestDao.getLeaveBalance(leaveRequest.getEmployeeId()).getEARNED_LEAVE() < days) {
				throw new ValidationException("You Have Insufficient Earned Leaves to Apply");
			}
		}
		else if(leaveRequest.getLeaveType().equals(String.valueOf(Leaves.SICK_LEAVE))) {
			
			if(leaveRequestDao.getLeaveBalance(leaveRequest.getEmployeeId()).getSICK_LEAVE() < days) {
				throw new ValidationException("You Have Insufficient Sick Leaves to Apply");
			}
		}
		else if(leaveRequest.getLeaveType().equals(String.valueOf(Leaves.OPTIONAL_LEAVE))){
			
			if(leaveRequestDao.getLeaveBalance(leaveRequest.getEmployeeId()).getOPTIONAL_LEAVE() < days) {
				throw new ValidationException("You Have Insufficient Optional Leaves to Apply");
			}
		}
	}
}