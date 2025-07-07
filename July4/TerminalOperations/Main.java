package July4.TerminalOperations;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Main {
	public static void main(String[] args) {

	Course jmc = new Course("Java Master Class", 100);
	Course pymc = new Course("Python Master Class", 50);
	Course additionalCourse = new Course("Creating Games in Java");
	
	List<Student> students = Stream.generate(() -> new Student(jmc, pymc, additionalCourse))
            					   .limit(5000)
            					   .collect(Collectors.toList());
	
    Double sumCompleted = students.stream()
            					  .mapToDouble(s -> s.getPercentComplete(jmc))
            					  .reduce(0.0,Double::sum);
    
    Double avgPercentageCompleted=sumCompleted/5000;
    double averagePercent = avgPercentageCompleted * 1.25;
    
    System.out.println("Average percent (scaled): " + averagePercent);

    Course newCourse = new Course("New Course", 30);

    //students.forEach(System.out::println);
    System.out.println("----------------------------------");
    
    List<Student> selectedStudents = students.stream()
            								 .sorted(Comparator.comparingInt(Student::getMinLectureAttended).reversed())
            								 .limit(10)
            								 .collect(Collectors.toList());

    selectedStudents.forEach(s -> s.addCourse(newCourse));
    selectedStudents.forEach(System.out::println);
	
	}
}
