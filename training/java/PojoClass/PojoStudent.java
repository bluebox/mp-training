package PojoClass;

public class PojoStudent {
	public int rollNumber;
	protected String name;
	private int marks;
	
	public PojoStudent() {
		this(1,"Shyam",250);
	}
	public PojoStudent(String name) {
		this(100,name,0);
	}
	
	public PojoStudent(int rollNumber,String name, int marks) {
		this.name=name;
		this.rollNumber=rollNumber;
		this.marks=marks;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setMarks(int marks) {
		this.marks=marks;
	}
	
	public String getName() {
		return this.name;
	}
	public int getMarks() {
		return this.marks;
	}
	
	public String displayStudentDetails() {
		return this.name+" with roll number "+this.rollNumber+" scored "+this.marks;
	}

}
