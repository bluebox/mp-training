package analysis;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Example {
    public static void main(String[] args) {
        String filePath = "/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/employees.xlsx";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write header
            writer.println("ID,Name,Department,Hours");

            // Write data
            writer.println("101,Alice,Engineering,8.5");
            writer.println("102,Bob,Marketing,7.0");
            writer.println("103,Charlie,Finance,9.0");

            System.out.println("CSV file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
