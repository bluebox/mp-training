package CaseStudy_Gym;

public class Member extends Person{
	private String memberId;
    private MembershipPlan plan;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public void setMembershipPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name     : " + this.getName());
        System.out.println("Age      : " + this.getAge());
        if (plan != null) {
            System.out.println("Plan     : " + plan.toString());
        } else {
            System.out.println("Plan     : No plan assigned.");
        }
    }
}
