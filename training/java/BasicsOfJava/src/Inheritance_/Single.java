package Inheritance_;

public class Single {
	
	Franchise company = new Franchise("TechWorld Inc.", "Alice Johnson", "New York", 1995,
			"Bangalore", 120, "John Doe");
}

class Company {
	protected String name;
	protected String ceo;
	protected String headquarters;
	protected int establishedYear;
	
	public Company(String name, String ceo, String headquarters, int year) {
		
		this.name = name;
		this.ceo = ceo;
		this.headquarters = headquarters;
		this.establishedYear = year;
	}
	public void displayCompanyInfo() {
		System.out.println("\n--- Company Details ---");
		System.out.println("Company Name: " + name);
		System.out.println("CEO: " + ceo);
		System.out.println("Headquarters: " + headquarters);
		System.out.println("Established: " + establishedYear);
	}
	public void annualMeeting() {
		
		System.out.println(name + " holds an annual global meeting every December.");
	}
}
class Franchise extends Company {
	
	private String location;
	private int employeeCount;
	private String manager;
	public Franchise(String name, String ceo, String headquarters, int year,
			String location, int employeeCount, String manager) {
			
		super(name, ceo, headquarters, year);
		this.location = location;
		this.employeeCount = employeeCount;
		this.manager = manager;
}
	public void displayFranchiseInfo() {
		displayCompanyInfo();
		System.out.println("\n--- Franchise Details ---");
		System.out.println("Location: " + location);
		System.out.println("Manager: " + manager);
		System.out.println("Employees: " + employeeCount);
	
	}
	
}