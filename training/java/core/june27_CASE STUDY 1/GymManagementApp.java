package June27_CaseStudy;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Abstract class for common person details
abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void showDetails();
}

// Membership Plan class
class MembershipPlan {
    private String planName;
    private int durationMonths;
    private double fee;

    public MembershipPlan(String planName, int durationMonths, double fee) {
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.fee = fee;
    }

    public String getPlanName() {
        return planName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getFee() {
        return fee;
    }

    public void showPlanDetails() {
        System.out.println("Plan Name: " + planName + ", Duration: " + durationMonths + " months, Fee: ₹" + fee);
    }
}

// Member class extends Person
class Member extends Person {
    private int memberId;
    private MembershipPlan plan;

    public Member(int memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId + ", Name: " + getName() + ", Age: " + getAge());
        if (plan != null) {
            System.out.print("Assigned Plan: ");
            plan.showPlanDetails();
        } else {
            System.out.println("No Membership Plan Assigned");
        }
        System.out.println("------------------------------------");
    }
}

// Gym class to manage members and plans
class Gym {
    private ArrayList<Member> members;
    private ArrayList<MembershipPlan> plans;

    public Gym() {
        members = new ArrayList<>();
        plans = new ArrayList<>();
        initializePlans();
    }

    private void initializePlans() {
        plans.add(new MembershipPlan("Basic", 3, 1500));
        plans.add(new MembershipPlan("Premium", 6, 2700));
        plans.add(new MembershipPlan("Gold", 12, 4800));
    }

    public void addMember(int id, String name, int age) {
        members.add(new Member(id, name, age));
        System.out.println("Member added successfully.");
    }

    public void assignPlanToMember(int memberId, int planChoice) {
        Member selectedMember = null;
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                selectedMember = m;
                break;
            }
        }
        if (selectedMember == null) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }
        if (planChoice >= 1 && planChoice <= plans.size()) {
            selectedMember.assignPlan(plans.get(planChoice - 1));
            System.out.println("Plan assigned successfully.");
        } else {
            System.out.println("Invalid plan choice.");
        }
    }

    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered yet.");
        } else {
            for (Member m : members) {
                m.showDetails();
            }
        }
    }

    public void showAvailablePlans() {
        System.out.println("Available Membership Plans:");
        for (int i = 0; i < plans.size(); i++) {
            System.out.print((i + 1) + ". ");
            plans.get(i).showPlanDetails();
        }
    }
}

// Main application class
public class GymManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        int memberIdCounter = 1;

        while (true) {
            System.out.println("\n===== Gym Membership Management System =====");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        gym.addMember(memberIdCounter++, name, age);
                        break;

                    case 2:
                        gym.showAvailablePlans();
                        System.out.print("Enter Member ID to assign plan: ");
                        int memberId = sc.nextInt();
                        System.out.print("Select Plan (enter number): ");
                        int planChoice = sc.nextInt();
                        gym.assignPlanToMember(memberId, planChoice);
                        break;

                    case 3:
                        gym.showAllMembers();
                        break;

                    case 4:
                        System.out.println("Exiting the system. Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values only.");
                sc.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                sc.nextLine(); // Clear input buffer
            }
        }
    }
}
