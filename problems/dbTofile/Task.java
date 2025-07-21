package dbTofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Task {
	private static final String url="jdbc:mysql://127.0.0.1:3306/employeedata";
	private static  final String username="root";
	private static final String password="root";
	
	
	public static void main(String[] args) {
		Connection c=connectionestablish();
		String csvpath="C:\\Users\\DELL\\Downloads\\Employee_Timesheet_May_to_July_2025_FormattedDate.xlsx";
		List<List<String>> records=new ArrayList<>();
		try(XSSFWorkbook wb =new XSSFWorkbook(new FileInputStream(csvpath))){
			XSSFSheet sh=wb.getSheetAt(0);
			int rowcount=sh.getPhysicalNumberOfRows();
			for(int i=0;i<rowcount;i++) {
				XSSFRow row=sh.getRow(i);
				List<String> eachrowlist=new ArrayList<>();
				int cellcount=row.getPhysicalNumberOfCells(); 
				for(int j=0;j<cellcount;j++) {
					eachrowlist.add(sh.getRow(i).getCell(j).toString());
					
				}
				records.add(eachrowlist);
			}
			
		}catch(IOException e) {
			System.out.println("can not read file"+e);
		}
		
		records=records.subList(1,records.size());
		addrecordstodb(c,records);
		dbtocsv(c);
		
		
		
		
		
	}
	public static  Connection connectionestablish() {
		Connection c=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(url, username, password);
			System.out.println("connection established with db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
		

	}
	public static void addrecordstodb(Connection c,List<List<String>> records) {
		
		
		 String query = "insert into employeedata.employeeTask (employee_id, name, department, project_id, date, task_category, hours_worked, remarks) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparestatement=c.prepareStatement(query);
			for (List<String> record : records) {
	            
	            preparestatement.setString(1, record.get(0)); 
	            preparestatement.setString(2, record.get(1)); 
	            preparestatement.setString(3, record.get(2)); 
	            preparestatement.setString(4, record.get(3));
	            
	            LocalDate date=LocalDate.parse(record.get(4),DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	            Date sqldate = Date.valueOf(date);
	            preparestatement.setDate(5, sqldate);

	            preparestatement.setString(6, record.get(5));
	            preparestatement.setDouble(7, Double.parseDouble(record.get(6)));
	            preparestatement.setString(8, record.get(7));

	            preparestatement.executeUpdate();
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void dbtocsv(Connection c) {
		
String query="select * from employeedata.employeeTask ";
		
		try {
			Statement statement=c.createStatement();
			ResultSet res=statement.executeQuery(query);
			XSSFWorkbook workbook=new XSSFWorkbook();
			XSSFSheet sheet=workbook.createSheet("Employee_log_from_db");
			int row=1;
			while(res.next()) {
				sheet.createRow(row);
				sheet.getRow(row).createCell(0).setCellValue(res.getString("employee_id"));
				sheet.getRow(row).createCell(1).setCellValue(res.getString("name"));
				sheet.getRow(row).createCell(2).setCellValue(res.getString("department"));
				sheet.getRow(row).createCell(3).setCellValue(res.getString("project_id"));
				sheet.getRow(row).createCell(4).setCellValue(res.getDate("date").toString());
				sheet.getRow(row).createCell(5).setCellValue(res.getString("task_category"));
				sheet.getRow(row).createCell(6).setCellValue(res.getDouble("hours_worked"));
				sheet.getRow(row).createCell(7).setCellValue(res.getString("remarks"));
				row++;
				
			}
			try {
				File file=new File("C:\\Users\\DELL\\Downloads\\Employee_Timesheet_May_to_July_2025_FormattedDate.xlsx");
				FileOutputStream fop=new FileOutputStream(file); 
				workbook.write(fop);
				}
				catch(IOException e) {
					System.out.println("can not write file "+e);
					
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
