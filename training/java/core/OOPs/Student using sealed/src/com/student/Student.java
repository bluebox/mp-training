package com.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class Student extends CourseEngagement{
	public static List<Student> studentList;
	private long studentId;
	private String countryCode;
	private int yearEnrolled;
	private int ageEnrolled;
	private String gender;
	private boolean programmingExperience;
	private Map<String,CourseEngagement> engagementMap;
	public Course course;
	public LocalDate enrollmentDate;
	public String engagementType;
	public int lastLecture;
	public LocalDate lastActivityDate;
	public Student(long studentId,String countryCode,int yearEnrolled,int ageEnrolled,String gender,boolean programmingExperience,Map<String,CourseEngagement> engagementMap,Course course,LocalDate enrollmentDate,String engagementType,int lastLecture,LocalDate lastActivityDate) {
		super(course, enrollmentDate, engagementType, lastLecture, lastActivityDate);
		this.studentId=studentId;
		this.countryCode=countryCode;
		this.yearEnrolled=yearEnrolled;
		this.ageEnrolled=ageEnrolled;
		this.gender=gender;
		this.programmingExperience=programmingExperience;
		this.engagementMap=engagementMap;
	}
	public void addCourse(Course c) {
		CourseEngagement ce=new CourseEngagement(c, LocalDate.now(), "Sincere",0, LocalDate.now());
		engagementMap.put(c.courseCode, ce);
	}
	public void addCourse(Course c,LocalDate enrollDate) {
		CourseEngagement ce=new CourseEngagement(c, enrollDate, engagementMap.get(c.courseCode).engagementType,engagementMap.get(c).lastLecture, LocalDate.now());
		engagementMap.put(c.courseCode, ce);
	}
	public int getAge() {
		return ageEnrolled;
		
	}
	public int getMonthSinceActive(String courseCode) {
		return engagementMap.get(courseCode).getMonthsSinceActive();
		
	}
	public int getMonthSinceActive() {
		return LocalDate.now().until(lastActivityDate).getMonths();
		
	}
	public double getPercentComplete(String courseCode) {
		return this.getPercentageComplete();
	}
	public int getYearsSinceEnrolled() {
		return LocalDate.now().getYear()-this.yearEnrolled;
	}
	public void watchLecture(int lectureNumber,int month,int year) {
		this.lastLecture=lectureNumber;
		this.lastActivityDate=LocalDate.of(year, month, (int)(Math.random()*29+1));
	}
	public static Optional<Student> getRandomStudent(Course c) {
		return studentList.stream().filter(i->i.course==c)
				.findAny();
	}
}
