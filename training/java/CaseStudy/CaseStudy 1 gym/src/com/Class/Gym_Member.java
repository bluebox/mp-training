package com.Class;

public class Gym_Member extends Gym_Person {
    private String id;
    private Gym_MembershipPlan plan;
    

    public Gym_Member(String id, String name, int age, String gender, String email) {
        super(name, age, gender, email);
        this.id = id;
    }

    public String getId() { return id; }
    public void setPlan(Gym_MembershipPlan plan) { this.plan = plan; }
    public Gym_MembershipPlan getPlan() {
    	return plan;
    }
    @Override
    public void showDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Gender: " + getGender());
        System.out.println("Email: " + getEmail());
        if (plan != null) {
            System.out.println("Plan: " + plan);
        } else {
            System.out.println("No Plan Assigned");
        }
    }
}
