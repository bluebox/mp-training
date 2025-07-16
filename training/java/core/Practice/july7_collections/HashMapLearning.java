package Practice.july7_collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapLearning {

	public static void main(String[] args) {
		Student s1=new Student("Deepika",23);
		Student s2=new Student("Meghana",21);
		Student s3=new Student("PardhaSaradhi",51);
		Student s4=new Student("Sailaja",41);
		Student s5=new Student("Deepu",22);
		Student s6=new Student("NageshwarRao",51);
		Student s7=new Student("Sruthi",22);
		Course c1=new Course("Java",1000.0);
		Course c2=new Course("Python",700.0);
		Course c3=new Course("SQL",500.0);
		Map<Course,List<Student>> institute=new HashMap<>();
		institute.put(c1, Arrays.asList(s1,s2,s3));
		institute.put(c2, Arrays.asList(s4,s5));
		institute.put(c3, Arrays.asList(s6,s7));
		
		System.out.println("For getting the name of the course c1 where student1 is registered");
		for(List<Student> c:institute.values()) {
			for(Student s:c) {
				System.out.println("["+s.getName()+", "+s.getAge()+"]");
			}
			System.out.println();
		}
	}

}
class Student{
	private String name;
	private Integer age;
	
	public Student(String name,Integer age) {
		this.name=name;
		this.age=age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
class Course{
	private String courseName;
	private Double price;
	
	public Course(String courseName,Double price) {
		this.courseName=courseName;
		this.price=price;
	}

	public String getCourseName() {
		return courseName;
	}

	public Double getPrice() {
		return price;
	}
}