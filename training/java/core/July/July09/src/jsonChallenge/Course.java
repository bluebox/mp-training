package jsonChallenge;

import java.util.StringJoiner;

public class Course {

	private final String courseCode;
	private final String title;
	private final int lectureCount;

	public Course(String courseCode, String title, int lectureCount) {
		this.courseCode = courseCode;
		this.title = title;
		this.lectureCount = lectureCount;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getTitle() {
		return title;
	}

	public int getLectureCount() {
		if(lectureCount <= 0) {
			return 3;
		}
		return lectureCount;
	}
	@Override
	public String toString() {
		return new StringJoiner(", ","{","}") 
				.add( "\"courseCode\":\"" + courseCode+"\"")
				.add("\"title\":\""+ title+"\"")
				.add("\"lectureCount\":"+ lectureCount)
				.toString();
	}
	
//	@Override
//	public String toString() {
//		return "Course [courseCode=" + courseCode + ", title=" + title + ", lectureCount=" + lectureCount + "]";
//	}

}
