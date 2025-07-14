package analysis;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.EmployeeWorkLog;
import service.CSVExporter;
import service.ExcelReader;

public class WeekendSummary {
	
	public static void weekendSummary(String excelPath, String outCsv) {
		
        List<EmployeeWorkLog> logs = ExcelReader.readExcel(excelPath);
        
        Map<String, Double> result = logs.stream()
            .filter(l -> {
                DayOfWeek d = l.getDate().getDayOfWeek();
                return d==DayOfWeek.SATURDAY || d==DayOfWeek.SUNDAY;
            })
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getEmployeeId,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
            ));
        
        CSVExporter.export(
            result,
            new String[]{"EmployeeID","WeekendHours"},
            e -> new String[]{e.getKey(), String.format("%.2f", e.getValue())},
            outCsv
        );
    }
	
}
