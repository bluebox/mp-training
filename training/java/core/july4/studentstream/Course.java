package dev.tulasidhar.july4.studentstream;

public record Course(String courseCode, String title, int lectureCount) {

    public Course {
        if (lectureCount <= 0) {
            lectureCount = 1;
        }
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
}
