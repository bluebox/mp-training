package streams;

import java.util.ArrayList;

import csv.CsvReader;
import csv.Employee;

public class Example {
	
	public static void main(String[] args) {
		CsvReader reader = new CsvReader();
		ArrayList<Employee>records = reader.decipherCSV(reader.readCSV("/FileSystem/src/csv/Sample_Employee_WorkLogs.csv")).stream().collect();
		
	}
	
}
