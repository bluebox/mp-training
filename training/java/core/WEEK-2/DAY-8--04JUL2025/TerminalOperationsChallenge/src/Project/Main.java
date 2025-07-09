package Project;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Course pymc = new Course("pymc", 50);
        Course jmc = new Course("jmc", 100);
        Course game = new Course("Creating Games in Java", 0);

        List<Course> courses = List.of(jmc, pymc, game);
        List<Student> students = Stream.generate(() -> Student.getRandomStudent(courses))
                                       .limit(5000)
                                       .collect(Collectors.toList());

        // 1. Count male and female
        Map<Student.Gender, Long> genderCount = students.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
        System.out.println("Gender Count: " + genderCount);

        // 2. Age groups
        Map<String, Long> ageGroups = students.stream().collect(Collectors.groupingBy(s -> {
            int age = s.getAge();
            if (age < 30) return "Under 30";
            else if (age <= 60) return "30 to 60";
            else return "Above 60";
        }, Collectors.counting()));
        System.out.println("Age Group Count: " + ageGroups);

        // 3. Summary statistics on age
        IntSummaryStatistics stats = students.stream().mapToInt(Student::getAge).summaryStatistics();
        System.out.println("Age Summary: " + stats);

        // 4. Distinct countries
        Set<String> countries = students.stream()
                .map(Student::getCountryCode)
                .collect(Collectors.toSet());
        System.out.println("Countries: " + countries);

        // 5. Still active and enrolled > 7 years
        boolean active7 = students.stream()
                .anyMatch(s -> s.isActive() && s.getYearsEnrolled() > 7);
        System.out.println("Active > 7 years? " + active7);

        // 6. Print 5 students
        students.stream().limit(5).forEach(System.out::println);

        // 7. Java Masterclass average %
        double javaAvg = students.stream()
                .map(s -> s.getPercentCompleteForCourse("jmc"))
                .reduce(0.0, Double::sum) / students.size();
        System.out.println("Avg Java Masterclass %: " + javaAvg);

        // 8. Collect students > 75% of 125% of avg
        double target = javaAvg * 1.25 * 0.75;
        List<Student> aboveThreshold = students.stream()
                .filter(s -> s.getPercentCompleteForCourse("jmc") > target)
                .collect(Collectors.toList());

        // 9. Sort by years enrolled and get top 10
        List<Student> top10 = aboveThreshold.stream()
                .filter(Student::isActive)
                .sorted(Comparator.comparing(Student::getYearsEnrolled).reversed())
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 students for trial:");
        top10.forEach(System.out::println);

        // 10. Add new course (not necessary in current structure, just assumed)
    }
}

