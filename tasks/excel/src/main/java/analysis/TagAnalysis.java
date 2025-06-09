package analysis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import csv.CsvReader;
import csv.Employee;
import excel.App;

public class TagAnalysis {

    public void tagCount() {
        App reader = new App();
        List<Employee> employees = reader.readExcel("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx");
        Map<String, Set<String>> tagToEmployees = new HashMap<>();

        for (Employee emp : employees) {
            String remark = emp.remarks();
            Matcher matcher = Pattern.compile("\\w+").matcher(remark);
            while (matcher.find()) {
                String tag = matcher.group().toLowerCase();
                tagToEmployees.computeIfAbsent(tag, k -> new HashSet<>()).add(emp.employeeId());
            }
        }
        Map<String, Long> tagCount = tagToEmployees.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (long) entry.getValue().size()
                ));

        System.out.println("Tag Counts:");
        tagCount.forEach((tag, count) ->
                System.out.println("Tag: " + tag + "  Used by " + count + " employees")
        );

        List<String[]> csvRows = new ArrayList<>();
        csvRows.add(new String[]{"Tag", "Employee Count", "Employee IDs"});

        tagToEmployees.forEach((tag, empIds) -> {
            csvRows.add(new String[]{
                    tag,
                    String.valueOf(empIds.size()),
                    String.join(" | ", empIds)
            });
        });

        try (FileWriter writer = new FileWriter("/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/tag_analysis.csv")) {
            for (String[] row : csvRows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
            System.out.println("\nCSV written successfully to tag_analysis.csv");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
		new TagAnalysis().tagCount();
	}
}
