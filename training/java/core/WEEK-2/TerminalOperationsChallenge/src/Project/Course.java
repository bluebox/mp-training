package Project;

public class Course {
    private String courseName;
    private int lectureCount;

    public Course(String courseName, int lectureCount) {
        this.courseName = courseName;
        this.lectureCount = lectureCount;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getLectureCount() {
        return lectureCount;
    }
}

