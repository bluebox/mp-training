package dev.tulasidhar.case_study_2_excelfilereading.util;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;

public class TaskFour {
	public static void taskFourProcess(List<EmployeeWorkLog> employees) throws Exception{
		Map<String, Long> remarkCounts = employees.stream()
                .collect(Collectors.groupingBy(
                    EmployeeWorkLog::getRemarks,
                    Collectors.counting()
                ));
        writeRemarkCountsToCSV(remarkCounts, "task4_remarks_count.csv");
	}
	
	private static void writeRemarkCountsToCSV(Map<String, Long> data, String fileName) throws Exception {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Remark,Count\n");
            data.forEach((remark, count) -> {
                try {
                    writer.write(String.format("%s,%d\n", remark, count));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
	}
	
}
