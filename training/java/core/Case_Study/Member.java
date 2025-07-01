class Member extends Person {
    private int memberId;
    private MembershipPlan membershipPlan;

    public Member(int memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public int getMemberId() { return memberId; }

    public MembershipPlan getMembershipPlan() { return membershipPlan; }

    public void assignPlan(MembershipPlan plan) {
        this.membershipPlan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("ID: " + memberId + ", Name: " + getName() + ", Age: " + getAge());
        if (membershipPlan != null) {
            System.out.println("  Membership: " + membershipPlan.toString());
        } else {
            System.out.println("  Membership: Not Assigned");
        }
    }
}
