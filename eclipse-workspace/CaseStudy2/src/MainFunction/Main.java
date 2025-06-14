package MainFunction;

import java.util.List;

import BaseFiles.CSVReader;
import BaseFiles.CSVWriter;
import BaseFiles.EmployeeWorkLog;
import Functionalities.AnalyzeMeetings;
import Functionalities.ComputeAverageWeeklyHours;
import Functionalities.ProjectWiseProductivity;
import Functionalities.TimeSpent;
import Functionalities.WeeklyEffortPerProject;

public class Main {
    public static void main(String[] args) {
        String inputPath = "D:\\KMIT\\3RD YEAR 1ST SEM\\SE\\Eclipse_Workspace-1\\CaseStudy2\\Sample_Employee_WorkLogs.csv";
        List<EmployeeWorkLog> logs = CSVReader.readCSV(inputPath);

        List<String[]> averageWeeklyHours = ComputeAverageWeeklyHours.calculate(logs);
        CSVWriter.writeCSV("avg_weekly_hours.csv",averageWeeklyHours);

        List<String[]> weeklyEffort = WeeklyEffortPerProject.calculate(logs);
        CSVWriter.writeCSV("top5_60days.csv",weeklyEffort);

        List<String[]> projectWiseProductivity = ProjectWiseProductivity.calculate(logs);
        CSVWriter.writeCSV("proj_emp_hours.csv",projectWiseProductivity);

        List<String[]> timeSpent = TimeSpent.calculate(logs);
        CSVWriter.writeCSV("time_percent_category.csv",timeSpent);

        List<String[]> analyzeMeetings = AnalyzeMeetings.calculate(logs);
        CSVWriter.writeCSV("meeting_hours.csv",analyzeMeetings);
    }
}
