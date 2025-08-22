class Member extends Person {
    private String memberId;
    private MembershipPlan plan;

    public Member(String name, int age, String memberId) {
        super(name, age);
        this.memberId = memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Plan: " + (plan != null ? plan.getDetails() : "Not Assigned"));
        System.out.println("------------------------------");
    }
}