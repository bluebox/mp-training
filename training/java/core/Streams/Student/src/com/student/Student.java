package com.student;

import java.time.LocalDate;

import java.util.*;
public non-sealed class Student extends CourseEngagement{
	public static Map<Long,Student> studentList=new HashMap<>();
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
	public Long getStudentId() {
		return this.studentId;
	}
	public void addCourse(Course c) {
		CourseEngagement ce=new CourseEngagement(c, LocalDate.now(), "Sincere",0, LocalDate.now());
		engagementMap.put(c.courseCode, ce);
		System.out.println(engagementMap.keySet());
	}
	public void addCourse(Course c,LocalDate enrollDate) {
		CourseEngagement ce;
		try {
			ce=new CourseEngagement(c, enrollDate, engagementMap.get(c.courseCode).engagementType,engagementMap.get(c).lastLecture, LocalDate.now());
		}
		catch(Exception e) {
			ce=new CourseEngagement(c, enrollDate, engagementMap.get(c.courseCode).engagementType,0, LocalDate.now());
		}
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
	public static ArrayList<Optional<Student>> getRandomStudent(Course ...course) {
		ArrayList<Optional<Student>> s=new ArrayList<>();
		for(Course c:course) {
		s.add(((Collection<Student>) studentList).stream().filter(i->i.course==c)
				.findAny());
		}
		return s;
	}
	public String toString() {
		for(String c:engagementMap.keySet()) {
			System.out.println(engagementMap.containsKey(c));
		}
		return "Student ID : "+studentId+"\nCountry code : "+countryCode+"\nEnrolled year : "+yearEnrolled+"\nEnrolled Age : "+ageEnrolled+"\nGender : "+gender+"\nProgramming Experience : "+programmingExperience+"\nCourses engaged : \n"+engagementMap.toString()+"\nCourses : "+course+"\nEnrollment date : "+enrollmentDate+"\nEngagement type : "+engagementType+"\nLast Lecture : "+lastLecture+"\nLast Active Date : "+lastActivityDate;
	}
}
