import java.util.*;

public class Main {
    static List<Member> members = new ArrayList<>();
    static List<MembershipPlan> plans = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    plans.add(new MembershipPlan("Basic", 3, 1000));
    plans.add(new MembershipPlan("Premium", 6, 2500));
    plans.add(new MembershipPlan("Gold", 12, 4000));

    int choice = -1;
    do {
        try {
            System.out.println("\n***** Gym Membership Management *****");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
            case 1 : addMember(); break;
            case 2 : assignPlan(); break;
            case 3 : viewMembers(); break;
            case 4 : System.out.println("Exiting..."); break;
            default : System.out.println("Invalid choice."); break;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid input. Please enter a number (1-4).");
        }
    } while (choice != 4);
}
  static void addMember() {
      try {
          System.out.print("Enter Member ID: ");
          String id = scanner.nextLine();
          System.out.print("Enter Name: ");
          String name = scanner.nextLine();
          System.out.print("Enter Age: ");
          int age = scanner.nextInt();
          members.add(new Member(name, age, id));
          System.out.println("Member added successfully.");
      } catch (Exception e) {
          System.out.println("Invalid input. Try again.");
          scanner.nextLine(); 
      }
  }

  static void assignPlan() {
      System.out.print("Enter Member ID: ");
      String id = scanner.nextLine();
      Member member = null;
      for (Member m : members) {
          if (m.getMemberId().equalsIgnoreCase(id)) {
              member = m;
              break;
          }
      }
      if (member == null) {
          System.out.println("Member not found.");
          return;
      }

      System.out.println("Available Plans:");
      for (int i = 0; i < plans.size(); i++) {
          System.out.println((i + 1) + ". " + plans.get(i).getDetails());
      }

      System.out.print("Choose a plan number: ");
      int planChoice = scanner.nextInt();
      if (planChoice < 1 || planChoice > plans.size()) {
          System.out.println("Invalid plan.");
          return;
      }

      member.assignPlan(plans.get(planChoice - 1));
      System.out.println("Plan assigned successfully.");
  }

  static void viewMembers() {
      if (members.isEmpty()) {
          System.out.println("No members registered.");
          return;
      }
      for (Member m : members) {
          m.showDetails();
      }
  }
}
