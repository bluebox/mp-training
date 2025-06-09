package com;
import com.domain.EmployeePojo;
import com.service.AnalyseData;
import com.service.DataLoder;
import com.service.EmployeeDataAnalytics;
import com.service.WriteToExcel;

public class Main {
	
    public static void main(String[] args) {
        EmployeeDataAnalytics employeeDataAnalytics = new EmployeeDataAnalytics();
        EmployeePojo employeePojo = null;
        DataLoder dataLoder=new DataLoder();
        AnalyseData analyseData=new AnalyseData();
        
        dataLoder.dataLoder(employeeDataAnalytics, employeePojo);
        
        analyseData.timeConsumingWork(employeeDataAnalytics.getEmployeeList());
        analyseData.calculate7DaySlidingAverage(employeeDataAnalytics.getEmployeeList());
        analyseData.calculateWeeklyEffort(employeeDataAnalytics.getEmployeeList());
        analyseData.calculateProjectProductivity(employeeDataAnalytics.getEmployeeList());
        analyseData.calculateStdDevPerProject(employeeDataAnalytics.getEmployeeList());

    }

}


