package Student;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {
	private Course course;
	private LocalDate enrollmentDate;
	private String engagementType;
	private int lastLecture;
	private LocalDate lastActivityDate;

	public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType, int lastLecture,
			LocalDate lastActivityDate) {
		this.course = course;
		this.enrollmentDate = enrollmentDate;
		this.engagementType = engagementType;
		if (lastLecture <= 0) {
			lastLecture = 5;
		}
		this.lastLecture = lastLecture;
		this.lastActivityDate = lastActivityDate;
	}

	public String getCourseCode() {

		return course.getCourseCode();
	}

	public int getEnrollmentYear() {

		return enrollmentDate.getYear();
	}

	public int getLastActivityYear() {

		return lastActivityDate.getYear();
	}

	public String getlastActivityMonth() {

		return lastActivityDate.getMonth().toString();
	}

	public int getMonthsSinceActive() {

		return Period.between(LocalDate.now(),lastActivityDate).getMonths();
	}

	public double getPercentComplete() {
		return (Double.valueOf(lastLecture)  /Double.valueOf( course.getLectureCount()) ) * 100;
	}

	public void watchLecture(int lecture,LocalDate date) {
		this.lastLecture = lecture;
		this.lastActivityDate = date;
	}

	public Course getCourse() {
		return course;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public String getEngagementType() {
		return engagementType;
	}

	public int getLastLecture() {
		return lastLecture;
	}

	public LocalDate getLastActivityDate() {
		return lastActivityDate;
	}

	@Override
	public String toString() {
		return "CourseEngagement [course=" + course + ", enrollmentDate=" + enrollmentDate + ", engagementType="
				+ engagementType + ", lastLecture=" + lastLecture + ", lastActivityDate=" + lastActivityDate + "]";
	}


}
