package analysis;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.EmployeeWorkLog;
import service.CSVExporter;
import service.ExcelReader;

public class UrgentCriticalLogs {
	
	public static void urgentCriticalLogs(String excelPath, String outCsv) {
		
        List<EmployeeWorkLog> logs = ExcelReader.readExcel(excelPath);
 
        Map<String, Double> result = logs.stream()
            .filter(l -> l.getRemarks().toLowerCase().contains("urgent")
                      || l.getRemarks().toLowerCase().contains("critical"))
            .sorted(Comparator.comparing(EmployeeWorkLog::getName))
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getEmployeeId,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
            ));
        
        CSVExporter.export(
            result,
            new String[]{"EmployeeID","UrgentCriticalHours"},
            e -> new String[]{e.getKey(), String.format("%.2f", e.getValue())},
            outCsv
        );
        
    }
	
}
