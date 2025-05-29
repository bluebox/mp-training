package PojoClass;
import java.io.Serializable;

public class JavaBeanStudent implements Serializable {
	private int rollNumber;
	private String name;
	private int marks;
	
	public JavaBeanStudent() {
		this(6,"Ram",200);
	}
	
	public JavaBeanStudent(String name) {
		this(100,name,0);
	}
	
	public JavaBeanStudent(int rollNumber,String name, int marks) {
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
