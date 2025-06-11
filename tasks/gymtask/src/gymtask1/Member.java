package gymtask1;

public class Member extends Person {
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
    
    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }
    
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }
    
    
    public boolean hasMembershipPlan() {
        return membershipPlan != null;
    }
    
    
    @Override
    public void showDetails() {
        System.out.println("=== Member Details ===");
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        
        if (hasMembershipPlan()) {
            System.out.println("Membership Plan: " + membershipPlan.toString());
        } else {
            System.out.println("Membership Plan: No plan assigned");
        }
    }
}
