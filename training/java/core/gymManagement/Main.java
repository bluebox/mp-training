package gymManagement;

import java.util.*;

public class Main {
    public static List<Trainee> trainees = new ArrayList<>();
    public static List<Trainer> trainers = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Gym Management System ===");
        Trainer trainer = new Trainer("Surya", 22, "fat burn");
        trainers.add(trainer);

        while (true) {
            System.out.println("\nLogin as:\n1. Trainee\n2. Trainer\n3. Exit");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> traineeLogin();
                case 2 -> trainerLogin();
                case 3 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static Trainee findTraineeById(int id) {
        for (Trainee t : trainees) {
            if (t.getMemberId() == id) return t;
        }
        return null;
    }

    private static Trainer findTrainerById(int id) {
        for (Trainer tr : trainers) {
            if (tr.getTrainerId() == id) return tr;
        }
        return null;
    }

    private static void traineeLogin() {
        System.out.print("Enter Trainee ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Trainee t = findTraineeById(id);

        if (t != null) {
            while (true) {
                System.out.println("\n1. View Details\n2. Add Progress\n3. View Progress History\n4. Assign/Update Membership Plan\n5. Logout");
                int ch = Integer.parseInt(sc.nextLine());
                switch (ch) {
                    case 1 -> t.showDetails();
                    case 2 -> {
                        System.out.print("Enter weight: ");
                        double weight = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter time stamp: ");
                        String date = sc.nextLine();
                        t.addProgress(weight, date);
                    }
                    case 3 -> t.showProgressHistory();
                    case 4 -> assignMembershipPlan(t);
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Trainee not found.");
        }
    }

    private static void trainerLogin() {
        System.out.print("Enter Trainer ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Trainer trainer = findTrainerById(id);

        if (trainer == null) {
            System.out.println("Trainer not found.");
            return;
        }

        while (true) {
            System.out.println("\n1. Add Trainee\n2. Show Trainees\n3. Assign Plan to Trainee\n4. View Details\n5. Logout");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> {
                    System.out.print("Enter Trainee name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    System.out.print("Assign to specific trainers? (yes/no): ");
                    String ans = sc.nextLine();

                    Trainee trainee;
                    if (ans.equalsIgnoreCase("yes")) {
                        System.out.print("Enter trainer names: ");
                        String[] names = sc.nextLine().split(" ");
                        for (int i = 0; i < names.length; i++) names[i] = names[i].trim();
                        trainee = new Trainee(name, age, names);
                    } else {
                        trainee = new Trainee(name, age);
                        trainer.assignTrainee(trainee);
                    }

                    trainees.add(trainee);
                    System.out.println("Trainee added with ID: " + trainee.getMemberId());
                }
                case 2 -> trainer.viewTrainees();
                case 3 -> {
                    System.out.print("Enter Trainee ID to assign plan: ");
                    int tid = Integer.parseInt(sc.nextLine());
                    Trainee t = findTraineeById(tid);
                    if (t != null) assignMembershipPlan(t);
                    else System.out.println("Trainee not found.");
                }
                case 4 -> trainer.showDetails();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void assignMembershipPlan(Trainee t) {
        System.out.println("Available Plans: ");
        for (MembershipPlan plan : MembershipPlan.values()) {
            System.out.println("- " + plan.name());
        }
        System.out.print("Enter plan name to assign: ");
        String selected = sc.nextLine().toUpperCase();
        try {
            MembershipPlan plan = MembershipPlan.valueOf(selected);
            t.assignPlan(plan);
            System.out.println("Plan assigned successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid plan name.");
        }
    }
}
