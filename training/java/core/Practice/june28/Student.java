package Practice.june28;

public class Student {
	private int marks;
	private String sName;
	public Student(int marks,String sName) {
		this.marks=marks;
		this.sName=sName;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
}
