package TerminalOperations;

import java.time.LocalDate;
import java.util.*;

public class Student {
    private static long lastId = 1;
    private long id;
    private String country;
    private int enrolledYear;
    private int enrolledAge;
    private String gender;
    private boolean hasExperience;
    private Map<String, CourseEngagement> engagements = new HashMap<>();

    public Student(String country, int enrolledYear, int enrolledAge, String gender, boolean hasExperience, Course... courses) {
        this.id = lastId++;
        this.country = country;
        this.enrolledYear = enrolledYear;
        this.enrolledAge = enrolledAge;
        this.gender = gender;
        this.hasExperience = hasExperience;

        for (Course c : courses) {
            addCourse(c);
        }
    }

    public void addCourse(Course c) {
        engagements.put(c.getCourseCode(), new CourseEngagement(c, LocalDate.of(enrolledYear, 1, 1), "Enrolled"));
    }

    public void watchLecture(String courseCode, int lecture, int month, int year) {
        CourseEngagement engagement = engagements.get(courseCode);
        if (engagement != null) {
            engagement.watchLecture(lecture, LocalDate.of(year, month, 1));
        }
    }

    public double getPercentComplete(String courseCode) {
        CourseEngagement engagement = engagements.get(courseCode);
        return (engagement != null) ? engagement.getPercentComplete() : 0;
    }

    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement engagement = engagements.get(courseCode);
        return (engagement != null) ? engagement.getMonthsSinceActive() : 0;
    }

    public int getAge() {
        return enrolledAge + (LocalDate.now().getYear() - enrolledYear);
    }

    public static Student getRandomStudent(Course... courses) {
        Random random = new Random();
        String[] countries = {"AU", "US", "IN", "CA"};
        String[] genders = {"M", "F", "U"};

        Student student = new Student(
                countries[random.nextInt(countries.length)],
                random.nextInt(2015, 2025),
                random.nextInt(18, 50),
                genders[random.nextInt(genders.length)],
                random.nextBoolean(),
                courses);

        for (Course c : courses) {
            int lecture = random.nextInt(1, c.getLectureCount());
            int year = random.nextInt(student.enrolledYear, LocalDate.now().getYear() + 1);
            int month = random.nextInt(1, 13);
            student.watchLecture(c.getCourseCode(), lecture, month, year);
        }

        return student;
    }

    public long getId() { return id; }
    public String getCountry() { return country; }
    public int getEnrolledAge() { return enrolledAge; }
    public String getGender() { return gender; }
    public boolean hasExperience() { return hasExperience; }
}

