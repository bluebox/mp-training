//package com.prana;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//public class WriteFile {
//
//    static class Demographics {0
//        String countryCode;
//        int enrolledMonth;
//        int enrolledYear;
//        int ageAtEnrollment;
//        String gender;
//        boolean previousProgrammingExperience;
//
//        public Demographics(String countryCode, int enrolledMonth, int enrolledYear,
//                            int ageAtEnrollment, String gender, boolean previousProgrammingExperience) {
//            this.countryCode = countryCode;
//            this.enrolledMonth = enrolledMonth;
//            this.enrolledYear = enrolledYear;
//            this.ageAtEnrollment = ageAtEnrollment;
//            this.gender = gender;
//            this.previousProgrammingExperience = previousProgrammingExperience;
//        }
//    }
//
//    static class Engagement {
//        String courseCode;
//        String engagementType;
//        int enrollmentMonth;
//        int enrollmentYear;
//
//        public Engagement(String courseCode, String engagementType, int enrollmentMonth, int enrollmentYear) {
//            this.courseCode = courseCode;
//            this.engagementType = engagementType;
//            this.enrollmentMonth = enrollmentMonth;
//            this.enrollmentYear = enrollmentYear;
//        }
//    }
//
//    static class Student {
//        int studentId;
//        Demographics demographics;
//        List<Engagement> engagement;
//
//        public Student(int studentId, Demographics demographics, List<Engagement> engagement) {
//            this.studentId = studentId;
//            this.demographics = demographics;
//            this.engagement = engagement;
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Student> students = new ArrayList<>();
//        Random rand = new Random();
//        String[] countries = {"US", "GB", "IN", "AU", "CA"};
//        String[] genders = {"M", "F", "U"};
//        String[] courses = {"JMC", "PYC", "MLA", "WEB", "DBS"};
//
//        for (int i = 1; i <= 1000; i++) {
//            Demographics demo = new Demographics(
//                countries[rand.nextInt(countries.length)],
//                rand.nextInt(12) + 1,
//                2015 + rand.nextInt(10),
//                18 + rand.nextInt(30),
//                genders[rand.nextInt(genders.length)],
//                rand.nextBoolean()
//            );
//
//            List<Engagement> engagements = new ArrayList<>();
//            for (int j = 0; j < rand.nextInt(3) + 1; j++) {
//                engagements.add(new Engagement(
//                    courses[rand.nextInt(courses.length)],
//                    "Lecture " + (rand.nextInt(20) + 1),
//                    demo.enrolledMonth,
//                    demo.enrolledYear
//                ));
//            }
//
//            students.add(new Student(i, demo, engagements));
//        }
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        try (FileWriter writer = new FileWriter("students.json")) {
//            gson.toJson(students, writer);
//            System.out.println(" JSON file created with 1000 student records.");
//        } catch (IOException e) {
//            System.err.println(" Error writing JSON file: " + e.getMessage());
//        }//l
//    }
//}