package com.gym.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.gym.classes.Member;
import com.gym.classes.MembershipPlan;


public class Gym {
    private ArrayList<Member> members;
    private static ArrayList<MembershipPlan> plans = new ArrayList<>();
    private int lastId = 0;
    
    private static final String FILE_NAME = "members.txt";

    static {
        plans.add(new MembershipPlan("Basic", 3, 5000));
        plans.add(new MembershipPlan("Premium", 6, 8000));
        plans.add(new MembershipPlan("Gold", 12, 12000));
    }

    public Gym() {
        members = new ArrayList<>();
        loadDataFromFile();
    }

    public void addNewMember(String name, int age, int height, int weight) {
        lastId += 1;
        Member mem = new Member(lastId, name, age, height, weight);
        members.add(mem);
        System.out.println("User registered!\n");
        saveDataToFile();
    }

    public void showAllMembers() {
        System.out.println("\n======================= Current Members =========================");
        for (Member x : members) {
        	x.showDetails();
        }
        System.out.println("=================================================================\n");
    }

    public void assignPlanToMember(int memberId) {
        boolean memberFound = false;
        System.out.println("Enter the plan id you want to assign:");
        for (int i = 0; i < plans.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, plans.get(i).planName);
        }

        Scanner sc = new Scanner(System.in);
        int planId = sc.nextInt() - 1;

        for (Member x : members) {
            if (x.getMemberId() == memberId) {
                x.setMemPlan(plans.get(planId));
                memberFound = true;
                System.out.println("Successfully assigned plan to user!\n");
                saveDataToFile();
            }
        }

        if (!memberFound) {
            System.out.println("Member not found. Please enter a valid id.\n");
        }
    }

    public static ArrayList<MembershipPlan> getPlans() {
        return plans;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    //File handling
    private void saveDataToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Member m : members) {
                pw.println(
                        m.getMemberId() + "," + m.getMemberName() + "," + m.getMemberAge() + "," + m.getMemberHeight() + "," + m.getMemberWeight() + "," +
                                (m.getMembershipPlan() != null ? m.getMembershipPlan().planName : "NoPlan")
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    
    private void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    int height = Integer.parseInt(parts[3]);
                    int weight = Integer.parseInt(parts[4]);
                    String planName = parts[5];

                    Member m = new Member(id, name, age, height, weight);
                    if (!planName.equals("NoPlan")) {
                        for (MembershipPlan p : plans) {
                            if (p.planName.equalsIgnoreCase(planName)) {
                                m.setMemPlan(p);
                                break;
                            }
                        }
                    }
                    members.add(m);
                    if (id > lastId) lastId = id; // Update lastId to avoid duplicate IDs
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    public void deleteMember() {
        if (members.isEmpty()) {
            System.out.println("No members to delete.\n");
            return;
        }

        // Show all members
        showAllMembers();

        // Ask for member ID
        System.out.println("Enter the ID of the member you want to delete:");
        Scanner sc = new Scanner(System.in);

        int idToDelete = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId() == idToDelete) {
                members.remove(i);
                found = true;
                System.out.println("Member deleted successfully!\n");
                saveDataToFile(); // Save after deleting
                break;
            }
        }

        if (!found) {
            System.out.println("Member with ID " + idToDelete + " not found.\n");
        }
    }
}
