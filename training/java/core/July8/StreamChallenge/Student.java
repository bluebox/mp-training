package July8.StreamChallenge;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Student {

	private static int id = 1000;
	private int studentId;
	private String countryCode;
	private int yearEnrolled;
	private int ageEnrolled;
	private String gender;
	private boolean programmingExperience;
	private Map<String, CourseEngagement> engagementMap;

	public Student(int studentId, String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean programmingExperience) {
		this.studentId = studentId;
		this.countryCode = countryCode;
		this.yearEnrolled = yearEnrolled;
		this.ageEnrolled = ageEnrolled;
		this.gender = gender;
		this.programmingExperience = programmingExperience;
		this.engagementMap = new HashMap<>();
	}

	public int getStudentId() {
		return studentId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public int getYearEnrolled() {
		return yearEnrolled;
	}

	public int getAgeEnrolled() {
		return ageEnrolled;
	}

	public String getGender() {
		return gender;
	}

	public boolean isProgrammingExperience() {
		return programmingExperience;
	}

	public Map<String, CourseEngagement> getEngagementMap() {
		return engagementMap;
	}

	public void addCourse(Course course) {
		var enrollDate = LocalDate.of(new Random().nextInt(2020,2026),new Random().nextInt(1,13),1);
		addCourse(course,enrollDate);
	}

	public void addCourse(Course course, LocalDate enrollDate) {

		if(! engagementMap.containsKey(course.getCourseName())) {

			engagementMap.put(course.getCourseName(),new CourseEngagement(course,enrollDate,"Online",new Random().nextInt(1,30),enrollDate));
//			System.out.println("Course Added Sucessfully");
			return;

		}
		System.out.println("Course Already Enrolled");
	}

	public int getAge() {
		
		return LocalDate.now().getYear() - yearEnrolled + ageEnrolled;
	}

	public int getMonthsSinceActive(String courseCode) {

		return engagementMap.get(courseCode).getMonthsSinceActive();
	}

	public double getPercentComplete(String courseCode) {
		
		return engagementMap.get(courseCode).getPercentComplete();
	}
	
	public double getPercentComplete() {
		
		double sum=0;
		for(var courseEng : engagementMap.values()) {
			
			sum+=getPercentComplete(courseEng.getCourseName());
		}
		return sum == 0 ? 0: sum/engagementMap.size();
	}

	public int getMonthsSinceActive() {
		
		int min = 1000;
		for(CourseEngagement course: engagementMap.values()) {
			int inActive = course.getMonthsSinceActive();
			if(min > inActive) {
				min = inActive;
			}
		}
		return min;
	}

	public int getYearsSinceEnrolled() {

		return LocalDate.now().getYear() - yearEnrolled;
	}

	public void watchLecture(String courseCode,int lectureNumber,int year,int month) {
		
		if(engagementMap.containsKey(courseCode)) {
			engagementMap.get(courseCode).watchLecture(lectureNumber, LocalDate.of(year, month, 1));
			return;
		}
		System.out.println("Course not Enrolled");
	}

	@Override
	public String toString() {
		return "Student Id = " + studentId + ", Country Code = " + countryCode + ", Year Enrolled = " + yearEnrolled + ", Age Enrolled = " + ageEnrolled + ", Gender = " + gender + ", Programming Experience = " + programmingExperience + ", Engagement Map = " + engagementMap;
	}

	public static Student getRandomStudent(List<Course> courses) {
		
		Random random = new Random();
		Student student= new Student(id++ , ("+".concat(random.nextInt(50,500)+"") ),random.nextInt(2020,2026),random.nextInt(1,80),random.nextBoolean() ? "Male": "Female",random.nextBoolean());
		for(Course course : courses) {
			student.addCourse(course);
			student.watchLecture(course.getCourseName(), random.nextInt(5,course.getLectureCount()),random.nextInt(2020,2030), random.nextInt(1,12));
		}
		return student;
	}
}
