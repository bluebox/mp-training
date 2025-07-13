package dev.tulasidhar.case_study_2_excelfilereading.util;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;

public class TaskOne {
	public static void taskOneProcess(List<EmployeeWorkLog> employees ) throws Exception {
		List<EmployeeWorkLog> filteredTask1 = employees.stream()
                .filter(e -> e.getHoursWorked() > 100 && (e.getDateTime().getMonthValue() <= 5 && e.getDateTime().getMonthValue() >= 3))
                .collect(Collectors.toList());
        
        writeWorkLogsToCSV(filteredTask1, "task1results.csv");
	}
	
	 static void writeWorkLogsToCSV(List<EmployeeWorkLog> logs, String fileName) throws Exception {
	        try (FileWriter writer = new FileWriter(fileName)) {
	            writer.write("EmployeeId,Name,Department,ProjectId,Date,TaskCategory,HoursWorked,Remarks\n");
	            for (EmployeeWorkLog log : logs) {
	                writer.write(String.format("%s,%s,%s,%s,%s,%s,%.2f,%s\n",
	                    log.getEmployeeId(),
	                    log.getName(),
	                    log.getDepartment(),
	                    log.getProjectId(),
	                    log.getDateTime(),
	                    log.getTaskCategory(),
	                    log.getHoursWorked(),
	                    log.getRemarks()));
	            }
	        }
	    }
}
