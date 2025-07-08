package StudentStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Student> students = Stream
				.generate(Student::getRandomStudent)
				.limit(20)
				.collect(Collectors.toList());
		

		int genderCountMale = (int)students.stream()
				.filter(student -> student.getSgender() == Gender.MALE).count();
		
		System.out.println("No.of Males: " + genderCountMale);
		
		int genderCountFemale = (int)students.stream()
				.filter(student -> student.getSgender() == Gender.FEMALE).count();
		
		System.out.println("No.of Females: " + genderCountFemale);
		
		int ageupto25 = (int) 
				students.stream().filter(student -> student.getSage() <= 25).count();
		
		System.out.println("No.of Students upto age 25:  " + ageupto25);


		int ageAbove25 = (int) 
				students.stream().filter(student -> student.getSage() > 25).count();
		
		System.out.println("No.of Students above age 25:  " + ageAbove25);
		
		int maxage = students.stream()
		                .mapToInt(Student::getSage).max().orElse(0);               
		
		System.out.println("The Maximum age is :  " + maxage);
		
		
		for(Courses course: Courses.values()) {
			int StudentsInCourse = (int) students.stream()
					.filter(student->student.getSCourse().contains(course)).count();
			System.out.println("Students in "+ course+" course :  " + StudentsInCourse);
		}
		
	
		List<String> StudentsWithMultipleCourses = students.stream()
				.filter(student -> student.getSCourse().size()==1).map(Student::getSid)
				.collect(Collectors.toList());
		
		
		System.out.println("Students Ids those who registered in multiple courses: \n"+StudentsWithMultipleCourses);

		
		
	}

}
