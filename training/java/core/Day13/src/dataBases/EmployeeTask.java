package dataBases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.xdevapi.Result;

public class EmployeeTask {
	
	public static void main(String[] args) {
		
		List<EmployeeWorkLog> EmployeeWorkLogs=excelReader("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\New_Employee_logs.csv");
		if(EmployeeWorkLogs.size()==0) {
			return;
		}
		
		//EmployeeWorkLogs.forEach(System.out::println);
		
		
		MysqlDataSource ds=new MysqlDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/example");
		ds.setUser("root");
		ds.setPassword("root");
		try {
			Connection conn=ds.getConnection();
			Statement state=conn.createStatement();
			//state.executeUpdate("CREATE TABLE EMPLOYEES (EmployeeId varchar(70), Name varchar(70), Department varchar(70), ProjectId varchar(70) , TaskCategory varchar(70),Hours Double, Remarks varchar(70))");
			PreparedStatement insertion=conn.prepareStatement("insert into employees(EmployeeId,Name,Department,ProjectId,TaskCategory,Hours,Remarks) values(?,?,?,?,?,?,?)");
			for(EmployeeWorkLog log : EmployeeWorkLogs) {
				insertion.setString(1, log.getEmployeeId());
				insertion.setString(2, log.getName());
				insertion.setString(3, log.getDepartment());
				insertion.setString(4, log.getProjectId());
				insertion.setString(5, log.getTaskCategory());
				insertion.setDouble(6, log.getHoursWorked());
				insertion.setString(7, log.getRemarks());
				insertion.addBatch();
			}
			insertion.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<EmployeeWorkLog> excelReader(String path){
		
		List<EmployeeWorkLog> EmployeeWorkLogs=new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isHeader = true;
            int count=1;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; 
                }
                String[] tokens = line.split(",", -1);
                	
                String employeeId ="";
                if(!isValidId(tokens[0].trim())) {
                	System.out.println("invalid Id at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                employeeId=tokens[0].trim();
                
                String name = "";
                if(!isValid(tokens[1].trim())) {
                	System.out.println("invalid name at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                name=tokens[1].trim();
                
                String department ="";
                if(!isValidDept(tokens[2].trim())){
                	System.out.println("invalid department name at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                department=tokens[2].trim();
                
                String projectId = "";
                if(!isValidId(tokens[3].trim())) {
                	System.out.println("invalid Id at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                projectId=tokens[3].trim();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(tokens[4].trim(), formatter);
                
                String taskCategory = "";
                if(!isValid(tokens[5].trim())) {
                	System.out.println("invalid task category name at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                taskCategory=tokens[5].trim();
                		
                double hoursWorked = (Double.parseDouble(tokens[6].trim()))<0?0:(Double.parseDouble(tokens[6].trim()))>10?0:(Double.parseDouble(tokens[6].trim()));
                String remarks = "";
                if(!isValid(tokens[7].trim())) {
                	System.out.println("invalid remarks at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                remarks=tokens[7].trim();
                
                count++;
                EmployeeWorkLogs.add(new EmployeeWorkLog(employeeId, name, department, projectId, date, taskCategory, hoursWorked, remarks));
            }
                }

         catch (Exception e) {
            e.printStackTrace();
        }

		return EmployeeWorkLogs;
	}
	
	private static boolean isValidDept(String trim) {
		// TODO Auto-generated method stub
		if (trim == null || trim.isEmpty()) {
	        return false;
	    }
		Pattern pattern = Pattern.compile(new String ("^[a-zA-Z/]"),Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(trim);
	    return (matcher.find());
	}


	private static boolean isValidId(String trim) {
		// TODO Auto-generated method stub
		if (trim == null || trim.isEmpty()) {
	        return false;
	    }
		Pattern pattern = Pattern.compile(new String ("^[a-zA-Z0-9]"),Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(trim);
	    return (matcher.find());
	}


	private static boolean isValid(String trim) {
		if (trim == null || trim.isEmpty()) {
	        return false;
	    }
	    Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"),Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(trim);
	    return (matcher.find());
		
	}
}


