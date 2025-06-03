package hybridInheritance;

public class Student extends Department {
	String rollNo;
	String studentName;

	Student(String collegeName, String deptName,String rollNo, String studentName) {
		super(collegeName, deptName);
		this.rollNo=rollNo;
		this.studentName=studentName;
		// TODO Auto-generated constructor stub
	}
	public void studentInfo()
	{
		System.out.println("Student name:- "+studentName +"\nRoll No:- "+rollNo);
	}

}
