package java;

import java.time.LocalDate;
import java.util.*;

public class Student {
    public long i = 0;
    private long studentId;
    private String countryCode;
    private int yearEnrolled;
    private int ageEnrolled;
    private String gender;
    private boolean programmingExperience;
    public Random random;
    public Map<String, courseEngagement> engagementMap;

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean programmingExperience) {
        this.studentId = i++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;
        engagementMap = new HashMap<>();
        random = new Random();

    }

    public void addCourse(Course course) {
        addCourse(course, LocalDate.now());

    }

    public int getAge() {
        return ageEnrolled + LocalDate.now().getYear() - yearEnrolled;
    }

    public void addCourse(Course course, LocalDate enrollmentDate) {
        engagementMap.put(course.getCode(), new courseEngagement(course, enrollmentDate, "enrollement", 0, enrollmentDate));
    }

    public static Student getRandomStudent(Course... courses) {
        Random random = new Random();
        String[] countries = {"IN", "AUS", "PAK", "UK", "USA"};
        String[] genders = {"Male", "Female", "Other"};

        Student newStudent = new Student(
                countries[random.nextInt(countries.length)],
                2015 + random.nextInt(35),
                20 + random.nextInt(35),
                genders[random.nextInt(genders.length)],
                random.nextBoolean()
        );

        int count = 1 + random.nextInt(Math.min(3, courses.length));
        List<Course> courseList = Arrays.asList(courses);
        Collections.shuffle(courseList);
        for (int i = 0; i < count; i++) {
            newStudent.addCourse(courseList.get(i));
        }

        return newStudent;
    }

    @Override
    public String toString() {
        return String.format(
                "Student [ID: %d, Country: %s, Enrolled Year: %d, Age Enrolled: %d, Gender: %s, Programming Experience: %s]\n%s",
                studentId,
                countryCode,
                yearEnrolled,
                ageEnrolled,
                gender,
                programmingExperience ? "Yes" : "No", "-".repeat(50)
        );
    }

    public String getGender() {
        return gender;
    }


}
