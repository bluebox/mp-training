package day8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student_Main {

	public static void main(String[] args) {
		List<Student> students = Stream
				.generate(Student::getRandomStudent)
				.limit(100)
				.collect(Collectors.toList());

		int genderCountMale = (int) 
				students.stream().
				filter(student -> student.getGender() == Gender.MALE)
				.count();
		System.out.println("no of male are: " + genderCountMale);

		int ageAbove50 = (int) 
				students.stream().
				filter(student -> student.getAge() > 50)
				.count();
		System.out.println("students above 50 are " + ageAbove50);
		
		int expCount= (int)
				students.stream()
				.filter(student -> student.isHasExp())
				.count();
		System.out.println("no of experienced are "+expCount);
		
	}

}
