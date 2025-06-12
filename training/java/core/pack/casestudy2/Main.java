package casestudy2;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		ExcelReader reader = new ExcelReader();
		// System.out.println("Working directory: " + System.getProperty("user.dir"));
		List<EmployeeWorkLog> logs = reader
				.readFromExcel("training/java/core/pack/casestudy2/Sample_Employee_WorkLogs.xlsx");
		
		Analytics.performAnalytics(logs);

	}

}
