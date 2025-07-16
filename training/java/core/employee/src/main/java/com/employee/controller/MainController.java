package com.employee.controller;

import com.employee.dao.EmployeeDataToCSV;
import com.employee.model.EmployeeWorkLog;
import com.employee.model.Varients;
import com.employee.service.VarientServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        EmployeeDataToCSV exporter = new EmployeeDataToCSV();
        List<EmployeeWorkLog> employeeSheet1 =exporter.getEmployeeSheet1();
        List<EmployeeWorkLog> employeeSheet2 =exporter.getEmployeeSheet2();
	    System.out.println("-----Welcome to Employee Productivity & Analytics System-----");
	        int option = 0;
	        do {
	        	Scanner scanner=new Scanner(System.in);
	        	option=VarientServiceImpl.displayOptions(scanner,option);
	    		try {
	    			Varients[] varients=Varients.values();
		    		switch(varients[option]){
		    		case VARIENT_1:
		    			 // Writing Employee Data to Csv
		    			  exporter.writeToCSV(employeeSheet1, "EmployeeData.csv");
		    			  System.out.println("\nData Exported To \"EmployeeData.csv\" Successfully\n ");
		    			  break;
		    		case VARIENT_2:
		    			 // Variant 2 
		    	          Map<String, List<EmployeeWorkLog>> deptTasks =VarientServiceImpl.timeConsuming_PerDepartment(employeeSheet1);
		    			  exporter.writeToCSVVarient2_27(deptTasks,"Varient2.csv");
		    			  System.out.println("\nData Exported To \"Varient2.csv\" Successfully\n ");
		    			  break;
		    		case VARIENT_3:
		    			// Variant 9
		    			  List<Map.Entry<String, Double>> highWorkingHours=VarientServiceImpl.top5EmployeesLast60Days(employeeSheet1);
		    			  exporter.writeToCSVVarient9(highWorkingHours,"Varient9.csv");
		    			  System.out.println("\nData Exported To \"Varient9.csv\" Successfully\n ");
		    			  break;

		    		case VARIENT_4:
		    			// Variant 22
		    	        Map<String, Double> employeeHoursDifference=VarientServiceImpl.computeHourDifferences(employeeSheet1, employeeSheet2);
		    	        exporter.writeToCSVVarient22(employeeHoursDifference,"Varient22.csv");
		    	        System.out.println("\nData Exported To \"Varient22.csv\" Successfully\n ");
		    			  break;
		    		case VARIENT_5:
		    			// Variant 27
		    	        Map<String, List<EmployeeWorkLog>> groupByTime =VarientServiceImpl.groupByTimePeriod(employeeSheet1);
		    	        exporter.writeToCSVVarient2_27(groupByTime,"Varient27.csv");
		    	        System.out.println("\nData Exported To \"Varient27.csv\" Successfully\n ");
		    			  break;
		    		case VARIENT_6:
		    			// Variant 30
		    	        List<EmployeeWorkLog> meetingLogs = VarientServiceImpl.suggestMeetingsBasedOnRemarks(employeeSheet1);
		    	        exporter.writeToCSVVarient30(meetingLogs,"Varient30.csv");
		    	        System.out.println("\nData Exported To \"Varient30.csv\" Successfully\n ");
		    			  break;
		    		case QUIT:
		    			System.out.println("Thank You! You Are Exiting From Our System");
		    			scanner.close();
		    		}
	    		}
	    		catch(ArrayIndexOutOfBoundsException e) {
	    			System.out.println("You Entered An Invalid Input(Out Of Range)!!! Please Try Again.");
	    			return;
	    		}	
	    		catch(Exception e) {
	    			System.out.println("You Entered An Invalid Input!!! You Are Exiting From Our System...");
	    		}
	    	}while(option!=0);
    }
}