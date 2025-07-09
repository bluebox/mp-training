package challenges_8th_july;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Student {
	private static Random random = new Random();
	private int yearEnrolled;
	private boolean active;
	private Map<Course, Integer> lecturesWatched = new HashMap<>();

	public Map<Course, Integer> getLecturesWatched() {
		return lecturesWatched;
	}

	public Student(int yearEnrolled, boolean active, Map<Course, Integer> lecturesWatched) {
		this.yearEnrolled = yearEnrolled;
		this.active = active;
		this.lecturesWatched = lecturesWatched;
	}

	public int getCourseCount() {
		return lecturesWatched.size();
	}

	public static Student getRandomStudent(Course... courses) {
		int year = 2000 + random.nextInt(26);
		Map<Course, Integer> lecturesMap = new HashMap<>();
		int end = random.nextInt(courses.length) + 1;
		List<Course> coursesAsList = Arrays.asList(courses);
		Collections.shuffle(coursesAsList);
		List<Course> newOrder = coursesAsList.subList(0, end);
		for (Course c : newOrder) {
			int watched = 30 + random.nextInt(c.getLectureCount() - 30 + 1);
			lecturesMap.put(c, watched);
		}
		return new Student(year, random.nextBoolean(), lecturesMap);
	}

	@Override
	public String toString() {
		return "Student [yearEnrolled=" + yearEnrolled + ", active=" + active + ", Course Count=" + getCourseCount()
				+ "]";
	}

	public boolean isActive() {
		return this.active;
	}

	public double getPercentComplete(Course course) {
		int totalLectures = course.getLectureCount();
		int watched = lecturesWatched.getOrDefault(course, 0);

		return ((double) watched / totalLectures) * 100;
	}

	public double getOverallAveragePercentage() {
		double sum = 0;
		for (Map.Entry<Course, Integer> entry : lecturesWatched.entrySet()) {
			sum += getPercentComplete(entry.getKey());
		}
		return sum / this.getCourseCount();
	}

	public int getYearEnrolled() {
		return this.yearEnrolled;
	}
}
