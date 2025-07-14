package July8.StreamChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("A001","Python",new Random().nextInt(30,80)));
		courses.add(new Course("A002","Data Science",new Random().nextInt(30,80)));
		courses.add(new Course("A003","Generative AI",new Random().nextInt(30,80)));
 
		var students = Stream.iterate(1,i->i+1)
				.limit(10000)
				.map(s-> Student.getRandomStudent(courses.subList(0, new Random().nextInt(0,4))))
				.collect(Collectors.toList());
		
		var stats= students.stream()
				.mapToDouble(s->s.getPercentComplete())
				.summaryStatistics();
		
		System.out.println("Stats :"+stats); 
		
		var groupByCourse = students.stream()
				.flatMap(s->s.getEngagementMap().values().stream()
				.map(CourseEngagement::getCourse))
				.map(Course::getTitle)
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		System.out.println("Counting Student in Each Course : ");
		System.out.println(groupByCourse);
		
		var groupByNumberCourse = students.stream()
				.collect(Collectors.groupingBy(s->s.getEngagementMap().size(),Collectors.counting()));
		
		for(Entry<Integer, Long> count :groupByNumberCourse.entrySet()) {
			System.out.println("Course "+count.getKey()+" - Number Students Enrolled : "+count.getValue());
		}
		
		var groupByCourseComplete = students.stream()
				.flatMap(s->s.getEngagementMap().values().stream())
				.collect(Collectors.groupingBy(CourseEngagement::getCourse,Collectors.averagingDouble(s->s.getPercentComplete())));
 
		for(var count :groupByCourseComplete.entrySet()) {
			System.out.println("Course : "+count.getKey().getTitle()+" Average complete Percentage : "+count.getValue());
		}
	}
}
