package com.Case_Study_1;

import java.util.ArrayList;

class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public Gym() {
        plans.add(new MembershipPlan("Basic", 1, 29.99));
        plans.add(new MembershipPlan("Premium", 6, 79.99));
        plans.add(new MembershipPlan("Gold", 12, 299.99));
    }

    public void addMember(String id, String name, int age) {
        for (Member m : members) {
            if (m.getId().equals(id)) {
                System.out.println("Member already exists!");
                return;
            }
        }
        members.add(new Member(id, name, age));
        System.out.println("Member added!");
    }

    public void assignPlan(String id, int planIndex) {
        Member m = findMember(id);
        if (m != null && planIndex >= 0 && planIndex < plans.size()) {
            m.setPlan(plans.get(planIndex));
            System.out.println("Plan assigned.");
        } else {
            System.out.println("Error: Member not found or plan invalid");
        }
    }

    private Member findMember(String id) {
        for (Member m : members) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members yet.");
        }
        for (Member m : members) {
            m.showDetails();
            System.out.println("---");
        }
    }

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println((i + 1) + ". " + plans.get(i));
        }
    }
}
