
public class Member extends Person {
    private int memberId;
    private MembershipPlan plan;

    public Member(int memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
        this.plan = null;
    }

    public int getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("\nMember ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            System.out.println("Assigned Plan:");
            plan.showPlanDetails();
        } else {
            System.out.println("No plan assigned.");
        }
    }
}