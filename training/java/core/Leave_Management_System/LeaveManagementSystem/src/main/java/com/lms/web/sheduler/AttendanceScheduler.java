package com.lms.web.sheduler;



import com.lms.web.dao.AttendanceDAO;
import com.lms.web.dao.LeaveRequestsDao;
import com.lms.web.model.Attendance;
import com.lms.web.model.LeaveRequest;
import com.lms.web.service.impl.WorkdayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AttendanceScheduler {

	@Autowired
    private AttendanceDAO attendanceDAO;
	
	@Autowired
	private LeaveRequestsDao leaveRequestDAO;
	
	@Autowired
	private WorkdayService workDayService;


    @Scheduled(cron = "0 59 23 * * *")
    public void validateDailyAttendance() {
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);

        List<Integer> allEmployees = attendanceDAO.getAllEmployeeIds();

        for (Integer empId : allEmployees) {
            Attendance att = null;
            try {
                att = attendanceDAO.getAttendanceForDay(empId, sqlDate);
            } catch (Exception e) {
                attendanceDAO.forceMarkAbsent(empId, sqlDate);
                continue;
            }

         System.out.println("Attendance validation completed for " + today);
    }}
    
    
    @Scheduled(cron = "0 59 23 L * ?")
    public void processLossOfPayForAbsentees() {
        LocalDate prevMonth=LocalDate.now().minusMonths(1);
        LocalDate monthStart = prevMonth.withDayOfMonth(1);
        LocalDate monthEnd = prevMonth.withDayOfMonth(prevMonth.lengthOfMonth());
        
        Date startDate=Date.valueOf(monthStart);
        Date endDate=Date.valueOf(monthEnd);

        leaveRequestDAO.autoApprovePendingLeaves(startDate, endDate);
        List<Date> nonWorkingDays=new ArrayList<>();
        for(int i=1;i<=prevMonth.lengthOfMonth();i++) {
        	LocalDate date=prevMonth.withDayOfMonth(i);
        	boolean isWorkingDay=workDayService.isWorkingDay(date);
        	if(!isWorkingDay) {
        		nonWorkingDays.add(Date.valueOf(date));
        	}	
        }
        Map<Integer,List<Date>> absents = attendanceDAO.getAbsentsBetween(startDate, endDate,nonWorkingDays);
        
        Map<Integer, List<Date>> approvedLeavesByEmployee = leaveRequestDAO.exitsApprovedLeaves(startDate, endDate);
        

        List<LeaveRequest> lossOfPayList = new ArrayList<>();

        for (Map.Entry<Integer, List<Date>> absentEntry : absents.entrySet()) {
            Integer empId = absentEntry.getKey();
            List<Date> absentDates = absentEntry.getValue();

            List<Date> approvedDates = approvedLeavesByEmployee.getOrDefault(empId, new ArrayList<>());

            for (Date absentDate : absentDates) {
                if (!approvedDates.contains(absentDate)) {
                	
                    LeaveRequest lopRequest = new LeaveRequest();
                    lopRequest.setEmployeeId(empId);
                    lopRequest.setLeaveType("LOSS_OF_PAY");
                    lopRequest.setStartDate(absentDate);
                    lopRequest.setEndDate(absentDate);
                    lopRequest.setRemarks("Marked as Loss of Pay");
                    lopRequest.setLeaveStatus("ACCEPTED");


                    lossOfPayList.add(lopRequest);
                }
            }
            
            if(!lossOfPayList.isEmpty()) {
            leaveRequestDAO.markLopforAbsents(lossOfPayList);
            
        }}
        System.out.println("Loss of Pay processing completed for month: " + monthStart.getMonth());
    
}
    
}
