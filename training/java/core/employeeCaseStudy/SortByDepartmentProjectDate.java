package employeeCaseStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class SortByDepartmentProjectDate {
    public static void solve(List<EmployeeWorkLog> employees) {
        List<EmployeeWorkLog> sortedList = employees.stream()
            .sorted(Comparator
                .comparing(EmployeeWorkLog::getDepartment)
                .thenComparing(EmployeeWorkLog::getProjectId)
                .thenComparing(EmployeeWorkLog::getDate))
            .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter(new File("results/sorted_work_logs.csv"))) {
            writer.println("Department,ProjectId,Date,EmployeeId,TaskCategory,HoursWorked");
            sortedList.forEach(e -> writer.println(
                String.join(",",
                    e.getDepartment(),
                    e.getProjectId(),
                    e.getDate().toString(),
                    e.getEmployeeId(),
                    e.getTaskCategory(),
                    String.valueOf(e.getHoursWorked())
                )
            ));
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to sorted_work_logs.csv: " + e.getMessage());
        }
    }
}