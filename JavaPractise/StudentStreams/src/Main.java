import java.util.List;
import java.util.stream.Collectors;

class Course{
	String name;
	double percent;
	int year;
	public Course(String name, double percent, int year) {
		this.name = name;
		this.percent = percent;
		this.year = year;
	}
	
}
class Student{
	String name;
	List<Course> courses;
	public Student(String name, List<Course> courses) {
		super();
		this.name = name;
		this.courses = courses;
	}
}
public class Main {
	public static void main(String[] args) {
		List<Student> student=List.of(
				new Student("Akash",List.of(
						new Course("Java",80,2023),
						new Course("Python",90,2024))),
				new Student("Ram",List.of(
						new Course("Java",80,2023))),
				new Student("Raju",List.of(
						new Course("Python",60,2023),
						new Course("C++",75,2023))),
				new Student("Akash",List.of(
						new Course("C++",85,2024)))
				
				);
		System.out.println("The no of students per course :");
		student.stream().flatMap(s->s.courses.stream().map(c->c.name))
			.collect(Collectors.groupingBy(name-> name,Collectors.counting()))  
			.forEach((name,count)->System.out.println(name+":"+count));
	}
}
