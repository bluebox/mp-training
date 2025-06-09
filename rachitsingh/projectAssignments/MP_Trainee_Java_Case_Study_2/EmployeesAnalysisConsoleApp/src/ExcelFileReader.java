package EmployeesAnalysisConsoleApp.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
public class ExcelFileReader
{
    public static List<EmployeesWorkLogPOJO> readingEmployeesWorkLogs(String filePathName)
    {
        List<EmployeesWorkLogPOJO> employeesLogs = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePathName)))
        {
            String record;
            boolean skipHeaderRow = false;

            while ((record = reader.readLine()) != null) {
                if (!skipHeaderRow) {
                    skipHeaderRow = true;
                    continue;
                }
                String[] attributes = record.split(",");

                if (attributes.length != 8) {
                    continue;
                }
                String employeeID = attributes[0].trim();
                String employeeName = attributes[1].trim();
                String department = attributes[2].trim();
                String project = attributes[3].trim();
                LocalDate date = LocalDate.parse(attributes[4].trim());
                String taskCategory = attributes[5].trim();
                double hoursWorked = Double.parseDouble(attributes[6].trim());
                String remark = attributes[7].trim();

                employeesLogs.add(new EmployeesWorkLogPOJO(employeeID, employeeName, department, project, date,
                        taskCategory, hoursWorked, remark));
            }
        }
        catch(IOException IOE)
        {
            System.err.println("Error occured while reading from the source data file: " + IOE.getMessage());
            IOE.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println("Unexpected Error occured: " + e.getMessage());
            e.printStackTrace();
        }
        return employeesLogs;
    }
}
