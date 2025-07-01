package com.prana;


public class Student implements QueryItem, Comparable<Student> {
    private String name;
    private String course;
    private int yearStarted;
    private String studentId;

    public Student(String name, String course, int yearStarted, String studentId) {
        this.name = name;
        this.course = course;
        this.yearStarted = yearStarted;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getYearStarted() {
        return yearStarted;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public int compareTo(Student other) {
        return this.studentId.compareTo(other.studentId);
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return switch (fieldName.toUpperCase()) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEAR" -> Integer.toString(yearStarted).equals(value);
            case "ID" -> studentId.equalsIgnoreCase(value);
            default -> false;
        };
    }

    @Override
    public String toString() {
        return "%-20s %-10s %-4d %-10s".formatted(name, course, yearStarted, studentId);
    }
}
