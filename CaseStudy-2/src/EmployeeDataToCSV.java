package com.employee.dao;

import com.employee.model.EmployeeWorkLog;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeeDataToCSV {
    public void writeToCSV(List<EmployeeWorkLog> logs, String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
            writer.write("EmployeeID,Name,Department,ProjectID,Date,TaskCategory,HoursWorked,Remarks,Time\n");
            for (EmployeeWorkLog log : logs) {
                writer.write(String.join(",",
                        escape(log.getEmployeeId()),
                        escape(log.getName()),
                        escape(log.getDepartment()),
                        escape(log.getProjectId()),
                        log.getDate().toString(),
                        escape(log.getTaskCategory()),
                        String.valueOf(log.getHoursWorked()),
                        escape(log.getRemarks()),
                        log.getTime() != null ? log.getTime().toString() : ""
                ));
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