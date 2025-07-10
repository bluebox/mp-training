package Day10_09_07_Jsonchallenge;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Day8_04_06_StudentCourse.Course;
import Day8_04_06_StudentCourse.Student;

public class JsonChallenge {
    public static void main(String[] args) {
        Course javaCourse = new Course("JMC", "Java Masterclass");
        Course pythonCourse = new Course("PYMC", "Python Masterclass");
        Course fishingCourse = new Course("FSH", "Fishing Tutorials");
        List<Student> studentList = Stream.generate(() ->
                Student.getRandomStudent(javaCourse, pythonCourse, fishingCourse))
                .limit(100)
                .collect(Collectors.toList());
        try (FileWriter fileWriter = new FileWriter("students.json")) {
            fileWriter.write("[\n");
            for (int i = 0; i < studentList.size(); i++) {
                Student s = studentList.get(i);
                fileWriter.write("  {\n");
                fileWriter.write(String.format("    \"studentId\": %d,\n", s.getStudentId()));
                fileWriter.write(String.format("    \"countryCode\": \"%s\",\n", s.getCountryCode()));
                fileWriter.write(String.format("    \"yearEnrolled\": %d,\n", s.getYearEnrolled()));
                fileWriter.write(String.format("    \"ageEnrolled\": %d,\n", s.getAgeEnrolled()));
                fileWriter.write(String.format("    \"gender\": \"%s\",\n", s.getGender()));
                fileWriter.write(String.format("    \"programmingExperience\": %b,\n", s.hasProgrammingExperience()));
                fileWriter.write("    \"engagements\": {\n");
                var courseMap = s.getEngagementMap();
                int count = 0;
                for (var entry : courseMap.entrySet()) {
                    fileWriter.write(String.format("      \"%s\": {\n", entry.getKey()));
                    fileWriter.write(String.format("        \"percentComplete\": %.2f\n", s.getPercentComplete(entry.getKey())));
                    fileWriter.write("      }" + (count < courseMap.size() - 1 ? "," : "") + "\n");
                    count++;
                }
                fileWriter.write("    }\n");
                fileWriter.write("  }" + (i < studentList.size() - 1 ? "," : "") + "\n");
            }

            fileWriter.write("]\n");
            System.out.println("Successfully wrote student data to students.json");
        } catch (IOException ex) {
            System.err.println("Failed to write to file: " + ex.getMessage());
        }
    }
}
