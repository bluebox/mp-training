import java.util.ArrayList;


public class Gym {
	private ArrayList<Member> members;
    private ArrayList<MembershipPlan> plans;

    public Gym() {
        members = new ArrayList<>();
        plans = new ArrayList<>();
        plans.add(new MembershipPlan("Basic", 1, 3000));
        plans.add(new MembershipPlan("Silver", 3, 6000));
        plans.add(new MembershipPlan("Premium", 6, 9000 ));
        plans.add(new MembershipPlan("Gold", 12, 14000));
    }

    public void addMember(int memberId, String name, int age) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age. Age must be between 1 and 120.");
        }
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                throw new IllegalArgumentException("Member ID " + memberId + " already exists.");
            }
        }
        Member newMember = new Member(memberId, name, age);
        members.add(newMember);
        System.out.println("Member added successfully!");
    }

    public void assignPlanToMember(int memberId, String planName) throws IllegalArgumentException {
        Member member = findMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
        MembershipPlan plan = findPlan(planName);
        if (plan == null) {
            throw new IllegalArgumentException("Plan " + planName + " not found.");
        }
        member.assignPlan(plan);
        System.out.println("Plan " + planName + " assigned to member ID " + memberId);
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\n--- Registered Members ---");
        for (Member member : members) {
            member.showDetails();
            System.out.println("-------------------");
        }
    }

    private Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private MembershipPlan findPlan(String planName) {
        for (MembershipPlan plan : plans) {
            if (plan.getPlanName().equalsIgnoreCase(planName)) {
                return plan;
            }
        }
        return null;
    }

    public void displayAvailablePlans() {
        System.out.println("\n--- Available Plans ---");
        for (MembershipPlan plan : plans) {
            System.out.println("Plan: " + plan.getPlanName() +
                    ", Duration: " + plan.getDurationMonths() +
                    " months, Fee: " + plan.getFee());
        }
    }

}
