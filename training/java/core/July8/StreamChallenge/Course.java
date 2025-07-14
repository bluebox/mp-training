package July8.StreamChallenge;

public class Course {
	
	private String courseName;
	private String title;
	private int lectureCount;

	public Course(String courseName, String title, int lectureCount) {
		this.courseName = courseName;
		this.title = title;
		this.lectureCount = lectureCount;
	}

	public String getCourseName() {
		return courseName;
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
		return "Course Name = " + courseName + ", Title = " + title + ", Lecture Count = " + lectureCount;
	}
}
