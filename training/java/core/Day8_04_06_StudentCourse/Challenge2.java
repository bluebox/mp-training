package Day8_04_06_StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class Challenge2 {

    public static void main(String[] args) {
        Course java = new Course("JMC", "Java Masterclass", 60);
        Course python = new Course("PYMC", "Python Masterclass", 40);
        Course games = new Course("JGAME", "Java Games", 70);

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            students.add(Student.getRandomStudent(java, python));
        }

        double total = 0;
        for (Student s : students) {
            total += s.getPercentComplete("JMC");
        }
        double avg = total / students.size();
        int threshold = (int) (avg * 1.25);

        System.out.printf("Average Completion: %.2f%%\n", avg);
        System.out.println("Top Performer Threshold: " + threshold + "%");

        int given = 0;
        for (Student s : students) {
            if (s.getMonthsSinceActive("JMC") == 0 && s.getPercentComplete("JMC") >= threshold) {
                s.addCourse(games);
                System.out.println("Game Course add to student: " + s.getStudentId());
                given++;
                if (given == 10) break;
            }
        }
    }
}