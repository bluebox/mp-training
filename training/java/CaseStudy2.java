
import java.time.temporal.IsoFields;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class CaseStudy2 {

    public static void main(String[] args) {
        String inputPath = "/home/mphs/Desktop/Sample_Employee_WorkLogs.xlsx";

        List<EmployeeWorkLog> logs = ExcelReader.readExcelData(inputPath);

        if (logs.isEmpty()) {
            System.out.println("No data found in the input file.");
            return;
        }

        // Task 4: Average Weekly Hours by Employee per Month
        Map<String, Map<String, Double>> avgWeeklyHoursByEmpMonth = logs.stream()
                .collect(Collectors.groupingBy(
                        EmployeeWorkLog::getEmployeeId,
                        Collectors.groupingBy(
                                log -> log.getDate().getYear() + "-" + String.format("%02d", log.getDate().getMonthValue()),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        monthlyLogs -> {
                                            double totalHours = monthlyLogs.stream()
                                                    .mapToDouble(EmployeeWorkLog::getHoursWorked)
                                                    .sum();

                                            long weekCount = monthlyLogs.stream()
                                                    .map(log -> log.getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR))
                                                    .distinct()
                                                    .count();

                                            return totalHours / (weekCount == 0 ? 1 : weekCount);
                                        }
                                )
                        )
                ));

        CSVExporter.exportAvgWeeklyHoursToCSV;
    }}







