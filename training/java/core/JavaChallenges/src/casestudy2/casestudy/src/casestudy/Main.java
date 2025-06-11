// Main.java
package casestudy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputPath = "D:/casestudy/Sample_Employee_WorkLogs.xlsx";
        String outputBase = "D:/casestudy/";

        List<Employee> employees = EmployeeReader.readExcel(inputPath);
        System.out.println("Total records loaded: " + employees.size());

        try {
            Operation3.execute(employees, outputBase + "Operation3_Output.xlsx");
            Operation10.execute(employees, outputBase + "Operation10_Output.xlsx");
            Operation18.execute(employees, outputBase + "Operation18_Output.xlsx");
            Operation19.execute(employees, outputBase + "Operation19_Output.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("All operations completed successfully.");
    }
}


