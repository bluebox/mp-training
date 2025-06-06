package samplecodes;
import java.util.*;
import java.io.*;
public class FileWritingChallenge {
	public static void main(String[] args) { 
		System.out.println("Hello ");
		List<Student> students=new ArrayList<>();
		Student s1=new Student("Sameer",23);
		Student s2=new Student("Jack",24);
		Student s3=new Student("John",22);
		Student s4=new Student("Sai kiran",20);
		Student s5=new Student("Karun",23);
		Student s6=new Student("Durgesh",29);
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		students.add(s5);
		students.add(s6);
		try {
			FileWriter fw=new FileWriter("studentsdata.txt");
			fw.write("{");
			for(Student s : students) {
				String jsonFormat=s.convertToJsonFormat();
				System.out.println(jsonFormat);
				fw.write(jsonFormat);
			}
			fw.write("}");
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
class Student{
	private String name;
	private int age;
	Student(String name,int age){
		this.name=name;
		this.age=age;
	}
	public String convertToJsonFormat() {
		return "{\"name\": \"" + this.name + "\", \"age\": " + this.age + "}";
			
	}
}
