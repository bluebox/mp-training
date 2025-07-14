package EmployeeProductivity;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeAnalytics {

	public static void findOverworkedEmployees(List<EmployeeWorkLog> logs) {
		List<String[]> output = new ArrayList<>();
	    output.add(new String[]{"Employee ID", "Name", "Date", "Hours Worked"});

	    logs.stream()
	        .filter(log -> log.getHoursWorked() > 10)
	        .forEach(log -> output.add(new String[]{
	            log.getEmployeeId(),
	            log.getName(),
	            log.getDate().toString(),
	            String.valueOf(log.getHoursWorked())
	        }));

	    CSVExport.exportToCSV("overworked_employees.csv", output);
	}

	public static void analyzeProjectProductivity(List<EmployeeWorkLog> logs) {
		List<String[]> output = new ArrayList<>();
	    output.add(new String[]{"Project ID", "Employee ID", "Total Hours", "Average Hours"});
	    
	    Map<String, Map<String, DoubleSummaryStatistics>> projectData = logs.stream()
	            .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
	                Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
	                    Collectors.summarizingDouble(EmployeeWorkLog::getHoursWorked))));

	        projectData.forEach((projectId, empStats) -> empStats.forEach((empId, stats) ->
	            output.add(new String[]{
	                projectId, empId,
	                String.format("%.2f", stats.getSum()),
	                String.format("%.2f", stats.getAverage())
	            })));

	        CSVExport.exportToCSV("project_productivity.csv", output);
	}

	public static void detectMonthlyDrops(List<EmployeeWorkLog> logs) {
	    List<String[]> output = new ArrayList<>();
	    output.add(new String[]{"Employee ID", "From Month", "To Month", "Drop %"});
	    
		Map<String, Map<Integer, Map<Integer, Double>>> monthlyData = logs.stream()
		        .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
		            Collectors.groupingBy(log -> log.getDate().getYear(),
		                Collectors.groupingBy(log -> log.getDate().getMonthValue(),
		                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))));

		    monthlyData.forEach((empId, yearMap) -> {
		        yearMap.forEach((year, monthMap) -> {
		            List<Integer> months = new ArrayList<>(monthMap.keySet());
		            Collections.sort(months);
		            for (int i = 1; i < months.size(); i++) {
		                double prev = monthMap.get(months.get(i - 1));
		                double curr = monthMap.get(months.get(i));
		                if (prev > 0 && (prev - curr) / prev > 0.4) {
		                    double drop = ((prev - curr) / prev) * 100;
		                    output.add(new String[]{
		                        empId,
		                        String.format("%d-%02d", year, months.get(i - 1)),
		                        String.format("%d-%02d", year, months.get(i)),
		                        String.format("%.2f%%", drop)
		                    });
		                }}
		        });
		    });

		    CSVExport.exportToCSV("monthly_drops.csv", output);
	}

	public static void departmentWiseTopPerformers(List<EmployeeWorkLog> logs) {
	    List<String[]> output = new ArrayList<>();
	    output.add(new String[]{"Department", "Employee ID", "Total Hours"});
	    
		Map<String, Map<String, Double>> deptToEmpHours = logs.stream()
		        .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
		            Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
		                Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

		    deptToEmpHours.forEach((dept, empMap) -> empMap.entrySet().stream()
		        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
		        .limit(2)
		        .forEach(entry -> output.add(new String[]{
		            dept, entry.getKey(), String.format("%.2f", entry.getValue())})));

		    CSVExport.exportToCSV("top_performers_by_department.csv", output);
	}

	public static void identifyFrequentProjectSwitchers(List<EmployeeWorkLog> logs) {
	    List<String[]> output = new ArrayList<>();
	    output.add(new String[]{"Employee ID", "Month", "Project Count"});
	    
		Map<String, Map<Month, Set<String>>> switches = logs.stream()
		        .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
		            Collectors.groupingBy(log -> log.getDate().getMonth(),
		                Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet()))));

		    switches.forEach((empId, monMap) -> {
		        monMap.forEach((ym, projectSet) -> {
		            if (projectSet.size() > 1) {
		                output.add(new String[]{
		                    empId, ym.toString(), String.valueOf(projectSet.size())
		                });
		            }
		        });
		    });

		    CSVExport.exportToCSV("frequent_switchers.csv", output);
	}

}
