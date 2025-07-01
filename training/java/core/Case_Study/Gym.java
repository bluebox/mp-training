import java.util.*;

class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public Gym() {
        
        plans.add(new MembershipPlan("Basic", 1, 500));
        plans.add(new MembershipPlan("Premium", 3, 1200));
        plans.add(new MembershipPlan("Gold", 6, 2000));
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public ArrayList<MembershipPlan> getPlans() {
        return plans;
    }

    public Member findMemberById(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }

    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered yet.");
        } else {
            for (Member m : members) {
                m.showDetails();
                System.out.println(" ");
            }
        }
    }
}
