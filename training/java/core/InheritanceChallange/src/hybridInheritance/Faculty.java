package hybridInheritance;

public class Faculty extends Department{
	String facultyName;
	String subject;

	Faculty(String collegeName, String deptName,String facultyName, String subject) {
		super(collegeName, deptName);
		this.facultyName=facultyName;
		this.subject=subject;
		// TODO Auto-generated constructor stub
	}
	public void facultyInfo()
	{
		System.out.println("Faculity Name:- "+facultyName +"\nSubject:- "+subject);
	}
}
