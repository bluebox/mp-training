package com.employee.dao;

import com.employee.model.EmployeeWorkLog;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EmployeeDataToCSV {
	ReadEmployeeData reader = new ReadEmployeeData();
	
	private List<EmployeeWorkLog> employeeSheet1 = reader.readEmployeeData("D:/New folder/employee/EmployeeDetails1.xlsx");
    private List<EmployeeWorkLog> employeeSheet2 = reader.readEmployeeData("D:/New folder/employee/EmployeeDetails2.xlsx");
    
    public List<EmployeeWorkLog> getEmployeeSheet1() {
		return employeeSheet1;
	}

	public List<EmployeeWorkLog> getEmployeeSheet2() {
		return employeeSheet2;
	}
    public void writeToCSV(List<EmployeeWorkLog> logs, String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
            writer.write("EmployeeID,Name,Department,ProjectID,Date,TaskCategory,HoursWorked,Remarks\n");
            for (EmployeeWorkLog log : logs) {
                writer.write(String.join(",",
                        escape(log.getEmployeeId()),
                        escape(log.getName()),
                        escape(log.getDepartment()),
                        escape(log.getProjectId()),
                        log.getDate().toString(),
                        escape(log.getTaskCategory()),
                        String.valueOf(log.getHoursWorked()),
                        escape(log.getRemarks())
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //varient 2 & 30 csv
    public void writeToCSVVarient2_27(Map<String, List<EmployeeWorkLog>> logs, String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
        	for(Map.Entry<String,List<EmployeeWorkLog>> group:logs.entrySet()) {
        		writer.write(group.getKey()+"\n");
        		for (EmployeeWorkLog employee : group.getValue()) {
                    writer.write(String.join(",",
                            escape(employee.getEmployeeId()),
                            escape(employee.getName()),
                            escape(employee.getDepartment()),
                            escape(employee.getProjectId()),
                            employee.getDate().toString(),
                            escape(employee.getTaskCategory()),
                            String.valueOf(employee.getHoursWorked()),
                            escape(employee.getRemarks())
                    ));
                    writer.newLine();
                }
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //varient 9 csv
    public void writeToCSVVarient9(List<Map.Entry<String, Double>> logs, String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
        	writer.write("Top 5 Employees in last 60 days:\n");
        	for(Map.Entry<String, Double> employee:logs) {
        		writer.write(String.join(" => ",employee.getKey(),String.valueOf(employee.getValue())));
                writer.newLine();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //varient 22 csv
    public void writeToCSVVarient22(Map<String, Double> logs, String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
        	writer.write("\nHour Differences between two sheets:\n");
        	for(Entry<String,Double> employee:logs.entrySet()) {
        		writer.write(String.join(" => ",employee.getKey(),String.join(" ",String.valueOf(employee.getValue()),"hrs")));
                writer.newLine();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //varient 30 csv
    public void writeToCSVVarient30(List<EmployeeWorkLog> logs, String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
        	writer.write("\n--- Suggested Meetings Based on Remarks ---\n");
        	
        	for(EmployeeWorkLog employee:logs) {
        		writer.write("Schedule meeting with " + employee.getName() +" (Employee ID: " + employee.getEmployeeId() + ") from " + employee.getDepartment() +
                        " [Reason: " + employee.getRemarks() + ", Date: " + employee.getDate() +", Time: "+employee.getHoursAndMinutes(employee.getHoursWorked())+"]");
                writer.newLine();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private String escape(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }
}