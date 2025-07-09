package day9;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentChallenge {

	public static void main(String[] args) {
		Course jmc = new Course("JMC", "Java Masterclass", 100);
		Course springboot = new Course("SB", "Spring boot", 70);
		Course sql = new Course("SQL", "SQL Masterclass", 60);

		List<Student> students = Stream
				.generate(() -> Student.getRandomStudent(jmc, sql, springboot))
				.limit(5000)
				.collect(Collectors.toList());
		long jmcCount=students.stream()
				.filter(s->s.getLecturesWatched().containsKey(jmc))
				.count();
		System.out.println("jmc count is "+jmcCount);
		
		Map<Integer,Long> counter=students.stream() 
				.collect(Collectors.groupingBy(Student::getCourseCount,Collectors.counting()));
		System.out.println("no of students per number of courses are "+counter);
		
		double totalAveragePercentage=students.stream()
				.collect(Collectors.averagingDouble(s->s.getOverallAveragePercentage()));
		System.out.println("total average percentage "+totalAveragePercentage);
		
		Map<Integer,Long> yearMap=students.stream()
				.collect(Collectors.groupingBy(Student::getYearEnrolled,Collectors.counting()));
		
		System.out.println("year map is");
		System.out.println(yearMap);
	}

}
