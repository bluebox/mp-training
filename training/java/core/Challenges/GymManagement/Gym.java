package Challenges.GymManagement;

import java.util.ArrayList;

public class Gym {
    private ArrayList<Member> members;
    private ArrayList<MembershipPlan> plans;

    public Gym() {
        members = new ArrayList<>();
        plans = new ArrayList<>();
        loadDefaultPlans();
    }

    private void loadDefaultPlans() {
        plans.add(new MembershipPlan("Basic", 1, 30.0));
        plans.add(new MembershipPlan("Premium", 3, 75.0));
        plans.add(new MembershipPlan("Gold", 6, 120.0));
    }

    public void addMember(int id, String name, int age) {
        members.add(new Member(id, name, age));
        System.out.println("Member added successfully.");
    }

    public void assignPlan(int memberId, int planIndex) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                if (planIndex >= 0 && planIndex < plans.size()) {
                    member.setMembershipPlan(plans.get(planIndex));
                    System.out.println("Plan assigned successfully.");
                } else {
                    System.out.println("Invalid plan index.");
                }
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member member : members) {
                member.showDetails();
            }
        }
    }

    public void showAvailablePlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println("[" + i + "] " + plans.get(i).getPlanName());
        }
    }
}

