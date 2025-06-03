package hybridInheritance;

public class Department extends College {
	String deptName;
	Department(String collegeName,String deptName)
	{
		super(collegeName);
		this.deptName=deptName;
	}
	public void departmentInfo()
	{
		System.out.println("Department :- "+deptName);
			
	}

}
