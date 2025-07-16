package com.employee.service;

import com.employee.model.EmployeeWorkLog;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class VarientServiceImpl {

	//variant 2: Sort by task category and hours descending; top 3 most time-consuming tasks per department.
	public static Map<String, List<EmployeeWorkLog>> timeConsuming_PerDepartment(List<EmployeeWorkLog> logs) {
		
	    Map<String, List<EmployeeWorkLog>> groupedByDept = logs.stream()
	            .filter(log -> log.getDepartment() != null)
	            .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment));

	    Map<String, List<EmployeeWorkLog>> result = new HashMap<>();
	    for (Map.Entry<String, List<EmployeeWorkLog>> entry : groupedByDept.entrySet()) {
	        List<EmployeeWorkLog> sortedTop3 = entry.getValue().stream()
	                .sorted(Comparator.comparing(EmployeeWorkLog::getTaskCategory, Comparator.nullsLast(String::compareTo))
	                        .thenComparing(Comparator.comparingDouble(EmployeeWorkLog::getHoursWorked).reversed()))
	                .limit(3)
	                .collect(Collectors.toList());

	        result.put(entry.getKey(), sortedTop3);
	    }

	    return result;
	}
	



    // Variant 9: Top 5 employees with highest hours in last 60 days
    public static List<Map.Entry<String, Double>> top5EmployeesLast60Days(List<EmployeeWorkLog> logs) {
        LocalDate sixtyDaysAgo = LocalDate.now().minusDays(60);

        return logs.stream()
                .filter(log -> log.getDate() != null && !log.getDate().isBefore(sixtyDaysAgo))
                .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // Variant 22: Combine 2 sheets & compute hour differences
    public static Map<String, Double> computeHourDifferences(List<EmployeeWorkLog> sheet1, List<EmployeeWorkLog> sheet2) {
        List<EmployeeWorkLog> combined = new ArrayList<>();
        combined.addAll(sheet1);
        combined.addAll(sheet2);

        return combined.stream()
                .collect(Collectors.groupingBy(log -> log.getEmployeeId(),
                        Collectors.mapping(EmployeeWorkLog::getHoursWorked, Collectors.toList())
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().size() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Math.abs(e.getValue().get(0) - e.getValue().get(1))
                ));
    }

    // Variant 27: Time period-based grouping (morning, afternoon, evening)
    public static Map<String, List<EmployeeWorkLog>> groupByTimePeriod(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(log -> EmployeeWorkLog.getTimePeriod(log.getHoursWorked())));
    }

    // Variant 30: Analyze total "meeting" time from remarks
    public static List<EmployeeWorkLog> suggestMeetingsBasedOnRemarks(List<EmployeeWorkLog> logs) {
        return logs.stream()
                .filter(log -> log.getRemarks() != null)
                .filter(log -> {
                    String remark = log.getRemarks().toLowerCase();
                    return remark.contains("blocked") ||
                           remark.contains("pending review") ||
                           remark.contains("on hold") ||
                           remark.contains("in progress");
                })
                .collect(Collectors.toList());
    }
    
    //Displaying Options
    public static int displayOptions(Scanner scanner,int option) {
    	System.out.println("\nQUIT : Exist From System ---> Enter 0");
    	System.out.println("\nVARIENT_1 : Write Employee Data To CSV ---> Enter 1");
    	System.out.println("\nVARIENT_2 : Get Top-3 Time Consuming Tasks For Each Department ---> Enter 2");
    	System.out.println("\nVARIENT_3 : Get Top-5 Employees With High Working Hours For Last 60 Days --->Enter 3");
    	System.out.println("\nVARIENT_4 : Get Working Hours Difference Between Two Excel Sheets(Employee Data) ---> Enter 4");
    	System.out.println("\nVARIENT_5 : Get Time Period Based Grouping('MORNING','AFTERNOON','EVENING') ---> Enter 5");
    	System.out.println("\nVARIENT_6 : Get Scheduled Meeting Time From Remarks ---> Enter 6");
		System.out.println("\n\nEnter an option: ");
		option=scanner.nextInt();
		return option;
    }
}
