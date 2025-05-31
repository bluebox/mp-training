package singleinheritance;

public class Department extends College {
	String deptName;
	String HOD;
	public Department(String name, String location, String principal, String deptName, String HOD) {
		super(name, location, principal);
		this.deptName = deptName;
		this.HOD = HOD;
	}
	public void departmentInfo()
	{
		System.out.println("Departmentname:- "+deptName+"\nHOD:- "+HOD);
		
	}
	
	

}
