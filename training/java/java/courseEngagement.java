package java;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class courseEngagement {
    private Course course;
    private LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public courseEngagement(Course course, LocalDate enrollmentDate, String engagementType, int lastLecture, LocalDate lastActivityDate) {
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.engagementType = engagementType;
        this.lastLecture = lastLecture;
        this.lastActivityDate = lastActivityDate;
    }

    public String getCourseCode() {
        return course.getCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public Month getLastActivityMonth() {
        return lastActivityDate.getMonth();
    }

    public int getMonthsSinceActive() {
        Period gap = Period.between(LocalDate.now(), lastActivityDate);
        return gap.getMonths();
    }

    public double getPercentageComplete() {
        return (lastLecture * 100 / course.getLectureCount());

    }


}
