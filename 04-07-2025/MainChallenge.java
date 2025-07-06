package com.prana;

import java.util.*;
import java.util.stream.*;

public class MainChallenge {
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        Course pymc = new Course("Python Masterclass", 50);
        Course jmc  = new Course("Java Masterclass", 100);
        Course gameDev = new Course("Creating Games in Java", 30);
        List<Course> courses = List.of(pymc, jmc, gameDev);
        List<Student> students = Stream
            .generate(() -> Student.getRandomStudent(pymc, jmc, gameDev))
            .limit(5000)
            .toList();
        long maleCount = students.stream()
            .filter(s -> "male".equalsIgnoreCase(s.getGender()))
            .count();
        long femaleCount = students.stream()
            .filter(s -> "female".equalsIgnoreCase(s.getGender()))
            .count();
        long under30 = students.stream().filter(s -> s.getAge() < 30).count();
        long between30And60 = students.stream().filter(s -> s.getAge() >= 30 && s.getAge() <= 60).count();
        long over60 = students.stream().filter(s -> s.getAge() > 60).count();
        IntSummaryStatistics ageStats = students.stream()
            .mapToInt(Student::getAge)
            .summaryStatistics();

        Set<String> countries = students.stream()
            .map(Student::getCountryCode)
            .collect(Collectors.toSet());

        boolean anyLongActive = students.stream()
            .anyMatch(s -> s.isActive() && s.getYearsEnrolled() > 7);

        System.out.println("Gender distribution: males=" + maleCount + ", females=" + femaleCount);
        System.out.println("Age groups: <30=" + under30 + ", 30–60=" + between30And60 + ", >60=" + over60);
        System.out.println("Age summary: " + ageStats);
        System.out.println("Countries: " + countries);
        System.out.println("Any active >7 years: " + anyLongActive);

        System.out.println("\nSample 5 students:");
        students.stream().limit(5).forEach(System.out::println);

        double avgPercent = students.stream()
            .mapToDouble(s -> s.getPercentComplete(jmc))
            .average()
            .orElse(0);

        System.out.printf("\n Avg %% complete in Java Masterclass: %.2f%%%n", avgPercent);

        double threshold = avgPercent * 1.25;
        List<Student> highCompletion = students.stream()
            .filter(s -> s.getPercentComplete(jmc) > threshold)
            .toList();
        List<Student> trialStudents = highCompletion.stream()
            .filter(Student::isActive)
            .sorted(Comparator.comparingInt(Student::getYearsEnrolled).reversed())
            .limit(10)
            .toList();
        trialStudents.forEach(s -> s.addCourse(gameDev));
        System.out.println("\n Trial run – 10 selected students (active, high % complete, sorted by years enrolled):");
        trialStudents.forEach(System.out::println);
    }
}
