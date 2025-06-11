package gymtask1;

import java.util.ArrayList;

public class Gym {
	private ArrayList<Member> members;
    private ArrayList<MembershipPlan> availablePlans;
    private String gymName;
    
    
    public Gym(String gymName) {
        this.gymName = gymName;
        this.members = new ArrayList<>();
        this.availablePlans = new ArrayList<>();
        initializeDefaultPlans();
    }
    
    private void initializeDefaultPlans() {
        availablePlans.add(new MembershipPlan("Basic", 1, 29.99));
        availablePlans.add(new MembershipPlan("Premium", 6, 149.99));
        availablePlans.add(new MembershipPlan("Gold", 12, 249.99));
    }
    
    public void addMember(String memberId, String name, int age) throws IllegalArgumentException {
        
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120");
        }
        
        
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                throw new IllegalArgumentException("Member ID already exists: " + memberId);
            }
        }
        
        Member newMember = new Member(memberId, name, age);
        members.add(newMember);
        System.out.println("Member added successfully: " + name);
    }
    
    public Member findMemberById(String memberId) throws IllegalArgumentException {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }
        
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
    
    public void assignMembershipPlan(String memberId, int planIndex) throws IllegalArgumentException {
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found with ID: " + memberId);
        }
        
        if (planIndex < 0 || planIndex >= availablePlans.size()) {
            throw new IllegalArgumentException("Invalid plan selection");
        }
        
        MembershipPlan selectedPlan = availablePlans.get(planIndex);
        member.setMembershipPlan(selectedPlan);
        System.out.println("Membership plan assigned successfully!");
        System.out.println("Member: " + member.getName() + " -> Plan: " + selectedPlan.getPlanName());
    }
    
    public void displayAvailablePlans() {
        System.out.println("\n=== Available Membership Plans  ===");
        for (int i = 0; i < availablePlans.size(); i++) {
            System.out.print((i + 1) + ". ");
            availablePlans.get(i).displayPlanDetails();
        }
    }
    
    
    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered yet.");
            return;
        }
        
        System.out.println("\n===  All Registered Members ===");
        for (Member member : members) {
            member.showDetails();
            System.out.println();
        }
    }
    
    public int getTotalMembers() {
        return members.size();
    }
    
    public String getGymName() {
        return gymName;
    }
    
    
    public ArrayList<MembershipPlan> getAvailablePlans() {
        return new ArrayList<>(availablePlans); 
    }
}
