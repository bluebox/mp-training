package GymManagement;

import Service.GymService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym_db", "root", "9490")) {
            GymService gymService = new GymService(conn);
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n=== Gym Management System ===");
                System.out.println("1. Add New Member");
                System.out.println("2. Assign Membership Plan");
                System.out.println("3. Show All Members");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        gymService.addMember(new Member(id, name, age));
                        break;
                    case 2:
                        System.out.print("Enter Member ID: ");
                        String memberId = sc.nextLine();
                        List<MembershipPlan> plans = gymService.getAllPlans();
                        System.out.println("Available Plans:");
                        for (int i = 0; i < plans.size(); i++) {
                            System.out.println(i + ": " + plans.get(i));
                        }
                        System.out.print("Enter plan index: ");
                        int index = Integer.parseInt(sc.nextLine());
                        gymService.assignPlan(memberId, index);
                        break;

                    case 3:
                        gymService.showAllMembers(); 
                        break;

                    case 4:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
