public class Member extends Person {
    private String memberId;
    private MembershipPlan plan;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
        this.plan = null;
    }

    public String getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            System.out.println("Plan: " + plan.toString());
        } else {
            System.out.println("Plan: No plan assigned");
        }
        System.out.println("---------------------------");
    }
}
