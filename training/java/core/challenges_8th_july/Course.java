package challenges_8th_july;


public class Course {
	private String courseCode;
	private String title;
	private int lectureCount;

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
		return lectureCount;
	}
}
