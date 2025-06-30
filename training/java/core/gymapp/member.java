class Member extends Person {
    private String memberId;
    private MembershipPlan membershipPlan;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
        this.membershipPlan = null;
    }

    public String getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.membershipPlan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (membershipPlan != null) {
            System.out.println("Plan: " + membershipPlan);
        } else {
            System.out.println("Plan: Not Assigned");
        }
        System.out.println("---------------------------");
    }
}