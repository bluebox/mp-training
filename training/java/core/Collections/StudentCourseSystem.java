package Collections;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

final class Course {
    private final String courseCode;
    private final String title;
    private final int lectureCount;

    public Course(String courseCode, String title, int lectureCount) {
        this.courseCode = courseCode;
        this.title = title;
        this.lectureCount = lectureCount;
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getLectureCount() { return lectureCount; }
}

final class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;
    private final String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.engagementType = engagementType;
        this.lastLecture = 0;
        this.lastActivityDate = enrollmentDate;
    }

    public String getCourseCode() { return course.getCourseCode(); }
    public int getEnrollmentYear() { return enrollmentDate.getYear(); }
    public int getLastActivityYear() { return lastActivityDate.getYear(); }
    public String getLastActivityMonth() { return lastActivityDate.getMonth().toString(); }
    public double getPercentCompleted() { return (lastLecture * 100.0) / course.getLectureCount(); }

    public int getMonthsSinceActive() {
        return (int) Period.between(lastActivityDate, LocalDate.now()).toTotalMonths();
    }

    public void watchLecture(int lecture, LocalDate date) {
        if (lecture > lastLecture) lastLecture = lecture;
        if (date.isAfter(lastActivityDate)) lastActivityDate = date;
    }
}

final class Student {
    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean programmingExperience;
    private final Map<String, CourseEngagement> engagementMap;

    public Student(long studentId, String countryCode, int yearEnrolled, int ageEnrolled, 
                  String gender, boolean programmingExperience) {
        this.studentId = studentId;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;
        this.engagementMap = new HashMap<>();
    }

    public void addCourse(Course course) {
        addCourse(course, LocalDate.now());
    }

    public void addCourse(Course course, LocalDate enrollDate) {
        engagementMap.put(course.getCourseCode(), 
            new CourseEngagement(course, enrollDate, "REGULAR"));
    }

    public int getAge() { return ageEnrolled + getYearsSinceEnrolled(); }
    public int getYearsSinceEnrolled() { return LocalDate.now().getYear() - yearEnrolled; }

    public int getMonthsSinceActive() {
        return engagementMap.values().stream()
            .mapToInt(CourseEngagement::getMonthsSinceActive)
            .min().orElse(0);
    }

    public int getMonthsSinceActive(String courseCode) {
        return engagementMap.containsKey(courseCode) ? 
            engagementMap.get(courseCode).getMonthsSinceActive() : 0;
    }

    public double getPercentComplete(String courseCode) {
        return engagementMap.containsKey(courseCode) ? 
            engagementMap.get(courseCode).getPercentCompleted() : 0.0;
    }

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        if (engagementMap.containsKey(courseCode)) {
            engagementMap.get(courseCode).watchLecture(lectureNumber, 
                LocalDate.of(year, month, 1));
        }
    }

    public static Student getRandomStudent(Course... courses) {
        Random random = new Random();
        Student student = new Student(
            random.nextInt(900000) + 100000,
            "US",
            LocalDate.now().getYear() - random.nextInt(5),
            18 + random.nextInt(50),
            random.nextBoolean() ? "M" : "F",
            random.nextBoolean()
        );
        for (Course course : courses) {
            student.addCourse(course, LocalDate.now().minusMonths(random.nextInt(12)));
        }
        return student;
    }
}

public class StudentCourseSystem {
    public static void main(String[] args) {
        Course javaCourse = new Course("J101", "Java Programming", 50);
        Course pythonCourse = new Course("P201", "Python Fundamentals", 40);

        Student student = Student.getRandomStudent(javaCourse, pythonCourse);
        student.watchLecture("J101", 25, 6, 2025);
        student.watchLecture("P201", 10, 6, 2025);

        System.out.println("Student age: " + student.getAge());
        System.out.println("Java course progress: " + student.getPercentComplete("J101") + "%");
        System.out.println("Months since active in Python: " + student.getMonthsSinceActive("P201"));
    }
}