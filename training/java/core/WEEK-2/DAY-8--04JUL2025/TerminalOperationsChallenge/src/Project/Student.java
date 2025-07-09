package Project;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Student {
    enum Gender { MALE, FEMALE }

    private String name;
    private int age;
    private Gender gender;
    private String countryCode;
    private boolean isActive;
    private int yearsEnrolled;
    private List<Course> courses;
    private Map<String, Double> percentCompleted;

    public Student(String name, int age, Gender gender, String countryCode, boolean isActive, int yearsEnrolled, List<Course> courses, Map<String, Double> percentCompleted) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.countryCode = countryCode;
        this.isActive = isActive;
        this.yearsEnrolled = yearsEnrolled;
        this.courses = courses;
        this.percentCompleted = percentCompleted;
    }

    public static Student getRandomStudent(List<Course> allCourses) {
        Random random = new Random();
        int age = ThreadLocalRandom.current().nextInt(18, 80);
        Gender gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        String country = List.of("IN", "US", "UK", "AU", "CA").get(random.nextInt(5));
        boolean isActive = random.nextBoolean();
        int yearsEnrolled = random.nextInt(1, 10);

        Map<String, Double> percentMap = new HashMap<>();
        for (Course c : allCourses) {
            percentMap.put(c.getCourseName(), random.nextDouble() * 100);
        }

        return new Student("Student_" + UUID.randomUUID().toString().substring(0, 5), age, gender, country, isActive, yearsEnrolled, allCourses, percentMap);
    }

    public int getAge() { return age; }
    public Gender getGender() { return gender; }
    public String getCountryCode() { return countryCode; }
    public boolean isActive() { return isActive; }
    public int getYearsEnrolled() { return yearsEnrolled; }
    public Map<String, Double> getPercentCompleted() { return percentCompleted; }
    public String getName() { return name; }
    public List<Course> getCourses() { return courses; }

    public double getPercentCompleteForCourse(String courseName) {
        return percentCompleted.getOrDefault(courseName, 0.0);
    }

    @Override
    public String toString() {
        return name + " (" + age + ", " + gender + ", " + countryCode + ", active: " + isActive + ", years: " + yearsEnrolled + ")";
    }
}

