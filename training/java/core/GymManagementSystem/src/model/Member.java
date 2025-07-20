package model;

public class Member extends Person {
    public int memberId;
    private MembershipPlan plan;

    public Member(int memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public void setPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    @Override
    public void showDetails() {
        System.out.println("ID: " + memberId + ", Name: " + getName() + ", Age: " + getAge());
        if (plan != null) {
            plan.showDetails();
        } else {
            System.out.println("No Membership Plan Assigned");
        }
    }
}
