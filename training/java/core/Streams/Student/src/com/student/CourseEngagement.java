package com.student;

import java.time.LocalDate;

@SuppressWarnings("preview")
public class CourseEngagement{
	public Course course;
	public LocalDate enrollmentDate;
	public String engagementType;
	public int lastLecture;
	public LocalDate lastActivityDate;
	CourseEngagement()
	{
		
	}
	public CourseEngagement(Course course,LocalDate enrollmentDate,String engagementType,int lastLecture,LocalDate lastActivityDate){
		this.course=course;
		this.enrollmentDate=enrollmentDate;
		this.engagementType=engagementType;
		this.lastLecture=lastLecture;
		this.lastActivityDate=lastActivityDate;
	}
	public String getCourseCode() {
		return course.courseCode;
	}
	public int getEnrollmentYear() {
		return this.enrollmentDate.getYear();
	}
	public int getLastActivityYear() {
		return this.lastActivityDate.getYear();
	}
	public int getLastActvityMonth() {
		return this.lastActivityDate.getMonthValue();
	}
	public int getMonthsSinceActive() {
		return LocalDate.now().until(lastActivityDate).getMonths();
	}
	public double getPercentageComplete() {
	    if (course == null || course.lectureCount == 0) return 0.0;
	    double x= ((double) this.lastLecture / course.lectureCount) * 100;
	    return x>0?x:0;
	}
	public void watchLecture(int lecture,LocalDate date) {
		this.lastLecture=lecture;
		this.lastActivityDate=date;
	}
}
