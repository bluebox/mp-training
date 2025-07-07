package day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {

	public static void main(String[] args) {
		Course jmc = new Course("JMC", "Java Masterclass", 100);
		Course springboot = new Course("SB", "Spring boot", 70);
		Course sql = new Course("SQL", "SQL Masterclass", 60);

		List<Student> students = Stream
				.generate(() -> Student.getRandomStudent(jmc, sql, springboot))
				.limit(5000)
				.collect(Collectors.toList());

		double average = students.stream()
				.mapToDouble(s -> s.getPercentComplete("JMC", jmc))
				.average()
				.orElse(0.0);
		
		System.out.println("Average JMC completion is " + average);

		long above125 = students.stream()
				.filter(s -> s.getPercentComplete("JMC", jmc) > average * 1.25)
				.count();

		System.out.println("above 1.25 times average are " + above125);
	}

	static class Student {
		private static Random random = new Random();
		private int yearEnrolled;
		private boolean active;
		private Map<String, Integer> lecturesWatched = new HashMap<>();

		public Student(int yearEnrolled, boolean active, Map<String, Integer> lecturesWatched) {
			this.yearEnrolled = yearEnrolled;
			this.active = active;
			this.lecturesWatched = lecturesWatched;
		}

		public static Student getRandomStudent(Course... courses) {
			int year = 2000 + random.nextInt(26);
			Map<String, Integer> lecturesMap = new HashMap<>();
			for (Course c : courses) {
				int watched = 30 + random.nextInt(c.getLectureCount() - 30 + 1);
				lecturesMap.put(c.getCourseCode(), watched);
			}
			return new Student(year, random.nextBoolean(), lecturesMap);
		}

		public boolean isActive() {
			return this.active;
		}

		public double getPercentComplete(String courseCode, Course course) {
			int totalLectures = course.getLectureCount();
			int watched = lecturesWatched.getOrDefault(course.getCourseCode(), 0);

			return ((double) watched / totalLectures) * 100;
		}

		public int getYearEnrolled() {
			return this.yearEnrolled;
		}
	}

}
