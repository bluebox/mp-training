package controller;

import model.EmployeeWorkLog;
import dao.ExcelRepository;
import service.AnalyticsService;
import util.CsvWriterUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalyticsController {
    private final ExcelRepository repo = new ExcelRepository();
    private final AnalyticsService service = new AnalyticsService();
    private final CsvWriterUtil csvWriter = new CsvWriterUtil();

    public void executeAnalytics() {
        List<EmployeeWorkLog> logs1 = repo.readFromExcel("EmployeeLogs1.xlsx");
        List<EmployeeWorkLog> logs2 = repo.readFromExcel("EmployeeLogs2.xlsx");

        exportUrgentLogs(logs1);
        exportDailyAvgHours(logs1);
        exportConsistency(logs1);
        exportHourDifferences(logs1, logs2);
        exportTimeGrouping(logs1);
    }

    private void exportUrgentLogs(List<EmployeeWorkLog> logs) {
        List<EmployeeWorkLog> filtered = service.filterUrgentOrCritical(logs);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"EmpID", "Name", "Remarks"});
        filtered.forEach(l -> data.add(new String[]{l.getEmployeeId(), l.getName(), l.getRemarks()}));
        csvWriter.writeToCSV("urgent_logs.csv", data);
    }

    private void exportDailyAvgHours(List<EmployeeWorkLog> logs) {
        Map<String, Double> avg = service.dailyAvgLast30Days(logs);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"EmpID", "AvgHours"});
        avg.forEach((k, v) -> data.add(new String[]{k, String.valueOf(v)}));
        csvWriter.writeToCSV("avg_hours.csv", data);
    }

    private void exportConsistency(List<EmployeeWorkLog> logs) {
        Map<String, Double> variance = service.consistencyByCategory(logs);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Category", "Variance"});
        variance.forEach((k, v) -> data.add(new String[]{k, String.valueOf(v)}));
        csvWriter.writeToCSV("category_variance.csv", data);
    }

    private void exportHourDifferences(List<EmployeeWorkLog> logs1, List<EmployeeWorkLog> logs2) {
        Map<String, Double> diff = service.differenceBetweenTwoSheets(logs1, logs2);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"EmpID", "HourDifference"});
        diff.forEach((k, v) -> data.add(new String[]{k, String.valueOf(v)}));
        csvWriter.writeToCSV("hour_difference.csv", data);
    }

    private void exportTimeGrouping(List<EmployeeWorkLog> logs) {
        Map<String, String> times = service.timeOfDayClassification(logs);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"EmpID_Date", "TimePeriod"});
        times.forEach((k, v) -> data.add(new String[]{k, v}));
        csvWriter.writeToCSV("time_period.csv", data);
    }
}
