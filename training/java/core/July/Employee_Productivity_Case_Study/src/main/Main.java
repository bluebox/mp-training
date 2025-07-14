package main;

import analysis.BugFixByCategoryDay;
import analysis.StdDevPerProject;
import analysis.UrgentCriticalLogs;
import analysis.WeekendSummary;
import analysis.WeeklyEffortPerProject;

public class Main {
	public static void main(String[] args) {
		
		 String filePath = ".\\data\\Employee_Productivity_DateFormatted.xlsx";
 
		 //8. Weekly effort per project from sorted date logs.
		 WeeklyEffortPerProject.weeklyEffortPerProject(filePath,"output/weekly_effort_per_project.csv");
		 
		 //26. Weekend logs; summarize weekend hours.
	     WeekendSummary.weekendSummary(filePath,"output/weekend_summary.csv");
	     
	     //5. Logs with "urgent" or "critical" in remarks; sort by employee name.
	     UrgentCriticalLogs.urgentCriticalLogs(filePath,"output/urgent_critical_logs.csv");

	     //14. Standard deviation of employee hours per project.
	     StdDevPerProject.stdDevPerProject(filePath,"output/stddev_hours_per_project.csv");

	     //10. "Bug Fix" tasks grouped by category and day of week.
	     BugFixByCategoryDay.bugFixByCategoryDay(filePath,"output/bugfix_by_category_day.csv");
		 
	}
}
