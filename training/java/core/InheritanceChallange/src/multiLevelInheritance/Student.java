package multiLevelInheritance;

public class Student extends Department{
	String studentName;
	String rollNo;
	
	public Student(String collegename, String deptName,String studentName,String rollNo) {
		super(collegename, deptName);
		this.rollNo=rollNo;
		this.studentName=studentName;
		
	}
	public void studentInfo()
	{
		System.out.println("RollNo:- "+rollNo+"\nStudentName:- "+studentName);
	}
	


}
