package learn;

public class Student {
	
	private String studentName;
	private int studentAge;
	private double percentage;
		
	public Student(String studentName, int studentAge, double percentage) {

		this.studentName = studentName;
		this.studentAge = studentAge;
		this.percentage = percentage;
	}

	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public int getStudentAge() {
		return studentAge;
	}
	
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	
}
