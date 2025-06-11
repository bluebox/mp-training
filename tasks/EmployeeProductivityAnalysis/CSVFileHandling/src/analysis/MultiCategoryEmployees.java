package analysis;

import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import csv.CsvReader;
import csv.Employee;
import excel.ExcelReader;

public class MultiCategoryEmployees {
	
	public void employees()
	{
		CsvReader reader = new CsvReader();
		List<Employee> employees = reader.decipherCSV(reader.readCSV("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.csv"));
		employees = ExcelReader.readExcel();
		int i = 0;
		// employees.forEach((emp->{
		// 	System.out.println(emp.date());
		// }));
		Map<String, Map<Integer, Set<String>>> grouped = employees.stream().filter(emp->emp.date()!= null).collect(
			    Collectors.groupingBy(
			        Employee::employeeId,
			        Collectors.groupingBy(
			            emp -> emp.date().get(WeekFields.ISO.weekOfWeekBasedYear()), 
			            Collectors.mapping(Employee::taskCatagory, Collectors.toSet())
			        )
			    )
			);
		
		List<String> result = grouped.entrySet().stream()
			    .filter(entry -> entry.getValue().values().stream()
			        .anyMatch(taskSet -> taskSet.size() >= 3))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList());
		
		System.out.println("Employees with 3+ task categories in any week:");
	    result.forEach(System.out::println);
	    
	    
	    List<String[]> csvRows = new ArrayList<>();
        csvRows.add(new String[]{"Employee ID", "Week Number", "Category Count", "Categories"});

        for (String empId : result) {
            Map<Integer, Set<String>> weeklyTasks = grouped.get(empId);
            if (weeklyTasks != null) {
                for (Map.Entry<Integer, Set<String>> weekEntry : weeklyTasks.entrySet()) {
                    int week = weekEntry.getKey();
                    Set<String> categories = weekEntry.getValue();
                    if (categories.size() >= 3) {
                        csvRows.add(new String[]{
                            empId,
                            String.valueOf(week),
                            String.valueOf(categories.size()),
                            String.join(" | ", categories)
                        });
                    }
                }
            }
        }
        
        try (FileWriter writer = new FileWriter("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/multi_category_employees.csv")) {
            for (String[] row : csvRows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
            System.out.println("\nCSV file written: multi_category_employees.csv");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
        

	}
	public static void main(String[] args) {
		new MultiCategoryEmployees().employees();
	}

}
