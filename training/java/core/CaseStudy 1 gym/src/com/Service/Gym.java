package com.Service;

import java.util.ArrayList;
import java.util.List;
import com.Class.*;
import com.Exceptions.*;
import com.Gym_Dao.Gym_Dao;

public class Gym {
    private ArrayList<Gym_Member> members = new ArrayList<>();
    private ArrayList<Gym_MembershipPlan> plans = new ArrayList<>();

    public Gym() {
        plans.add(new Gym_MembershipPlan("Basic", 1, 29.99));
        plans.add(new Gym_MembershipPlan("Premium", 3, 79.99));
        plans.add(new Gym_MembershipPlan("Gold", 12, 299.99));

        List<Gym_Member> loaded = Gym_Dao.loadMembersFromCSV();
        if (loaded != null) {
            members.addAll(loaded);
        }
    }

    public void addMember(String id, String name, int age, String gender, String email) throws Gym_Member_Already_Exists {
        for (Gym_Member m : members) {
            if (m.getId().equalsIgnoreCase(id)) {
                throw new Gym_Member_Already_Exists("Member ID already exists!");
            }
        }
        Gym_Member newMember = new Gym_Member(id, name, age, gender, email);
        members.add(newMember);
        Gym_Dao.saveAllMembersToCSV(members); // Save after adding
        System.out.println("✓ Member added successfully.");
    }

    public void removeMember(String id) throws Gym_Member_Not_Found {
        Gym_Member m = findMember(id);
        if (m != null) {
            members.remove(m);
            Gym_Dao.saveAllMembersToCSV(members); // Save after removal
            System.out.println("✓ Member removed successfully.");
        } else {
            throw new Gym_Member_Not_Found("Member not found with ID: " + id);
        }
    }

    public void assignPlan(String id, int planIndex) throws Gym_Member_Not_Found {
        Gym_Member m = findMember(id);
        if (m != null && planIndex >= 0 && planIndex < plans.size()) {
            m.setPlan(plans.get(planIndex));
            Gym_Dao.saveAllMembersToCSV(members); // Save after assigning
            System.out.println("✓ Plan assigned to member.");
        } else {
            throw new Gym_Member_Not_Found("Invalid member ID or plan index.");
        }
    }

    public boolean upgradeMembership(String memberId, int newPlanIndex) throws Gym_Member_Not_Found {
        Gym_Member member = findMember(memberId);
        if (member == null) throw new Gym_Member_Not_Found("Member not found!");

        Gym_MembershipPlan currentPlan = member.getPlan();
        Gym_MembershipPlan newPlan = getPlan(newPlanIndex);

        if (currentPlan == null || newPlan.getMonths() > currentPlan.getMonths()) {
            member.setPlan(newPlan);
            Gym_Dao.saveAllMembersToCSV(members); // Save after upgrade
            return true;
        } else {
            return false;
        }
    }

    public Gym_Member findMember(String id) {
        for (Gym_Member m : members) {
            if (m.getId().equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    public Gym_MembershipPlan getPlan(int index) {
        if (index >= 0 && index < plans.size()) {
            return plans.get(index);
        }
        return null;
    }

    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        }
        for (Gym_Member m : members) {
            m.showDetails();
            System.out.println("---");
        }
    }

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println((i + 1) + ". " + plans.get(i));
        }
    }

    public ArrayList<Gym_Member> getAllMembers() {
        return members;
    }
}
