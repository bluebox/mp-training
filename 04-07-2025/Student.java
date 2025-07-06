package com.prana;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class Student {
    private final String gender;       
    private final int age;
    private final String countryCode;
    private final boolean active;
    private final int yearsEnrolled;
    private final Map<Course, Double> progressMap = new HashMap<>();

    private Student(String gender, int age, String countryCode, boolean active, int yearsEnrolled) {
        this.gender = gender;
        this.age = age;
        this.countryCode = countryCode;
        this.active = active;
        this.yearsEnrolled = yearsEnrolled;
    }
    public static Student getRandomStudent(Course... courses) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String gender = rand.nextBoolean() ? "male" : "female";
        int age = rand.nextInt(18, 80);
        String country = List.of("US", "IN", "GB", "DE", "FR", "CN", "BR").get(rand.nextInt(7));
        boolean active = rand.nextBoolean();
        int years = rand.nextInt(1, 10);
        Student s = new Student(gender, age, country, active, years);
        for (Course c : courses) {
            if (c.getLectureCount() >= 30) {
                double pct = rand.nextDouble(0, 100);
                s.progressMap.put(c, pct);
            }
        }
        return s;
    }

    public String getGender()     { return gender; }
    public int getAge()          { return age; }
    public String getCountryCode(){ return countryCode; }
    public boolean isActive()    { return active; }
    public int getYearsEnrolled(){ return yearsEnrolled; }

    public double getPercentComplete(Course course) {
        return progressMap.getOrDefault(course, 0.0);
    }

    public void addCourse(Course course) {
        progressMap.putIfAbsent(course, 0.0);
    }

    @Override
    public String toString() {
        return String.format("Student[%s, age=%d, %s, active=%s, years=%d, progress=%s]",
            gender, age, countryCode, active, yearsEnrolled, progressMap);
    }
}
