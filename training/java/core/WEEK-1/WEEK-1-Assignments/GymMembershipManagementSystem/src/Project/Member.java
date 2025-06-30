package Project;

public class Member extends Person {
    private int memberId;
    private MembershipPlan membershipPlan;

    public Member(String name, int age, int memberId) {
        super(name, age);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMembershipPlan(MembershipPlan plan) {
        this.membershipPlan = plan;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name     : " + getName());
        System.out.println("Age      : " + getAge());
        if (membershipPlan != null) {
            System.out.println("Plan     : " + membershipPlan);
        } else {
            System.out.println("Plan     : No plan assigned.");
        }
        System.out.println("-------------------------------");
    }
}
