package csv;
import java.time.LocalDate;

public record Employee(String employeeId, String name, String department, String projectId, LocalDate date,
			String taskCatagory, double hoursWorked, String remarks)
	{
		
		
		
	}