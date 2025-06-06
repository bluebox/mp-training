package csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/*
 * CSV file format is given below for reference...
 * File stores the logs of employee's daily activity.
 * These logs are analyzed to know the productivity of the employee.
 * 
 * 								CSV file format
 *  EmployeeId,Name,Department,ProjectId,Date,TaskCatagory,HoursWorked,Remarks
 * 
 * */


public class CsvReader {
	
	public BufferedReader readCSV(String path)
	{
		if((path == null)||(path == ""))
		{
			return null;
		}
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(path))); 
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return br;
	}
	
	
	public ArrayList<Employee> decipherCSV(BufferedReader br)
	{
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		try {
			String line;
			String[] labels = null;
			int i = 0;
			do {
				HashMap<String,String> entry = new HashMap<String, String>();
				line = br.readLine();
				if(line != null)
				{
					if(i == 0)
					{
						labels = line.split(",");
					}else
					{
						
						String[] fields = line.split(",");
						for(int j = 0;j<fields.length;j++)
						{
							entry.put(labels[j].trim(), fields[j].trim());
						}
						if(fields.length<labels.length)
						{
							entry.put("Remarks", null);
						}
						System.out.println(line);
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//						System.out.print(entry.get("Date"));
						LocalDate date = LocalDate.parse(entry.get("Date"),dtf);
						double hoursWorked = Double.parseDouble(entry.get("HoursWorked"));
						
						Employee emp = new Employee(entry.get("EmployeeId"),entry.get("Name"),entry.get("Department"),entry.get("ProjectId"),date,
								entry.get("TaskCatagory"),hoursWorked, entry.get("Remarks"));
						
						employees.add(emp);
						
						
					}
				}
				i++;
			}while(line != null);
		}catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		
		return employees;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Hello");
		CsvReader reader = new CsvReader();
		List<Employee> empDetails = reader.decipherCSV(reader.readCSV("/home/mphs/Desktop/mp-training/training/java/core/FileSystem/src/csv/emp.csv"));
		System.out.println(empDetails.size());
		for(var emp:empDetails)
		{
			System.out.println(emp);
		}
		
	}

}
