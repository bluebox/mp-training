import java.util.ArrayList;

public class Gym {
    private ArrayList<Member> members;
    private ArrayList<MembershipPlan> plans;

    public Gym() {
        members = new ArrayList<>();
        plans = new ArrayList<>();

        plans.add(new MembershipPlan("Basic", 3, 100));
        plans.add(new MembershipPlan("Premium", 6, 180));
        plans.add(new MembershipPlan("Gold", 12, 300));
    }

    public void addMember(String memberId, String name, int age) {
        members.add(new Member(memberId, name, age));
        System.out.println("Member added successfully.");
    }

    public void assignPlan(String memberId, int planIndex) {
        Member member = findMemberById(memberId);
        if (member != null && planIndex >= 0 && planIndex < plans.size()) {
            member.assignPlan(plans.get(planIndex));
            System.out.println("Plan assigned successfully.");
        } else {
            System.out.println("Invalid member ID or plan ");
        }
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members ");
        } else {
            for (Member member : members) {
                member.showDetails();
            }
        }
    }

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println(i + ". " + plans.get(i).toString());
        }
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
}