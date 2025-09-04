package EmpProductivity;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputExcelPath = "input_data.xlsx";

        List<EmployeeWorkLog> logs = ExcelReader.readWorkLogs(inputExcelPath);

        CSVWriterUtil.writeToCSV("output_avg_weekly_hours.csv", AnalyticsProcessor.averageWeeklyHours(logs));
//        CSVWriterUtil.writeToCSV("output_multi_category.csv", AnalyticsProcessor.multiCategoryEmployees(logs));
//        CSVWriterUtil.writeToCSV("output_overtime.csv", AnalyticsProcessor.overtimeLogs(logs));
//        CSVWriterUtil.writeToCSV("output_critical_projects.csv", AnalyticsProcessor.criticalProjects(logs));
//        CSVWriterUtil.writeToCSV("output_category_contribution.csv", AnalyticsProcessor.categoryContributionPerProject(logs));

        System.out.println("Employe data exported successfully.");
    }
}
