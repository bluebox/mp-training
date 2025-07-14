package analysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.EmployeeWorkLog;
import service.CSVExporter;
import service.ExcelReader;

public class BugFixByCategoryDay {
	
	public static void bugFixByCategoryDay(String excelPath, String outCsv) {
		
        List<EmployeeWorkLog> logs = ExcelReader.readExcel(excelPath);
        Map<String, Double> result = logs.stream()
            .filter(l -> l.getTaskCategory().equalsIgnoreCase("Bug Fixing"))
            .collect(Collectors.groupingBy(
                l -> l.getTaskCategory() + "|" + l.getDate().getDayOfWeek(),
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
            ));
        
        CSVExporter.export(
            result,
            new String[]{"Category_Day","TotalHours"},
            e -> new String[]{e.getKey(), String.format("%.2f", e.getValue())},
            outCsv
        );
        
    }
}
