package models;

public class Member extends Person {
	
    private int memberId;
    private MembershipPlan membershipPlan;
    private String registerDate;

    public Member(String phone, String name, int age, int memberId) {
        super(phone, name, age);
        this.memberId = memberId;
    }
    
    public Member(String phone, String name, int age, int memberId, MembershipPlan membershipPlan, String registerDate) {
    	super(phone, name, age);
    	this.memberId = memberId;
    	this.membershipPlan=membershipPlan;
    	this.registerDate=registerDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMembershipPlan(MembershipPlan plan) {
        this.membershipPlan = plan;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }
    
    public String getRegisterDate() {
    	return this.registerDate;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name     : " + getName());
        System.out.println("Phone    : " + getPhone());
        System.out.println("Age      : " + getAge());
        if (membershipPlan != null) {
            System.out.println("Plan     : " + membershipPlan);
        } else {
            System.out.println("Plan     : No plan assigned.");
        }
        System.out.println("Date     : " + getRegisterDate());
        System.out.println("-------------------------------");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member ID: ").append(memberId).append("\n");
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Phone: ").append(getPhone()).append("\n");
        sb.append("Age: ").append(getAge()).append("\n");
        if (membershipPlan != null) {
            sb.append("Plan: ").append(membershipPlan).append("\n");
        } else {
            sb.append("Plan: No plan assigned.\n");
        }
        sb.append("Date: ").append(getRegisterDate()).append("\n");
        sb.append("-------------------------------").append("\n");
        return sb.toString();
    }
}
