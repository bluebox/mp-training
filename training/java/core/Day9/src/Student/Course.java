package Student;

public class Course {
    private String courseCode;
    private String title;
    private int lectureCount;

    public Course(String courseCode, String title, int lectureCount) {
        this.courseCode = courseCode;
        this.title = title;
        if (lectureCount <= 0) {
            this.lectureCount = 1;
        } else {
            this.lectureCount = lectureCount;
        }
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
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

    public String toString() {
        return courseCode + " " + title;
    }
}


