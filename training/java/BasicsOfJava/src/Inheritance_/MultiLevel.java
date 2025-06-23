package Inheritance_;

public class MultiLevel {
	
	public static void main(String[] args) {
		
		RegionalManager manager = new RegionalManager("DSP",100011,"Technical",15, "Andhra" , 2);
		manager.showEmployeeDetails();
		manager.showManagerDetails();
		manager.showRegionalManagerDetails();
	}
}

class Employee {
	
	protected String empName;
	protected int empId;
	protected String department;
	
	public Employee(String empName, int empId, String department) {
		this.empName = empName;
		this.empId = empId;
		this.department = department;
	}
	
	public void showEmployeeDetails() {
		System.out.println("\n____________Employee ___________");
		System.out.println("Name: " + empName);
		System.out.println("ID: " + empId);
		System.out.println("Department: " + department);
	}
}
class Manager extends Employee {
	protected int teamSize;
	public Manager(String empName, int empId, String department, int teamSize) {
		super(empName, empId, department);
		this.teamSize = teamSize;
	}
	public void showManagerDetails() {
		
		showEmployeeDetails();
		System.out.println("Team Size: " + teamSize);
	}
}
class RegionalManager extends Manager {
	private String region;
	private int branchesHandled;
	
	public RegionalManager(String empName, int empId, String department,
		int teamSize, String region, int branchesHandled) {
		super(empName, empId, department, teamSize);
		this.region = region;
		this.branchesHandled = branchesHandled;
	}
	public void showRegionalManagerDetails() {
		showManagerDetails();
		System.out.println("Region: " + region);
		System.out.println("Branches Managed: " + branchesHandled);
	}
}