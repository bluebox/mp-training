package Default;

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

    @Override
    public void showDetails() {
        System.out.println("ID: " + getMemberId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            plan.showDetails();
        } else {
            System.out.println("No Membership Plan Assigned. Add a new Member.");
        }
    }
}
