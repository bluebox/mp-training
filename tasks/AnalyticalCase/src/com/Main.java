package com;
import java.security.PrivateKey;

import org.apache.commons.collections4.map.StaticBucketMap;

import com.domain.EmployeePojo;
import com.service.CalculateProjectProductivity;
import com.service.CalculateStdPerProject;
import com.service.CalculateWeeklyEfforts;
import com.service.Caliculate7DaysSlidingWindow;
import com.service.DataLoder;
import com.service.EmployeeData;
import com.service.TimeConsumingWork;
import com.service.WriteToExcel;

public class Main {
	
    public static void main(String[] args) {
    	 EmployeeData employeeData = new EmployeeData();
    	 String filePath="/home/mphs/Desktop/mp-training/tasks/AnalyticalCase/src/Sample_Employee_WorkLogs.xlsx";
    	 DataLoder dataLoder= new DataLoder();
    	 dataLoder.dataLoder(employeeData,filePath);
        
        TimeConsumingWork timeConsumingWorkobj = new TimeConsumingWork();
        timeConsumingWorkobj.timeConsumingWork(employeeData.getEmployeeList());
        
        Caliculate7DaysSlidingWindow caliculate7DaysSlidingWindowobj = new Caliculate7DaysSlidingWindow();
        caliculate7DaysSlidingWindowobj.calculate7DaySlidingAverage(employeeData.getEmployeeList());
        
        CalculateWeeklyEfforts calculateWeeklyEffortobj = new CalculateWeeklyEfforts();
        calculateWeeklyEffortobj.calculateWeeklyEffort(employeeData.getEmployeeList());
        
        CalculateProjectProductivity calculateProjectProductivityobj = new CalculateProjectProductivity();
        calculateProjectProductivityobj.calculateProjectProductivity(employeeData.getEmployeeList());
        
        CalculateStdPerProject calculateStdPerProjectobj = new CalculateStdPerProject();
        calculateStdPerProjectobj.calculateStdDevPerProject(employeeData.getEmployeeList());

    }

}


