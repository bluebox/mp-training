import java.util.*;
abstract class Person {
    private String name;
    private int age;



    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void showDetails();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


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

    @Override
    public String toString() {
        return planName + " - " + durationMonths + " months - ₹" + fee;
    }
}


class Member extends Person {
    private String memberId;
    private MembershipPlan membershipPlan;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
        this.membershipPlan = null;
    }

    public String getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.membershipPlan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (membershipPlan != null) {
            System.out.println("Plan: " + membershipPlan);
        } else {
            System.out.println("Plan: Not Assigned");
        }
        System.out.println("---------------------------");
    }
}


class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public Gym() {
        plans.add(new MembershipPlan("Basic", 3, 1500));
        plans.add(new MembershipPlan("Premium", 6, 3000));
        plans.add(new MembershipPlan("Gold", 12, 5000));
    }

    public void addMember(Member m) {
        members.add(m);
        System.out.println("Member added successfully.");
    }

    public void assignPlanToMember(String memberId, int planIndex) {
        for (Member m : members) {
            if (m.getMemberId().equalsIgnoreCase(memberId)) {
                if (planIndex >= 0 && planIndex < plans.size()) {
                    m.assignPlan(plans.get(planIndex));
                    System.out.println("Plan assigned successfully.");
                } else {
                    System.out.println("Invalid plan index.");
                }
                return;
            }
        }
        System.out.println("Member not found.");
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

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println(i + ": " + plans.get(i));
        }
    }
}


public class GymApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        int choice;

        do {
            System.out.println("\n--- Gym Membership Management ---");
            System.out.println("1. Add New Gym Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Member ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        gym.addMember(new Member(id, name, age));
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    String memberId = sc.nextLine();
                    System.out.println("Available Plans:");
                    gym.showPlans();
                    System.out.print("Select plan index: ");
                    try {
                        int planIndex = Integer.parseInt(sc.nextLine());
                        gym.assignPlanToMember(memberId, planIndex);
                    } catch (Exception e) {
                        System.out.println("Invalid plan selection.");
                    }
                    break;
                case 3:
                    gym.showAllMembers();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}
