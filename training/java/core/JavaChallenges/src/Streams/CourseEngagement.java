package Streams;

import java.time.LocalDate;

public class CourseEngagement {
	public Course course;
	public LocalDate enrollmentDate;
	public String engagementType;
	public int lastLecture;
	public LocalDate lastActivityDate;
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
		return (this.lastLecture/course.lectureCount)*100;
	}
	public void watchLecture(int lecture,LocalDate date) {
		this.lastLecture=lecture;
		this.lastActivityDate=date;
	}
}