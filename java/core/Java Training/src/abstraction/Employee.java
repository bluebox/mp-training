package oops.abstraction;

public class Employee extends HrDept {
	private String empName;
	private String empDesg;
	private String empDOB;
	private String empJoineDate;
	private int empSalary;
	
	Employee(String companyName,String locationOfCompany,String hrName,String hrJoinedDate,String empName,String empDesg,String empDOB,String empJoinedDate,int empSalary){
		super(companyName,locationOfCompany,hrName,hrJoinedDate);
		this.empName=empName;
		this.empDesg=empDesg;
		this.empDOB=empDOB;
		this.empJoineDate=empJoinedDate;
		this.empSalary=empSalary;
	}
	
	public void showData() {
		System.out.println("	Employee Details ");
		System.out.println("------------------------------------------------");
		
		System.out.println("Employee Name : "+empName);
		System.out.println("Employee Designation : "+empDesg);
		System.out.println("Employee Date of Birth : "+empDOB);
		System.out.println("Company : "+ companyName);
		System.out.println("Location : "+locationOfCompany);
		System.out.println("Employee joined in : "+empJoineDate);
		System.out.println("HR Name : "+hrName);
		System.out.println("Employee salary : "+empSalary+" LPA");
		System.out.println("------------------------------------------------");
	}
	
	public static void main(String[] args) {
		Company hr=new HrDept("Medplus","Hyderabad","Mahesh","10-07-2020");
		Company emp=new Employee("Medplus","Hyderabad","Mahesh","10-07-2020","Jai","Software Developer","20-09-2001","12-03-2024",6);
		hr.showData();
		emp.showData();
	}
}
