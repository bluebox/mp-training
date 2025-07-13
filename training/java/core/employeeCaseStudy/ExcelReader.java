package employeeCaseStudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private List<EmployeeWorkLog> employees;
	private String fileName;

	public List<EmployeeWorkLog> getEmployees() {
		return employees;
	}

	public ExcelReader(String fileName) {
		this.fileName = fileName;
		employees = new ArrayList<>();
		try {
			obtainData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void obtainData() throws IOException {
	    try {
	        FileInputStream file = new FileInputStream(fileName);
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        boolean firstRow = true;

	        for (Row row : sheet) {
	            if (firstRow) {
	                firstRow = false;
	                continue;
	            }

	            String employeeId = row.getCell(0).getStringCellValue();
	            String name = row.getCell(1).getStringCellValue();
	            String department = row.getCell(2).getStringCellValue();
	            String projectId = row.getCell(3).getStringCellValue();
	            Date date=row.getCell(4).getDateCellValue();
	            LocalDate localDate = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
//	            System.out.println(date);
	            String taskCategory = row.getCell(5).getStringCellValue();
	            double hoursWorked = row.getCell(6).getNumericCellValue();
	            String remarks = row.getCell(7).getStringCellValue();

	            EmployeeWorkLog employee = new EmployeeWorkLog(
	                employeeId, name, department, projectId, localDate,
	                taskCategory, hoursWorked, remarks
	            );

	            employees.add(employee);
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

}
