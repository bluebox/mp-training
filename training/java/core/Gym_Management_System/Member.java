package training.java.core.Gym_Management_System;

class Member extends Person {
    private int memberId;
    private MembershipPlan plan;

    public Member(int memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("ID: " + memberId + ", Name: " + name + ", Age: " + age);
        if (plan != null) {
            System.out.println("Plan: " + plan.getDetails());
        } else {
            System.out.println("Plan: Not Assigned");
        }
    }
}
