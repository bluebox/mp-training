package Student;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsChallenge {
    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("JMC", "Java Masterclass", 100),
                new Course("PYMC", "Python Masterclass", 60),
                new Course("DS", "Data Structures", 45),
                new Course("AI", "AI with Python", 55)
        );

        Supplier<Student> supplier = () -> Student.getRandomStudent(courses);
        List<Student> students = Stream.generate(supplier)
                .limit(10000)
                .collect(Collectors.toList());

        Map<String, Long> enrolledPerCourse = students.stream()
                .flatMap(s -> s.getEngagements().keySet().stream())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        System.out.println("Students per course:");
        enrolledPerCourse.forEach((k, v) -> System.out.println(k + " = " + v));

        Map<Integer, Long> courseBuckets = students.stream()
                .collect(Collectors.groupingBy(s -> s.getEngagements().size(), Collectors.counting()));

               }
}