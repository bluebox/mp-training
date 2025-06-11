package Challenges.GymManagement;

public class Member extends Person {
    private int memberId;
    private MembershipPlan membershipPlan;

    public Member(int memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (membershipPlan != null) {
            System.out.println("Membership Plan:");
            membershipPlan.showPlanDetails();
        } else {
            System.out.println("Membership Plan: Not Assigned");
        }
        System.out.println("--------------------------------");
    }
}

