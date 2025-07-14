package EmployeeProductivity;

//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<EmployeeWorkLog> readExcel(String filePath) {
        List<EmployeeWorkLog> workLogs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {

            Sheet sheet = workbook.getSheetAt(0);

            boolean isFirstRow = true;
            for (Row row : sheet) {
                if (isFirstRow) {  
                    isFirstRow = false;
                    continue;
                }
                
                String empId = row.getCell(0).toString();
                String name = row.getCell(1).toString();
                String dept = row.getCell(2).toString();
                String projectId = row.getCell(3).toString();
                String dateStr = row.getCell(4).toString();
                LocalDate date = LocalDate.parse(dateStr, formatter);
                String task = row.getCell(5).toString();
                double hours = row.getCell(6).getNumericCellValue();
                String remarks = row.getCell(7).toString();
                EmployeeWorkLog log = new EmployeeWorkLog(
                        empId, name, dept, projectId,
                        date, task, hours, remarks
                );
                workLogs.add(log);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return workLogs;
    }

    public static void main(String[] args) {
    		System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");

        String filePath = "C:\\Users\\Sindhuja\\Downloads\\Employee_Productivity_Case_Study (1).xlsx"; 
        List<EmployeeWorkLog> logs = readExcel(filePath);
        Pattern pattern = Pattern.compile("\\b(urgent|critical)\\b");

        System.out.println(logs.stream().limit(100).count());
        //5
        logs.stream().filter(employee->pattern.matcher(employee.getRemarks().toLowerCase()).find())
        .sorted(Comparator.comparing(EmployeeWorkLog::getName))
        .forEach(System.out::println);
        //8
        WeekFields weekFields = WeekFields.ISO;
        Map<String, Map<Integer, Double>> result = logs.stream()
                .sorted(Comparator.comparing(EmployeeWorkLog::getDate)) 
                .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                    Collectors.groupingBy(
                        log -> log.getDate().get(weekFields.weekOfWeekBasedYear()),
                        Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                    )
                ));

            result.forEach((project, weekMap) -> {
                System.out.println("Project: " + project);
                weekMap.forEach((week, hours) ->
                    System.out.println("\tWeek " + week + ": " + hours + " hrs"));
            });
            //17
            Map<String, Map<YearMonth, Set<String>>> switches = logs.stream()
                    .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                        Collectors.groupingBy(
                            log -> YearMonth.from(log.getDate()),
                            Collectors.mapping(EmployeeWorkLog::getDepartment, Collectors.toSet())
                        )
                    ));

                switches.forEach((empId, monthMap) -> {
                    monthMap.forEach((yearMonth, departments) -> {
                        if (departments.size() > 1) {
                            System.out.println("Employee: " + empId + 
                                " switched departments in " + yearMonth + 
                                " → " + departments);
                        }
                    });
                });
                
               //26 
                List<EmployeeWorkLog> weekendLogs = logs.stream()
                        .filter(log -> {
                            DayOfWeek day = log.getDate().getDayOfWeek();
                            return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
                        })
                        .collect(Collectors.toList());
                    Map<String, Double> weekendHoursByEmployee = weekendLogs.stream()
                        .collect(Collectors.groupingBy(
                            EmployeeWorkLog::getEmployeeId,
                            Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
                        ));
                    weekendHoursByEmployee.forEach((empId, totalHours) ->
                        System.out.println("Employee: " + empId + " | Weekend Hours: " + totalHours));
                    //29
                    Map<String, DoubleSummaryStatistics> statsByEmployee = logs.stream()
                            .collect(Collectors.groupingBy(
                                EmployeeWorkLog::getEmployeeId,
                                Collectors.summarizingDouble(EmployeeWorkLog::getHoursWorked)
                            ));
                        statsByEmployee.forEach((empId, stats) -> {
                            System.out.println("Employee: " + empId);
                            System.out.println("   Min:  " + stats.getMin());
                            System.out.println("   Max:  " + stats.getMax());
                            System.out.println("   Avg:  " + String.format("%.2f", stats.getAverage()));
                        });

    }
}
