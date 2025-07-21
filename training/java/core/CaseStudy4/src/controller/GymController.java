package controller;

import service.GymService;
import model.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GymController {
    private static GymService service = new GymService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
      

        while (true) {
            System.out.println("\n=== Gym Management ===");
            System.out.println("1. Add Member\n2. View Members\n3. Delete Member\n4. Update Member\n5. Exit");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1 -> addMember();
                    case 2 -> viewMembers();
                    case 3 -> deleteMember();
                    case 4 -> updateMember();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid choice.");
                }
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addMember() throws SQLException {
        System.out.println("Enter Name:");
        String name = sc.next();
        System.out.println("Enter Age:");
        int age = sc.nextInt();
        System.out.println("Enter Phone:");
        String phone = sc.next();
        System.out.println("Enter Plan:");
        String plan = sc.next();
        GymService.registerMember(name, age, phone, plan);
        System.out.println("Member added successfully.");
    }

    private static void viewMembers() throws SQLException {
        List<Member> members = service.showAllMembers();
        for (Member m : members) {
            System.out.println("ID: " + m.getId() + ", Name: " + m.getName() +
                    ", Age: " + m.getAge() + ", Phone: " + m.getPhone() + ", Plan: " + m.getPlan());
        }
    }

    private static void deleteMember() throws SQLException {
        System.out.println("Enter Member ID to Delete:");
        int id = sc.nextInt();
        service.removeMember(id);
        System.out.println("Member deleted.");
    }

    private static void updateMember() throws SQLException {
        System.out.println("Enter ID to Update:");
        int id = sc.nextInt();
        System.out.println("Enter Name:");
        String name = sc.next();
        System.out.println("Enter Age:");
        int age = sc.nextInt();
        System.out.println("Enter Phone:");
        String phone = sc.next();
        System.out.println("Enter Plan:");
        String plan = sc.next();
        service.modifyMember(id, name, age, phone, plan);
        System.out.println("Member updated.");
    }
}