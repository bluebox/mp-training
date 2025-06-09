package com.employee;

import com.employee.domain.EmployeeWorkLog;
import com.employee.services.CountOfTagsInRemarks;
import com.employee.services.DaysWithLessthanTwoHours;
import com.employee.services.EmployeeAnalytics;
import com.employee.services.ExcelReader;
import com.employee.services.ProjectWiseProductivity;
import com.employee.services.SummarizeWeekendHours;
import com.employee.services.UrgentCriticalLogs;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception{

        ExcelReader reader = new ExcelReader();
        
        List<EmployeeWorkLog> logs = reader.readExcel("Sample_Employee_WorkLogs.xlsx");
        
        
        UrgentCriticalLogs urgentCritical = new UrgentCriticalLogs(); 
        urgentCritical.writeUrgentCriticalLogs(logs, "UrgentLogs.xlsx");
        
        ProjectWiseProductivity projectWiseProductivity = new ProjectWiseProductivity();
        projectWiseProductivity.writeProjectProductivity(logs, "Project-wise productivity.xlsx");
        
        
        SummarizeWeekendHours summarizeWeekendHours = new SummarizeWeekendHours();
        summarizeWeekendHours.writeWeekendSummary(logs, "Weekend summary.xlsx");
        
        DaysWithLessthanTwoHours daysWithLessthanTwoHours = new DaysWithLessthanTwoHours();
        daysWithLessthanTwoHours.writeLowHourLogs(logs, "LowHoursLogs.xlsx");
        
        
        CountOfTagsInRemarks countOfTagsInRemarks = new CountOfTagsInRemarks();
        countOfTagsInRemarks.writeTagCounts(logs, "TagCounts.xlsx");
        
        

        System.out.println("Analytics exported");
    }
}