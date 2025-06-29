package com.Case_Study_1;

class Member extends Person {
    private String id;
    private MembershipPlan plan;

    public Member(String id, String name, int age) {
        super(name, age);
        this.id = id;
    }

    public String getId() { return id; }
    public void setPlan(MembershipPlan plan) { this.plan = plan; }

    @Override
    public void showDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            System.out.println("Plan: " + plan.toString());
        } else {
            System.out.println("No Plan Assigned");
        }
    }
}