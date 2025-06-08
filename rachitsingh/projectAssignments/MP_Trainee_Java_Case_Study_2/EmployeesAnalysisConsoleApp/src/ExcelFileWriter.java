import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelFileWriter 
{
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void writeTopThreeTasks(Map<String, List<EmployeesWorkLogPOJO>> data, String filePathName)
    {
        try (FileWriter fileWriter = new FileWriter(filePathName)) {
            fileWriter.write("Department,Employee ID,Name,Task Category,Hours Worked,Date\n");
            for (Map.Entry<String, List<EmployeesWorkLogPOJO>> mapEntry : data.entrySet()) {
                for (EmployeesWorkLogPOJO employeeLog : mapEntry.getValue()) {
                    StringBuilder record = new StringBuilder();
                    record.append(mapEntry.getKey()).append(",")
                            .append(employeeLog.getEmployeeID()).append(",")
                            .append(employeeLog.getemployeeName()).append(",")
                            .append(employeeLog.getTaskCategory()).append(",")
                            .append(employeeLog.getHoursWorked()).append(",")
                            .append(employeeLog.getDate().format(DATE_FORMAT)).append("\n");
                    fileWriter.write(record.toString());
                }
            }
        } catch (IOException IOE) {
            System.err.println("Error occured while writing top 3 tasks: " + IOE.getMessage());
        }
    }
    
    public static void writeWeekendSummary(double totalHours, String filePathName)
    {
        try (FileWriter fileWriter = new FileWriter(filePathName)) 
        {
            fileWriter.write("Total hours worked in the weeked:\n");
            fileWriter.write(String.valueOf(totalHours) + "\n");
        } 
        catch (IOException IOE) 
        {
            System.err.println("Error occured while writing the summary of weekend: " + IOE.getMessage());
        }
    }
    
    public static void writeDailyAverage(Map<String, Double> data, String filePathName)
    {
        try (FileWriter fileWriter = new FileWriter(filePathName)) 
        {
            fileWriter.write("Employee ID, Average Hours Worked (Last 30 days)\n");
            for (Map.Entry<String, Double> mapEntry : data.entrySet()) {
                fileWriter.write(mapEntry.getKey() + "," + mapEntry.getValue() + "\n");
            }
        } 
        catch (IOException IOE) 
        {
            System.err.println("Error occured while writing daily average working hours: " + IOE.getMessage());
        }
    }
    
    public static void writeLessthanTwoHourWorkDaysData(Map<String, List<java.time.LocalDate>> data, String filePathName)
    {
        try(FileWriter fileWriter = new FileWriter(filePathName))
        {
            fileWriter.write("Employee ID, Dates with <2 working hours\n");

            for (Map.Entry<String, List<java.time.LocalDate>> mapEntry : data.entrySet()) {
                String allCombinedDates = mapEntry.getValue().stream()
                        .map(date -> date.format(DATE_FORMAT))
                        .collect(Collectors.joining("; "));

                fileWriter.write(mapEntry.getKey() + "," + allCombinedDates + "\n");
            }
        }
        catch (IOException IOE) 
        {
            System.err.println("Error occured while writing <2 hour days: " + IOE.getMessage());
        }
    }
}
