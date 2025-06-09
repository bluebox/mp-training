package com.employee;

import com.employee.domain.EmployeeWorkLog;
import com.employee.services.EmployeeAnalytics;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception{

        EmployeeAnalytics analytics = new EmployeeAnalytics();
        List<EmployeeWorkLog> logs = analytics.readExcel("Sample_Employee_WorkLogs.xlsx");

        analytics.writeUrgentLogs(logs, "UrgentLogs.xlsx");

        analytics.writeLowHourLogs(logs, "LowHoursLogs.xlsx");

        analytics.writeTagCounts(logs, "TagCounts.xlsx");
        
        analytics.writeProjectProductivity(logs, "Project-wise productivity.xlsx");
        
        analytics.writeWeekendSummary(logs, "Weekend summary.xlsx");

        System.out.println("Analytics exported");
    }
}