package emp;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static void main(String[] args) throws IOException {
		String filePath = "src/dataFiles/Updated_Employee_Task_Log.xlsx";
		List<Employee> edata = readExcelData(filePath);

//		System.out.println(edata);

		System.out.println("Critical or urgent");
		method1(edata);
		System.out.println("Bug fixing");
		method2(edata);
	}

	public static List<Employee> readExcelData(String filePath) throws IOException {
		List<Employee> logs = new ArrayList<>();
		FileInputStream fileinput = new FileInputStream(filePath);
		Workbook workBook = new XSSFWorkbook(fileinput);
		Sheet sheet = workBook.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			String empId = row.getCell(0).getStringCellValue();
			String ename = row.getCell(1).getStringCellValue();
			String dept = row.getCell(2).getStringCellValue();
			String projectId = row.getCell(3).getStringCellValue();
			LocalDate date = row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String taskcatogaries = row.getCell(5).getStringCellValue();
			double hoursWorked = row.getCell(6).getNumericCellValue();
			String remarks = row.getCell(7).getStringCellValue();
			logs.add(new Employee(empId, ename, dept, projectId, date, taskcatogaries, hoursWorked, remarks));
		}
		return logs;

	}

	public static void method1(List<Employee> logs) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method1.csv"))) {
			bw.write("Emp Id,Name,Dept,ProjectID,Date,taskcategory,workhours,remarks \n");
			logs.stream()
					.filter(log -> log.getRemarks().toLowerCase().contains("critical")
							|| log.getRemarks().toLowerCase().contains("urgent"))
					.sorted(Comparator.comparing(Employee::getName)).forEach(log -> {
						try {
							bw.write(String.format("%s,%s,%s,%s,%s,%s,%.2f,%s \n", log.getEmployeeId(), log.getName(),
									log.getDepartment(), log.getProjectId(), log.getDate(), log.getTaskCategory(),
									log.getHoursWorked(), log.getRemarks()));

						} catch (IOException e) {

						}
					});
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void method2(List<Employee> logs)
	{
		try (BufferedWriter bw=new BufferedWriter(new FileWriter("method2.csv")))
		{
			bw.write("day,EmpId,Name,Dept,ProjectID,Date,taskcategory,workhours,remarks \n");
			logs.stream()
			.filter(log ->log.getTaskCategory().equalsIgnoreCase("bug fixing"))
			.collect(Collectors.groupingBy(log->log.getDate().getDayOfWeek()))
			.forEach((day,list)->{
				list.forEach(log->{
					try {
						bw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%.2f,%s \n",day,log.getEmployeeId(),log.getName(),log.getDepartment(),
								log.getProjectId(),log.getDate(),log.getTaskCategory(),log.getHoursWorked(),log.getRemarks()));
						
					}
					catch(Exception e)
					{
						
					}
				});
			});
					
			
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
	}
	public static void method3(List<Employee> logs)
	{
		try (BufferedWriter bw=new BufferedWriter(new FileWriter("method3.csv")))
		{
			bw.write("day,EmpId,Name,Dept,ProjectID,Date,taskcategory,workhours,remarks \n");
			logs.stream()
			.collect(Collectors.groupingBy(log->log.getDate().getDayOfWeek()))
			.forEach((day,list)->{
				list.forEach(log->{
					try {
						bw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%.2f,%s \n",day,log.getEmployeeId(),log.getName(),log.getDepartment(),
								log.getProjectId(),log.getDate(),log.getTaskCategory(),log.getHoursWorked(),log.getRemarks()));
						
					}
					catch(Exception e)
					{
						
					}
				});
			});
					
			
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

}
