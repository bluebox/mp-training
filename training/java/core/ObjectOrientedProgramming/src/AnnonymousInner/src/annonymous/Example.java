package annonymous;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Example {
	
	private List<Employee>employees;
	public Example()
	{
		employees = new ArrayList<Employee>();
	}
	public List<Employee> getEmployees()
	{
		return employees;
	}
	abstract class EmployeeObjects
	{
		private String firstName;
		private String lastName;
		private LocalDate date;
		
		
		
		public String getFirstName() {
			return firstName;
		}



		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}



		public String getLastName() {
			return lastName;
		}



		public void setLastName(String lastName) {
			this.lastName = lastName;
		}



		public LocalDate getDate() {
			return date;
		}



		public void setDate() {
			this.date = LocalDate.now();
		}



		public EmployeeObjects(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.date = LocalDate.now();
		}



		public abstract boolean addEmployee();
//		{
//			Employee emp = new Employee(this.firstName, this.lastName, this.date);
//			employees.add(emp);
//			return true;
//		}
		
		
		
	}
	
	
	public void displayRecords()
	{
		for(var emp:employees) {
			System.out.println(emp);
		}
	}
	
	
	
}
