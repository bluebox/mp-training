package gymCaseStudy;


public class Member extends Person {
	
	 private int memberId;
	 private MembershipPlan membershipPlan;
	
	 public Member(int memberId, String name, int age) {
	     super(name, age);
	     this.memberId = memberId;
	     this.membershipPlan = null;
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
	     System.out.println("------------------------------------");
	     System.out.println("Member ID: " + memberId);
	     System.out.println("Name: " + getName());
	     System.out.println("Age: " + getAge());
	     if (membershipPlan != null) {
	         System.out.println("Assigned " + membershipPlan.toString());
	     } else {
	         System.out.println("Assigned Plan: No plan assigned.");
	     }
	     System.out.println("------------------------------------");
	 }

}