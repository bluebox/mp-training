
package Json;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {
    private Course course;
    private LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }

    public String getCourseCode() {
        return course.getCourseCode();
    }

    public double getPercentComplete() {
        return (lastLecture * 100.0) / course.getLectureCount();
    }

    public int getMonthsSinceActive() {
        LocalDate now = LocalDate.now();
        Period diff = Period.between(lastActivityDate, now);
        return diff.getYears() * 12 + diff.getMonths();
    }

    public void watchLecture(int lectureNumber, LocalDate currentDate) {
        if (lectureNumber > lastLecture) {
            lastLecture = lectureNumber;
        }
        lastActivityDate = currentDate;
        engagementType = "Lecture " + lastLecture;
    }

    public String toString() {
        return course.getCourseCode() + " | Lecture: " + lastLecture +
               " | Last Active: " + lastActivityDate;
    }
}


