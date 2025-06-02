package multiLevelInheritance;

public class Department extends College {
	String deptName;
	public Department(String name, String deptName) {
		super(name);
		this.deptName = deptName;
	}
	public void departmentInfo()
	{
		System.out.println("Departmentname:- "+deptName);
		
	}

}
