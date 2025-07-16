package Practice.july7_streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
class Student{
	private String name;
	private int roll;
	public Student(String name,int roll) {
		this.name=name;
		this.roll=roll;
	}
	
	public String getName() {
		return name;
	}
	public int getRoll() {
		return roll;
	}
	@Override
	public String toString() {
		return "["+this.getName()+", "+this.getRoll()+"]";
	}
}
public class SortingObjects {
	public static void main(String[] args) {
		Student s1=new Student("Deepika",23);
		Student s2=new Student("Meghana",21);
		Student s3=new Student("PardhaSaradhi",51);
		Student s4=new Student("Sailaja",41);
		Student s5=new Student("Deepu",22);
		List<Student> students=Arrays.asList(s1,s2,s3,s4,s5);
		List<Student> sortedStudents=students.stream().sorted(Comparator.comparingInt(Student::getRoll)).collect(Collectors.toList());
		System.out.println(sortedStudents);
	}
}
