package studentEngagementCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int NUM_STUDENTS = 1000;
    private static final Random random = new Random();

    private static final String[] COUNTRY_CODES = {"GB", "US", "CA", "AU", "DE", "FR", "IN"};
    private static final String[] GENDERS = {"M", "F", "O"};
    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private static final String[] COURSE_CODES = {"JVC", "PYC", "CSC", "WEB"};
    private static final String[] COURSE_NAMES = {"Java Masterclass", "Python Bootcamp", "C# Fundamentals", "Web Development"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Student Data Generator ---");
        System.out.println("Select JSON output format:");
        System.out.println("1. Flattened Structure (students_flattened.json)");
        System.out.println("2. Hierarchical Structure (students_hierarchical.json)");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter 1 or 2.");
            scanner.close();
            return;
        } finally {
            scanner.nextLine();         }


        switch (choice) {
            case 1:
                generateFlattenedData();
                break;
            case 2:
                generateHierarchicalData();
                break;
            default:
                System.out.println("Invalid choice. Please run the program again and enter 1 or 2.");
        }

        scanner.close();
    }

    private static void generateFlattenedData() {
        final String FILE_NAME = "students_flattened.json";
        System.out.println("\nStarting Student Data Generation (Flattened JSON)...");
        System.out.println("Generating " + NUM_STUDENTS + " student records.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");

            for (int i = 0; i < NUM_STUDENTS; i++) {
                int studentId = i + 1;
                String countryCode = COUNTRY_CODES[random.nextInt(COUNTRY_CODES.length)];
                int enrolledYear = 2020 + random.nextInt(5);
                String enrolledMonth = MONTHS[random.nextInt(MONTHS.length)];
                String gender = GENDERS[random.nextInt(GENDERS.length)];
                boolean previousProgrammingExperience = random.nextBoolean();

                String studentJson = String.format(
                        "  {\n" +
                        "    \"studentId\": %d,\n" +
                        "    \"countryCode\": \"%s\",\n" +
                        "    \"enrolledYear\": %d,\n" +
                        "    \"enrolledMonth\": \"%s\",\n" +
                        "    \"gender\": \"%s\",\n" +
                        "    \"previousProgrammingExperience\": %b\n" +
                        "  }",
                        studentId, countryCode, enrolledYear, enrolledMonth, gender, previousProgrammingExperience
                );

                writer.write(studentJson);

                if (i < NUM_STUDENTS - 1) {
                    writer.write(",\n");
                } else {
                    writer.write("\n");
                }
            }

            writer.write("]\n");
            System.out.println("Successfully generated " + NUM_STUDENTS + " student records to " + FILE_NAME);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void generateHierarchicalData() {
        final String FILE_NAME = "students_hierarchical.json";
        System.out.println("\nStarting Student Data Generation (Hierarchical JSON)...");
        System.out.println("Generating " + NUM_STUDENTS + " student records.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");

            for (int i = 0; i < NUM_STUDENTS; i++) {
                int studentId = i + 1;
                String countryCode = COUNTRY_CODES[random.nextInt(COUNTRY_CODES.length)];
                int demographicsEnrolledYear = 2020 + random.nextInt(5);
                String demographicsEnrolledMonth = MONTHS[random.nextInt(MONTHS.length)];
                String gender = GENDERS[random.nextInt(GENDERS.length)];
                boolean previousProgrammingExperience = random.nextBoolean();

                int numEngagements = 1 + random.nextInt(3);
                StringBuilder engagementArrayBuilder = new StringBuilder();
                for (int j = 0; j < numEngagements; j++) {
                    String courseCode = COURSE_CODES[random.nextInt(COURSE_CODES.length)];
                    String courseName = COURSE_NAMES[random.nextInt(COURSE_NAMES.length)];
                    int lecture = 1 + random.nextInt(20);
                    String engagementMonth = MONTHS[random.nextInt(MONTHS.length)];
                    int engagementYear = 2023 + random.nextInt(2);

                    engagementArrayBuilder.append(String.format(
                            "      {\n" +
                            "        \"courseCode\": \"%s\",\n" +
                            "        \"courseName\": \"%s\",\n" +
                            "        \"lecture\": \"%s\",\n" +
                            "        \"enrollmentMonth\": \"%s\",\n" +
                            "        \"enrollmentYear\": %d\n" +
                            "      }",
                            courseCode, courseName, "Lecture " + lecture, engagementMonth, engagementYear
                    ));

                    if (j < numEngagements - 1) {
                        engagementArrayBuilder.append(",\n");
                    } else {
                        engagementArrayBuilder.append("\n");
                    }
                }

                String studentJson = String.format(
                        "  {\n" +
                        "    \"studentId\": %d,\n" +
                        "    \"demographics\": {\n" +
                        "      \"countryCode\": \"%s\",\n" +
                        "      \"enrolledYear\": %d,\n" +
                        "      \"enrolledMonth\": \"%s\",\n" +
                        "      \"gender\": \"%s\",\n" +
                        "      \"previousProgrammingExperience\": %b\n" +
                        "    },\n" +
                        "    \"engagement\": [\n" +
                        "%s" +
                        "    ]\n" +
                        "  }",
                        studentId, countryCode, demographicsEnrolledYear, demographicsEnrolledMonth, gender, previousProgrammingExperience,
                        engagementArrayBuilder.toString()
                );

                writer.write(studentJson);

                if (i < NUM_STUDENTS - 1) {
                    writer.write(",\n");
                } else {
                    writer.write("\n");
                }
            }

            writer.write("]\n");
            System.out.println("Successfully generated " + NUM_STUDENTS + " student records to " + FILE_NAME);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}