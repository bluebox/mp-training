import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Course {
    private String name;
    private int lectureCount;

    public Course(String name, int lectureCount) {
        this.name = name;
        this.lectureCount = lectureCount;
    }

    public String getName() { return name; }
    public int getLectureCount() { return lectureCount; }
}

class Student {
    private String name;
    private String gender;
    private int age;
    private String countryCode;
    private boolean isActive;
    private int yearsEnrolled;
    private List<Course> courses;

    public Student(String name, String gender, int age, String countryCode, boolean isActive, int yearsEnrolled, List<Course> courses) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.countryCode = countryCode;
        this.isActive = isActive;
        this.yearsEnrolled = yearsEnrolled;
        this.courses = courses;
    }

    public static Student getRandomStudent(Supplier<List<Course>> coursesSupplier) {
        Random random = new Random();
        String name = "Student" + random.nextInt(10000);
        String gender = random.nextBoolean() ? "Male" : "Female";
        int age = 18 + random.nextInt(50);
        String countryCode = new String[]{"US", "UK", "CA", "AU", "IN"}[random.nextInt(5)];
        boolean isActive = random.nextBoolean();
        int yearsEnrolled = 1 + random.nextInt(10);
        List<Course> courses = coursesSupplier.get();
        return new Student(name, gender, age, countryCode, isActive, yearsEnrolled, courses);
    }

    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getCountryCode() { return countryCode; }
    public boolean isActive() { return isActive; }
    public int getYearsEnrolled() { return yearsEnrolled; }
    public List<Course> getCourses() { return courses; }
    public double getPercentComplete(String courseName) {
        return courses.stream()
            .filter(c -> c.getName().equals(courseName))
            .findFirst()
            .map(c -> (30.0 / c.getLectureCount()) * 100)
            .orElse(0.0);
    }
}

public class MainChallenge {
    public static void main(String[] args) {
        Course jmc = new Course("Java Masterclass", 100);
        Course pymc = new Course("Python Masterclass", 50);
        Course games = new Course("Creating Games in Java", 40);

        Supplier<List<Course>> coursesSupplier = () -> {
            Random random = new Random();
            List<Course> allCourses = Arrays.asList(jmc, pymc, games);
            Collections.shuffle(allCourses);
            return allCourses.subList(0, 1 + random.nextInt(2));
        };

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(coursesSupplier))
            .limit(5000)
            .collect(Collectors.toList());

        Map<String, Long> genderCount = students.stream()
            .collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));

        Map<String, Long> ageGroups = students.stream()
            .collect(Collectors.groupingBy(
                s -> s.getAge() < 30 ? "Under 30" : s.getAge() <= 60 ? "30-60" : "Over 60",
                Collectors.counting()));

        IntSummaryStatistics ageStats = students.stream()
            .mapToInt(Student::getAge)
            .summaryStatistics();

        List<String> countries = students.stream()
            .map(Student::getCountryCode)
            .distinct()
            .collect(Collectors.toList());

        boolean hasLongTermActiveStudents = students.stream()
            .anyMatch(s -> s.isActive() && s.getYearsEnrolled() > 7);

        List<Student> selectedStudents = students.stream()
            .filter(s -> s.isActive() && s.getYearsEnrolled() > 7)
            .limit(5)
            .collect(Collectors.toList());

        double avgJmcCompletion = students.stream()
            .mapToDouble(s -> s.getPercentComplete("Java Masterclass"))
            .average()
            .orElse(0);

        System.out.println("Gender count: " + genderCount);
        System.out.println("Age groups: " + ageGroups);
        System.out.println("Age stats: " + ageStats);
        System.out.println("Countries: " + countries);
        System.out.println("Has long term active students: " + hasLongTermActiveStudents);
        System.out.println("Selected students: ");
        selectedStudents.forEach(s -> System.out.println(s.getName()));
        System.out.println("Average JMC completion: " + avgJmcCompletion);
    }
}