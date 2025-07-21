package com.demo.employeeelog;


	import java.io.File;
	import java.io.FileInputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;

	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelToMySQL {
	    public static void main(String[] args) {

	        String excelFilePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\employee_data.xlsx";
; 
	        String jdbcURL ="jdbc:mysql://localhost:3306/employeedb";

	        String username = "root";
	        String password = "kavi@2";   

try {
    Class.forName("com.mysql.cj.jdbc.Driver"); 
	        

	        try (
	        		
	
	            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
	            FileInputStream fis = new FileInputStream(excelFilePath);
	            XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        ) {
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            String sql = "INSERT INTO employee_logs (emp_id, employee_name, department, project, task_category, task_description, date, hours_worked, remarks) "
	                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement statement = conn.prepareStatement(sql);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // i=1 to skip header
	                XSSFRow row = sheet.getRow(i);

	                statement.setString(1, row.getCell(0).getStringCellValue());
	                statement.setString(2, row.getCell(1).getStringCellValue());
	                statement.setString(3, row.getCell(2).getStringCellValue()); 
	                statement.setString(4, row.getCell(3).getStringCellValue());
	                statement.setString(5, row.getCell(4).getStringCellValue()); 
	                statement.setString(6, row.getCell(5).getStringCellValue()); 

	                java.util.Date excelDate = row.getCell(6).getDateCellValue(); 
	                statement.setDate(7, new java.sql.Date(excelDate.getTime()));

	                statement.setDouble(8, row.getCell(7).getNumericCellValue()); 
	                statement.setString(9, row.getCell(8).getStringCellValue()); 

	                statement.executeUpdate();
	            }

	            System.out.println("Excel data inserted into MySQL successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
} catch (Exception e) {
    e.printStackTrace();
}
	    }
	}






