package com.Gym_Dao;

import java.io.*;
import java.util.*;
import com.Class.Gym_Member;
import com.Class.Gym_MembershipPlan;

public class Gym_Dao {
    private static final String FILE_PATH = "gym_members.csv";

    public static void saveAllMembersToCSV(List<Gym_Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Gym_Member member : members) {
                String planInfo = (member.getPlan() != null)
                        ? member.getPlan().getPlanName() + " - " + member.getPlan().getMonths() + " months - $" + member.getPlan().getFee()
                        : "No Plan";

                String data = member.getId() + "," +
                        member.getName() + "," +
                        member.getAge() + "," +
                        member.getGender() + "," +
                        member.getEmail() + "," +
                        planInfo;

                writer.println(data);
            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static List<Gym_Member> loadMembersFromCSV() {
        List<Gym_Member> loadedMembers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 5) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String gender = parts[3];
                    String email = parts[4];

                    Gym_Member member = new Gym_Member(id, name, age, gender, email);

                    // Optional: Add plan if available
                    if (parts.length >= 6 && !parts[5].equalsIgnoreCase("No Plan")) {
                        String planPart = parts[5];
                        String[] planDetails = planPart.split(" - ");
                        if (planDetails.length == 3) {
                            String planName = planDetails[0];
                            int months = Integer.parseInt(planDetails[1].replace(" months", "").trim());
                            double fee = Double.parseDouble(planDetails[2].replace("$", "").trim());
                            member.setPlan(new Gym_MembershipPlan(planName, months, fee));
                        }
                    }

                    loadedMembers.add(member);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from CSV: " + e.getMessage());
        }

        return loadedMembers;
    }
}

