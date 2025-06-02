package mulitlevelusinginterfaces;

class Student implements College, Department {
	
	String rollNo;
	String collegeName;
	String deptName;
	String studentName;

	public Student(String collegeName, String deptName, String studentName,String RollNo) {
		this.collegeName = collegeName;
		this.rollNo=RollNo;
		this.deptName = deptName;
		this.studentName = studentName;
	}

	public void CollegeInfo() {
		System.out.println("College:- " + collegeName);
	}

	public void DepartmentInfo() {
		System.out.println("Department:- " + deptName);
	}
	public void StudentInfo() {
	     System.out.println("Roll No:- "+rollNo+"\nStudent:- " + studentName
	    		 );
	 }
}
