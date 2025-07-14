package EmployeeProductivity;

import java.util.List;

public class Main {
	public static void main(String[] args){
		List<EmployeeWorkLog> logs = ReadingExcel.readExcel("C:\\Users\\DELL\\Downloads\\data.xlsx");

		System.out.println("Employees Logging >10 hrs in a Day");
		EmployeeAnalytics.findOverworkedEmployees(logs);

	    System.out.println("\nProject-Wise Productivity: Total and Average Hours per Employee");
	    EmployeeAnalytics.analyzeProjectProductivity(logs);

	    System.out.println("\nDrop in Hours >40% Compared to Previous Month");
	    EmployeeAnalytics.detectMonthlyDrops(logs);

	    System.out.println("\nDepartment → Top 2 Employees by Total Hours");
	    EmployeeAnalytics.departmentWiseTopPerformers(logs);

	    System.out.println("\nEmployees Who Changed Projects >1 Time/Month");
	    EmployeeAnalytics.identifyFrequentProjectSwitchers(logs);
	}
}
