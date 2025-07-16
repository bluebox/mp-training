package corejava.july4_studentsandcourses;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {
    Course javaMasterClass = new Course("javaMasterClass", 100);
    Course pythonMasterClass = new Course("pythonMasterClass", 50);
    Course additionaCourse = new Course("Creating games in java");

    public static void main(String[] args) {
        MainChallenge mc = new MainChallenge();

        List<Student> students = Stream.generate(() -> new Student(mc.javaMasterClass, mc.pythonMasterClass, mc.additionaCourse))
                .limit(5000)
                .collect(Collectors.toList());
        Double sumCompleted = students.stream()
                .mapToDouble(s -> s.getPercentComplete(mc.javaMasterClass))
                .reduce(0.0,Double::sum);
        Double avgPercentageCompleted=sumCompleted/5000;
        double averagePercent = avgPercentageCompleted * 1.25;
        
        System.out.println("Average percent (scaled): " + averagePercent);

        Course newCourse = new Course("new course", 30);

        //students.forEach(System.out::println);
       // System.out.println("----------------------------------");
        List<Student> selectedStudents = students.stream()
                .sorted(Comparator.comparingInt(Student::getMinLectureAttended).reversed())
                .limit(10)
                .collect(Collectors.toList());

        selectedStudents.forEach(s -> s.addCourse(newCourse));
        selectedStudents.forEach(System.out::println);
    }
}
