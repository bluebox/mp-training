package EmployeeCaseStudy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Main {	

//	static String filepath= "/home/karthik-malasani/Data/employee_data.csv";
	
   static  String excelFilePath = "/home/karthik-malasani/Data/employee_data.xls";

	static List<String> headers = new ArrayList<>();
	
	static List<Employee> employee= new ArrayList<>();
	
    static DataFormatter formatter = new DataFormatter();

	
	
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = null;
        Workbook workbook = null;

        try {
            try {
				fis = new FileInputStream(excelFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
             workbook = new HSSFWorkbook(fis);   

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            // Read headers
            if (iterator.hasNext()) {
                Row headerRow = iterator.next();
                for (Cell cell : headerRow) {
                    headers.add(formatter.formatCellValue(cell));
                }
            }

            // Read rows
            while (iterator.hasNext()) {
                Row row = iterator.next();

                Employee emp = new Employee(
                        formatter.formatCellValue(row.getCell(0)),
                        formatter.formatCellValue(row.getCell(1)),
                        formatter.formatCellValue(row.getCell(2)),
                        formatter.formatCellValue(row.getCell(3)),
                        LocalDate.parse(formatter.formatCellValue(row.getCell(4))),
                        formatter.formatCellValue(row.getCell(5)),
                        Double.valueOf(formatter.formatCellValue(row.getCell(6))),
                        formatter.formatCellValue(row.getCell(7))
                );

                employee.add(emp);
            }

            System.out.println("Read done: " + employee.size() + " records.");

        }catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        
        

//		try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
//			
//			String line;
//			
//			if((line=br.readLine())!=null) {
//				headers = Arrays.asList(line.split(","));
//			}
//			
//			while((line=br.readLine())!=null) {
//				String[] values = line.split(",");
//				
//				employee.add(new Employee(values[0],
//											values[1],
//											values[2],
//											values[3],
//											LocalDate.parse(values[4]),
//											values[5],
//											Double.valueOf(values[6]),
//											values[7]));
//			}
//		
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
						
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
