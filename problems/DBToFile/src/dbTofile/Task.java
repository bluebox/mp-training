package dbTofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
			for(int i=1;i<rowcount;i++) {
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
	            
	            LocalDate date=LocalDate.parse(""+record.get(4),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	            Date sqldate = java.sql.Date.valueOf(date);
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
		
		  String csvOutputPath = "output.csv";
		
		 try (Statement stmt = c.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM employeedata.employeeTask");
                 FileWriter csvWriter = new FileWriter(csvOutputPath)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                // Write header
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(meta.getColumnName(i));
                    if (i < columnCount) csvWriter.append(",");
                }
                csvWriter.append("\n");

                // Write rows
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        csvWriter.append(rs.getString(i));
                        if (i < columnCount) csvWriter.append(",");
                    }
                    csvWriter.append("\n");
                }

                System.out.println("Data written to output.csv");

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
			
		
	}
}
