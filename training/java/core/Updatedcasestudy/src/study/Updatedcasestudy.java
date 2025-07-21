package study;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Updatedcasestudy {
	
	private final String URL = "jdbc:mysql://localhost:3306/filereader";
    private final String USER = "devuser";  
    private final String PASSWORD = "Bobby@514";  
    
    

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
         Connection con= DriverManager.getConnection(URL, USER, PASSWORD);
         return con;
    }

    public static List<EmployeeWorkLog> readExcel() throws Exception {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        FileInputStream fis = new FileInputStream("C://Users/rohith reddy/Dropbox/My PC (LAPTOP-I2UV09L7)/Desktop/Case Study-2/input_data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
          XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            
            String empId = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String dept = row.getCell(2).getStringCellValue();
            String projId = row.getCell(3).getStringCellValue();

            LocalDate date;
            Cell dateCell = row.getCell(4);
            if (DateUtil.isCellDateFormatted(dateCell)) {
                date = dateCell.getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            } else {
                date = dateCell.getLocalDateTimeCellValue().toLocalDate();
            }
            
            String category = row.getCell(5).getStringCellValue();
            double hours = row.getCell(6).getNumericCellValue();
            String remarks = row.getCell(7).getStringCellValue();

            logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, category, hours, remarks));
        }

        workbook.close();
        fis.close();
        return logs;
    }
    public void insertLogsToDB(List<EmployeeWorkLog> logs) {
        String insertSQL = "INSERT INTO filereader.employeedata " +
                "(employeeid,name, department, projectid, date, taskcategory, hoursworked, remarks) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            for (EmployeeWorkLog log : logs) {
                stmt.setString(1, log.getEmployeeId());
                stmt.setString(2, log.getName());
                stmt.setString(3, log.getDepartment());
                stmt.setString(4, log.getProjectId());
                
               LocalDate date=LocalDate.parse(""+log.getDate(),DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
            		   
                stmt.setDate(5, java.sql.Date.valueOf(date));
                
                stmt.setString(6, log.getTaskCategory());
                stmt.setDouble(7, log.getHoursWorked());
                stmt.setString(8, log.getRemarks());

                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("All the data successfully inserted into MySQL.");
        } catch (SQLException e) {
            System.err.println("SQL error while inserting data: " + e.getMessage());
        }
    }


        public static void main(String[] args) {
            try {
                Updatedcasestudy app = new Updatedcasestudy();
                List<EmployeeWorkLog> logs = app.readExcel();
                app.insertLogsToDB(logs);
                app.writeLogsToCSV(logs, "output.csv");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("bhcsbc");
            
            
    }
        public void writeLogsToCSV(List<EmployeeWorkLog> logs, String outputPath) {
            try (FileWriter writer = new FileWriter(outputPath)) {
            	
                writer.append("Employee ID,Name,Department,Project ID,Date,Task Category,Hours Worked,Remarks\n");
    
                
                
                for (EmployeeWorkLog log : logs) {
                    writer.append(log.getEmployeeId()).append(",")
                          .append(log.getName()).append(",")
                          .append(log.getDepartment()).append(",")
                          .append(log.getProjectId()).append(",")
                          .append(log.getDate().toString()).append(",")
                          .append(log.getTaskCategory()).append(",")
                          .append(String.valueOf(log.getHoursWorked())).append(",")
                          .append(log.getRemarks().replace(",", " ")).append("\n");
                }

                System.out.println(" Data successfully written to CSV: " + outputPath);
            } catch (IOException e) {
                System.err.println(" Error writing to CSV: " + e.getMessage());
            }
        }


    
}