package dev.tulasidhar.case_study_2_excelfilereading;

import java.util.List;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;
import dev.tulasidhar.case_study_2_excelfilereading.util.EmployeeGenerator;
import dev.tulasidhar.case_study_2_excelfilereading.util.ExcelWriter;
import dev.tulasidhar.case_study_2_excelfilereading.util.TaskFive;
import dev.tulasidhar.case_study_2_excelfilereading.util.TaskFour;
import dev.tulasidhar.case_study_2_excelfilereading.util.TaskOne;
import dev.tulasidhar.case_study_2_excelfilereading.util.TaskThree;
import dev.tulasidhar.case_study_2_excelfilereading.util.TaskTwo;

public class App 
{
	 public static void main(String[] args) throws Exception {
		 
	        List<EmployeeWorkLog> employees = EmployeeGenerator.generateEmployees(100);
	        ExcelWriter.writeWorkLogsToExcel(employees, "employees.xlsx");
	        System.out.println("Employee records written to employees.xlsx");
	        
	        try {
	        	TaskOne.taskOneProcess(employees);	
	        	TaskTwo.taskTwoProcess(employees);
	        	TaskThree.taskThreeProcess(employees);
	        	TaskFour.taskFourProcess(employees);
	        	TaskFive.taskFiveProcess(employees);
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        	
	        }
	        
	        
	    }
	 
}
