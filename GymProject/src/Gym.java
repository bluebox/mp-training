
import java.util.ArrayList;
import java.util.List;

public class Gym {
    private List<Member> members;
    private List<MembershipPlan> plans;

    public Gym() {
        members = new ArrayList<>();
        plans = new ArrayList<>();

        plans.add(new MembershipPlan("Basic", 1, 400.0));
        plans.add(new MembershipPlan("Premium", 3, 1000.0));
        plans.add(new MembershipPlan("Gold", 6, 1600.0));
    }

    public void addMember(int memberId, String name, int age) {
        members.add(new Member(memberId, name, age));
        System.out.println("Member added successfully.");
    }

    public void assignPlan(int memberId, int planIndex) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member ID not found.");
            return;
        }
        if (planIndex < 1 || planIndex > plans.size()) {
            System.out.println("Invalid plan index.");
            return;
        }
        member.assignPlan(plans.get(planIndex - 1));
        System.out.println("Plan assigned successfully.");
    }

    public Member findMemberById(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) {
                return m;
            }
        }
        return null;
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member m : members) {
            m.showDetails();
        }
    }

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.print((i + 1) + ". ");
            plans.get(i).showPlanDetails();
        }
    }
}