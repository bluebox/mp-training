package gymManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Trainee> trainees = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Gym Membership Management System ---");
            System.out.println("1. Add Trainee");
            System.out.println("2. Add Trainer");
            System.out.println("3. Assign Trainee to Trainer");
            System.out.println("4. View Trainees");
            System.out.println("5. View Trainers");
            System.out.println("6. Update Trainee Details");
            System.out.println("7. Delete Trainee");
            System.out.println("8. List Membership Plans");
            System.out.println("9. Add Progress Record");
            System.out.println("10. View Trainee Progress History");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Trainee Name: ");
                    String traineeName = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int traineeAge = getValidAge(scanner);

                    Trainee newTrainee = new Trainee(traineeName, traineeAge);
                    System.out.println("Assign Membership Plan:");
                    for (MembershipPlan plan : MembershipPlan.values()) {
                        plan.displayPlanDetails();
                    }
                    System.out.print("Choose a plan (1/2/3): ");
                    int planChoice = scanner.nextInt();
                    MembershipPlan selectedPlan = MembershipPlan.values()[planChoice - 1];
                    newTrainee.assignPlan(selectedPlan);
                    trainees.add(newTrainee);
                    break;

                case 2:
                    System.out.print("Enter Trainer Name: ");
                    String trainerName = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int trainerAge = getValidAge(scanner);
                    System.out.print("Enter Expertise: ");
                    String expertise = scanner.nextLine();

                    Trainer newTrainer = new Trainer(trainerName, trainerAge, expertise);
                    trainers.add(newTrainer);
                    break;

                case 3:
                    System.out.print("Enter Member ID of Trainee to assign: ");
                    int traineeId = scanner.nextInt();
                    Trainee traineeToAssign = findTraineeById(trainees, traineeId);
                    if (traineeToAssign != null) {
                        System.out.print("Enter Trainer ID to assign: ");
                        int trainerId = scanner.nextInt();
                        Trainer trainerToAssign = findTrainerById(trainers, trainerId);
                        if (trainerToAssign != null) {
                            trainerToAssign.addTrainee(traineeToAssign);
                            System.out.println("Trainee assigned to Trainer successfully.");
                        } else {
                            System.out.println("Trainer not found.");
                        }
                    } else {
                        System.out.println("Trainee not found.");
                    }
                    break;

                case 4:
                    for (Trainee trainee : trainees) {
                        trainee.showDetails();
                        System.out.println();
                    }
                    break;

                case 5:
                    for (Trainer trainer : trainers) {
                        trainer.showDetails();
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.print("Enter Member ID to update: ");
                    int updateId = scanner.nextInt();
                    Trainee traineeToUpdate = findTraineeById(trainees, updateId);
                    if (traineeToUpdate != null) {
                        System.out.println("Assign new Membership Plan:");
                        for (MembershipPlan plan : MembershipPlan.values()) {
                            plan.displayPlanDetails();
                        }
                        System.out.print("Choose a new plan (1/2/3): ");
                        int newPlanChoice = scanner.nextInt();
                        MembershipPlan newSelectedPlan = MembershipPlan.values()[newPlanChoice - 1];
                        traineeToUpdate.assignPlan(newSelectedPlan);
                        System.out.println("Trainee details updated.");
                    } else {
                        System.out.println("Trainee not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Member ID to delete: ");
                    int deleteId = scanner.nextInt();
                    trainees.removeIf(trainee -> trainee.getMemberId() == deleteId);
                    System.out.println("Trainee deleted (if existed).");
                    break;

                case 8:
                    for (MembershipPlan plan : MembershipPlan.values()) {
                        plan.displayPlanDetails();
                    }
                    break;

                case 9:
                    System.out.print("Enter Member ID to add progress record: ");
                    int progressId = scanner.nextInt();
                    Trainee traineeToAddProgress = findTraineeById(trainees, progressId);
                    if (traineeToAddProgress != null) {
                        System.out.print("Enter weight (kg): ");
                        double weight = scanner.nextDouble();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = scanner.next();
                        traineeToAddProgress.addProgress(weight, date);
                        System.out.println("Progress record added.");
                    } else {
                        System.out.println("Trainee not found.");
                    }
                    break;

                case 10:
                    System.out.print("Enter Member ID to view progress history: ");
                    int historyId = scanner.nextInt();
                    Trainee traineeToViewHistory = findTraineeById(trainees, historyId);
                    if (traineeToViewHistory != null) {
                        traineeToViewHistory.showProgressHistory();
                    } else {
                        System.out.println("Trainee not found.");
                    }
                    break;

                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getValidAge(Scanner scanner) {
        int age;
        while (true) {
            System.out.print("Enter Age (must be > 0): ");
            age = scanner.nextInt();
            if (age > 0) {
                break;
            } else {
                System.out.println("Invalid age. Please enter a valid age.");
            }
        }
        return age;
    }

    private static Trainee findTraineeById(ArrayList<Trainee> trainees, int memberId) {
        for (Trainee trainee : trainees) {
            if (trainee.getMemberId() == memberId) {
                return trainee;
            }
        }
        return null; 
    }

    private static Trainer findTrainerById(ArrayList<Trainer> trainers, int trainerId) {
        for (Trainer trainer : trainers) {
            if (trainer.getTrainerId() == trainerId) {
                return trainer;
            }
        }
        return null;
    }
}
