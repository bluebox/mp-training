import java.util.ArrayList;
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
