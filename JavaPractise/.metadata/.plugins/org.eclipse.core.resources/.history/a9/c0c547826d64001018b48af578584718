
public class Member extends Person {
    private String memberId;
    private MembershipPlan membershipPlan;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }

    public String getMemberId() { 
    	return memberId; 
    }

    public MembershipPlan getMembershipPlan() {
    	return membershipPlan; 
    }
    public void setMembershipPlan(MembershipPlan plan) {
        this.membershipPlan = plan;
    }

    public void showDetails() {
        System.out.println("ID: " + memberId + ", Name: " + getName() + ", Age: " + getAge());
        if (membershipPlan != null) {
            System.out.print("Plan: ");
            membershipPlan.showPlanDetails();
        } 
        else {
            System.out.println("Plan: Not Assigned");
        }
    }
}
