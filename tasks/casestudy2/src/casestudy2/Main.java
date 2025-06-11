package casestudy2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.EmployeeTask;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		List< EmployeeTask > EmployeeTasksData = new ArrayList<>();
		
		String file = "/home/developer/Downloads/Sample_Employee_WorkLogs.csv";
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		// skip heading row 
		br.readLine();
		String line = br.readLine();
		while(line != null)
		{
			String employeeId = line.split(",")[0];
			String employeeName = line.split(",")[1];
			String employeeDepartment = line.split(",")[2];
			String employeeProjectId = line.split(",")[3];
			LocalDate taskDate = LocalDate.parse(line.split(",")[4]);
			String employeeTaskCategory = line.split(",")[5];
			double hoursWorked = Double.parseDouble(line.split(",")[6]);
			String employeeRemarks = line.split(",")[7];
			
			EmployeeTask task= new EmployeeTask(employeeId ,employeeName, employeeDepartment,employeeProjectId, taskDate, employeeTaskCategory, hoursWorked, employeeRemarks) ;
			
			EmployeeTasksData.add(task);
			line = br.readLine();
		}
		for (EmployeeTask task : EmployeeTasksData) {
			System.out.println(task.toString());
		}
}
}
