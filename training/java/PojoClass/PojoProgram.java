package PojoClass;

public class PojoProgram {
	public static void main(String[] args) {
		PojoStudent student1=new PojoStudent();
		System.out.println(student1.getName());
		System.out.println(student1.displayStudentDetails());
		
		JavaBeanStudent student2=new JavaBeanStudent();
		System.out.println(student2.getMarks());
		System.out.println(student2.displayStudentDetails());
		
	}
}
