package hybridInheritance;

public class Main {
	public static void main(String[] args) {
		Student student=new Student("Snist","It","21311A1206","Manoj");
		student.studentInfo();
		student.departmentInfo();
		student.collegeInfo();
		
		System.out.println("-".repeat(30));
		Faculty faculty=new Faculty("Snist","It","Vijaya Laxmi","Machine Learning");
		faculty.facultyInfo();
		faculty.departmentInfo();
		faculty.collegeInfo();
		
	}

}
