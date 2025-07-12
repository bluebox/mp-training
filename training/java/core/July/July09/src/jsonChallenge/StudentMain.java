package jsonChallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentMain {
	public static void main(String[] args) {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("A001","Java",new Random().nextInt(30,80)));
		courses.add(new Course("A002","Python",new Random().nextInt(30,80)));
		courses.add(new Course("A003","React",new Random().nextInt(30,80)));
 

		var students = Stream.iterate(1,i->i+1).limit(1000)
				.map(s-> Student.getRandomStudent(
						courses.subList(0, new Random().nextInt(0,4))))
				.map(Student::toString)
				.collect(Collectors.joining(",\n","[","]"));
		System.out.println(students);
		
		 try {
			Files.writeString(Path.of(".\\src\\jsonChallenge\\students.json"),students);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
