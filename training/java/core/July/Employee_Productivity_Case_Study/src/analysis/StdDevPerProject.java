package analysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.EmployeeWorkLog;
import service.CSVExporter;
import service.ExcelReader;

public class StdDevPerProject {
	public static void stdDevPerProject(String excelPath, String outCsv) {
		
        List<EmployeeWorkLog> logs = ExcelReader.readExcel(excelPath);
        
        Map<String, Double> stddev = logs.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getProjectId,
                Collectors.mapping(EmployeeWorkLog::getHoursWorked, Collectors.toList())
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> {
                    List<Double> hrs = e.getValue();
                    double avg = hrs.stream().mapToDouble(d->d).average().orElse(0);
                    double variance = hrs.stream().mapToDouble(d->(d-avg)*(d-avg)).sum() / hrs.size();
                    return Math.sqrt(variance);
                }
            ));
        
        CSVExporter.export(
            stddev,
            new String[]{"ProjectID","StdDevHours"},
            e -> new String[]{e.getKey(), String.format("%.2f", e.getValue())},
            outCsv
        );
        
    }
}
