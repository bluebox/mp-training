package gymCaseStudy;

//File: Gym.java
import java.util.ArrayList;
import java.util.List;

public class Gym {
	 private List<Member> members;
	 private List<MembershipPlan> availablePlans;
	
	 public Gym() {
	     this.members = new ArrayList<>();
	     this.availablePlans = new ArrayList<>();
	     initializePlans();
	 }
	
	 private void initializePlans() {
	     availablePlans.add(new MembershipPlan("Basic", 3, 50.00));
	     availablePlans.add(new MembershipPlan("Premium", 6, 150.00));
	     availablePlans.add(new MembershipPlan("Gold", 12, 250.00));
	 }
	
	 public void addMember(Member member) {
	     members.add(member);
	     System.out.println("Member " + member.getName() + " added successfully.");
	 }
	
	 public Member findMemberById(int memberId) {
	     for (Member member : members) {
	         if (member.getMemberId() == memberId) {
	             return member;
	         }
	     }
	     return null;
	 }
	 
	 public Member findMemberByName(String name) {
	     for (Member member : members) {
	         if (member.getName().equalsIgnoreCase(name)) {
	             return member;
	         }
	     }
	     return null;
	 }
	 
	 public void displayAllMembers() {
	     if (members.isEmpty()) {
	         System.out.println("No members registered yet.");
	         return;
	     }
	     System.out.println("\n--- All Registered Members ---");
	     for (Member member : members) {
	         member.showDetails();
	     }
	 }
	
	 public List<MembershipPlan> getAvailablePlans() {
	     return availablePlans;
	 }
}