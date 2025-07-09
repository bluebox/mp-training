package Student;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();

		for(int i=0;i<10;i++) {
			Student student = Student.getRandomStudent(new Course("A001"+i,
					new Random().nextBoolean() ?"java" : "python",new Random().nextInt(30,80)));
			students.add(student);
			System.out.println(student);
		}

		long maleCount = students.stream().filter(s->s.getGender() == "Male").count();
		System.out.println("Total Count "+students.size());
		System.out.println("Male count "+maleCount);
		System.out.println("Female Count "+ (students.size()-maleCount));

		System.out.println("Toatal Count "+students.size());
		long old = students.stream().filter(s-> s.getAge() > 60 ).count() ;
		System.out.println("Old people (over 60 age) "+ old);

		long children= students.stream().filter(s-> s.getAge() <= 30 ).count();
		System.out.println("childrens (below 30 age) "+ children);

		long middleAge = students.stream().filter(s-> s.getAge() > 30 && s.getAge() <= 60).count();
		System.out.println("Middle Age people (between 30 to 60) "+ middleAge);

		System.out.println("Using summaryStatistics");
		IntSummaryStatistics stats = students.stream().mapToInt(s->s.getAge()).summaryStatistics();
		System.out.println(stats);

		students.stream().map(s-> s.getCountryCode()).distinct().forEach(System.out::print);
		System.out.println();

		boolean isActiveNow = students.stream()
				.anyMatch( s-> s.getYearsSinceEnrolled() > 7 && s.getMonthsSinceActive() < 7);
		System.out.println("student that are still active and enrolled for more than 7 Years? "+isActiveNow);

		students.stream().filter(s->s.getYearsSinceEnrolled() > 7 && s.getMonthsSinceActive() < 7)
		.limit(5).forEach(System.out::println);

		 Stream.iterate(1,i->i+1).limit(5000).map(s->
			Student.getRandomStudent(new Course("A001"+ new Random().nextInt(1,10000),
					new Random().nextBoolean() ? new Random().nextBoolean()?"java" : "games in java" : "python",
					new Random().nextInt(30,80)))).limit(10).forEach(System.out::println);

	}
}
