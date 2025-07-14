package analysis;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.EmployeeWorkLog;
import model.ProjectWeekKey;
import service.CSVExporter;
import service.ExcelReader;

public class WeeklyEffortPerProject {
	
	public static void weeklyEffortPerProject(String excelPath, String outCsv) {
		
        List<EmployeeWorkLog> logs = ExcelReader.readExcel(excelPath);
        
        Map<ProjectWeekKey, Double> result = logs.stream()
            .collect(Collectors.groupingBy(
                log -> new ProjectWeekKey(log.getProjectId(), log.getDate()),
                TreeMap::new,
                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
            ));
 
        CSVExporter.export(
            result,
            new String[]{"ProjectWeek","TotalHours"},
            e -> new String[]{e.getKey().toString(), String.format("%.2f", e.getValue())},
            outCsv
            
        );
    }
}
