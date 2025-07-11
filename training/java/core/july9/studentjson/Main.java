package dev.tulasidhar.july9.studentjson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course fsh = new Course("FSH", "Fishing tutorials");
        
        // Generate 10 random students
        List<Student> students = Stream.generate(() -> 
            Student.getRandomStudent(jmc, pymc, fsh))
            .limit(100)
            .collect(Collectors.toList());

        try (FileWriter writer = new FileWriter("students.json")) {
            writer.write("[\n");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write("  {\n");
                writer.write(String.format("    \"studentId\": %d,\n", student.getStudentId()));
                writer.write(String.format("    \"countryCode\": \"%s\",\n", student.getCountryCode()));
                writer.write(String.format("    \"yearEnrolled\": %d,\n", student.getYearEnrolled()));
                writer.write(String.format("    \"ageEnrolled\": %d,\n", student.getAgeEnrolled()));
                writer.write(String.format("    \"gender\": \"%s\",\n", student.getGender()));
                writer.write(String.format("    \"programmingExperience\": %b,\n", student.hasProgrammingExperience()));
                
                // Write course engagements
                writer.write("    \"engagements\": {\n");
                var engagements = student.getEngagementMap();
                int engCount = 0;
                for (var entry : engagements.entrySet()) {
                    writer.write(String.format("      \"%s\": {\n", entry.getKey()));
                    writer.write(String.format("        \"percentComplete\": %.2f\n", student.getPercentComplete(entry.getKey())));
                    writer.write("      }" + (engCount < engagements.size() - 1 ? "," : "") + "\n");
                    engCount++;
                }
                writer.write("    }\n");
                writer.write("  }" + (i < students.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]\n");
            
            System.out.println("Students data written to students.json");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

