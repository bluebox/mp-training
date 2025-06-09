package csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvReader {

    public BufferedReader readCSV(String path) {
        if (path == null || path.trim().isEmpty()) {
            return null;
        }
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Employee> decipherCSV(BufferedReader br) {
        ArrayList<Employee> employees = new ArrayList<>();
        if (br == null) return employees;

        try {
            String line;
            String[] headers = null;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (lineCount == 0) {
                    headers = fields;
                } else {
                    HashMap<String, String> entry = new HashMap<>();
                    for (int i = 0; i < headers.length && i < fields.length; i++) {
                        entry.put(headers[i].trim(), fields[i].trim());
                    }

                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(entry.get("Date"), dtf);

                        double hoursWorked = 0.0;
                        try {
                            hoursWorked = Double.parseDouble(entry.get("Hours Worked"));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid Hours Worked value at line " + (lineCount + 1));
                        }

                        Employee emp = new Employee(
                            entry.get(headers[0]),
                            entry.get("Name"),
                            entry.get("Department"),
                            entry.get("Project ID"),
                            date,
                            entry.get("Task Category"),
                            hoursWorked,
                            entry.getOrDefault("Remarks", null)
                        );

                        employees.add(emp);

                    } catch (Exception e) {
                        System.err.println("Failed to parse line " + (lineCount + 1) + ": " + e.getMessage());
                    }
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static void main(String[] args) {
        System.out.println("Reading CSV...");
        CsvReader reader = new CsvReader();
        String csvPath = "/home/mphs/Desktop/mp-training/tasks/EmployeeProductivityAnalysis/CSVFileHandling/src/Sample_Employee_WorkLogs.xlsx";
        List<Employee> empDetails = reader.decipherCSV(reader.readCSV(csvPath));

        System.out.println("Total records: " + empDetails.size());
        for (Employee emp : empDetails) {
            System.out.println(emp);
        }
    }
}
