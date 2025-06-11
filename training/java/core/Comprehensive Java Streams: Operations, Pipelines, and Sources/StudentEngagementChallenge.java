package one;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Course {
    private String name;
    private int lectureCount;
    private int lastActivityYear;

    public Course(String name, int lectureCount) {
        this.name = name;
        this.lectureCount = lectureCount;
    }

    public String getName() { return name; }
    public int getLectureCount() { return lectureCount; }
    public int getLastActivityYear() { return lastActivityYear; }
    public void setLastActivityYear(int year) { this.lastActivityYear = year; }
}

class Student {
    private String name;
    private List<Course> courses;
    private int enrollmentYear;
    private boolean isActive;

    public Student(String name, List<Course> courses, int enrollmentYear, boolean isActive) {
        this.name = name;
        this.courses = courses;
        this.enrollmentYear = enrollmentYear;
        this.isActive = isActive;
    }

    public static Student getRandomStudent(Supplier<List<Course>> coursesSupplier) {
        Random random = new Random();
        String name = "Student" + random.nextInt(10000);
        List<Course> courses = coursesSupplier.get();
        int enrollmentYear = 2021 + random.nextInt(4);
        boolean isActive = random.nextBoolean();
        return new Student(name, courses, enrollmentYear, isActive);
    }

    public String getName() { return name; }
    public List<Course> getCourses() { return courses; }
    public int getEnrollmentYear() { return enrollmentYear; }
    public boolean isActive() { return isActive; }
    public void addCourse(Course course) { courses.add(course); }
    public double getAveragePercentageComplete() {
        return courses.stream()
            .mapToDouble(c -> (30.0 / c.getLectureCount()) * 100)
            .average()
            .orElse(0.0);
    }
}

public class StudentEngagementChallenge {
    public static void main(String[] args) {
        List<Course> allCourses = Arrays.asList(
            new Course("Math", 50),
            new Course("Science", 45),
            new Course("History", 40),
            new Course("Art", 55)
        );

        Supplier<List<Course>> coursesSupplier = () -> {
            Random random = new Random();
            int courseCount = 1 + random.nextInt(3);
            Collections.shuffle(allCourses);
            return allCourses.subList(0, courseCount).stream()
                .map(c -> new Course(c.getName(), c.getLectureCount()))
                .collect(Collectors.toList());
        };

        List<Student> students = IntStream.range(0, 10000)
            .mapToObj(i -> Student.getRandomStudent(coursesSupplier))
            .collect(Collectors.toList());

        double averagePercentage = students.stream()
            .flatMap(s -> s.getCourses().stream())
            .collect(Collectors.averagingDouble(c -> (30.0 / c.getLectureCount()) * 100));

        double threshold = averagePercentage * 1.25;

        Set<Student> selectedStudents = students.stream()
            .filter(s -> s.getAveragePercentageComplete() > threshold)
            .filter(Student::isActive)
            .sorted(Comparator.comparing(Student::getEnrollmentYear))
            .limit(10)
            .collect(Collectors.toSet());

        Course newCourse = new Course("Programming", 60);
        selectedStudents.forEach(s -> s.addCourse(newCourse));

        Map<String, Long> studentsPerCourse = students.stream()
            .flatMap(s -> s.getCourses().stream())
            .collect(Collectors.groupingBy(Course::getName, Collectors.counting()));

        Map<Integer, Long> studentsByCourseCount = students.stream()
            .collect(Collectors.groupingBy(s -> s.getCourses().size(), Collectors.counting()));

        Map<String, Double> averagePercentagePerCourse = students.stream()
            .flatMap(s -> s.getCourses().stream())
            .collect(Collectors.groupingBy(Course::getName, 
                Collectors.averagingDouble(c -> (30.0 / c.getLectureCount()) * 100)));

        Map<String, Map<Integer, Long>> activityByCourseAndYear = students.stream()
            .flatMap(s -> s.getCourses().stream())
            .collect(Collectors.groupingBy(Course::getName,
                Collectors.groupingBy(Course::getLastActivityYear, Collectors.counting())));

        System.out.println("Students per course: " + studentsPerCourse);
        System.out.println("Students by course count: " + studentsByCourseCount);
        System.out.println("Average percentage per course: " + averagePercentagePerCourse);
        System.out.println("Activity by course and year: " + activityByCourseAndYear);
    }
}