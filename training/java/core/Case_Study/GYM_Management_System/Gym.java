import java.util.ArrayList;
import java.util.List;

public class Gym {
    private Register<Member> memberRegister;
    private Register<MemberShipPlan> planRegister;

    public Gym() {
        memberRegister = new Register<>();
        planRegister = new Register<>();

        planRegister.add(new MemberShipPlan("Basic", 3, 1199));
        planRegister.add(new MemberShipPlan("Premium", 6, 2799));
        planRegister.add(new MemberShipPlan("Elite", 12, 9899));

    }

    public void addMember(Member member) {
        memberRegister.add(member);
        System.out.println("Member added in Boult Gym");

    }

    public void assignPlan(String memberId, String planName) {
        try {
            Member member = memberRegister.findById(memberId);
            MemberShipPlan plan = planRegister.findById(planName);
            member.assignPlan(plan);
            System.out.println("plan assigned");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void viewAllMembers() {
        List<Member> members = memberRegister.getAll();
        if (members.isEmpty()) {
            System.out.println("members not found");
        } else {
            System.out.println("\n--- Registered Members ---");
            for (Member mem : members) {
                mem.showDetails();
                System.out.println();
            }
        }
    }

    public void viewPlans() {
        List<MemberShipPlan> allPlans = planRegister.getAll();
        for (MemberShipPlan p : allPlans) {
            p.showDetails();
        }
        System.out.println();
    }
}
