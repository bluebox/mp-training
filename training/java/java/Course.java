package java;

public class Course {
    private String courseCode;
    private String title;
    private int lectureCount;

    public Course(String courseCode, String title, int lectureCount) {
        this.courseCode = courseCode;
        this.title = title;
        this.lectureCount = lectureCount;
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 100);
    }

    public String toString() {
        return (courseCode + " " + title);
    }

    public String getCode() {
        return courseCode;
    }

    public int getLectureCount() {
        return lectureCount;
    }

}
