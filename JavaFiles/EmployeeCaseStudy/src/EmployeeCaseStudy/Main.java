package EmployeeCaseStudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Main {

	static String filepath= "/home/karthik-malasani/Data/employee_data.csv";
	
	static List<String> headers = new ArrayList<>();
	
	static List<Employee> employee= new ArrayList<>();
	
	
	public static void main(String[] args) {
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
			
			String line;
			
			if((line=br.readLine())!=null) {
				headers = Arrays.asList(line.split(","));
			}
			
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
				
				employee.add(new Employee(values[0],
											values[1],
											values[2],
											values[3],
											LocalDate.parse(values[4]),
											values[5],
											Double.valueOf(values[6]),
											values[7]));
			}
		
			System.out.println("------------------------------------------------------");	
			
			//Q7
			System.out.println("Identify employees logging >10 hrs in a day : \n");
			EmployeesLoggingMoreThan10Hours.Q7(employee,headers);
			System.out.println("------------------------------------------------------");	
			
			//Q12
			System.out.println("Project-wise productivity: total and average hours per employee");
			ProjectProductivitySummary.Q12(employee);
			System.out.println("------------------------------------------------------");
			
			//Q20
			System.out.println("Drop in hours >40% vs previous month.");
			DropInHours.Q20(employee);
			System.out.println("------------------------------------------------------");

			//Q24
			System.out.println("Custom collector: department to top 2 employees by hours. : \n");
			DepartmentTop2.Q24(employee,headers);
			System.out.println("------------------------------------------------------");
		
			//Q33
			System.out.println("Employees changing projects more than once/month:  \n");
			EmployeesChangingProjects.Q33(employee,headers);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
