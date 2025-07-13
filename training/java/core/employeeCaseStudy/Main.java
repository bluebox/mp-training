// Main.java
package employeeCaseStudy;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File resultsDir = new File("results");
        if (!resultsDir.exists()) {
            resultsDir.mkdirs();
        }

        ExcelReader reader = new ExcelReader("input_data.xlsx");
        List<EmployeeWorkLog> employees = reader.getEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employee data found or error reading the excel file.");
            return;
        }

        EngineeringEmployeesOnMultipleProjects.solve(employees);
        Top5EmployeesByHoursLast60Days.solve(employees);
        TimePercentagePerCategory.solve(employees);
        CategoryConsistencyAnalysis.solve(employees);
        SortByDepartmentProjectDate.solve(employees);

        System.out.println("Analysis complete. Result CSV files are located in the '/results' folder.");
    }
}