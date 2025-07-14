package TerminaloperationCh;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {
    Course jmc = new Course("jmc", 100);
    Course pymc = new Course("pymc", 50);
    Course additionaCourse = new Course("creating games in java", 40);

    public static void main(String[] args) {
        MainChallenge mc = new MainChallenge();

        List<Student> students = Stream.generate(() -> new Student(mc.jmc, mc.pymc, mc.additionaCourse))
                .limit(5000)
                .collect(Collectors.toList());

        OptionalDouble avgPercentage = students.stream()
                .mapToDouble(s -> s.getPercentComplete(mc.jmc))
                .average();

        double averagePercent = avgPercentage.orElse(0.0) * 1.25;
        System.out.println("Average percent (scaled): " + averagePercent);

        Course newCourse = new Course("new course", 30);

        List<Student> selectedStudents = students.stream()
                .sorted(Comparator.comparingInt(Student::getMinLectureAttended).reversed())
                .limit(10)
                .collect(Collectors.toList());

        selectedStudents.forEach(s -> s.addCourse(newCourse));
        selectedStudents.forEach(System.out::println);
    }
}
