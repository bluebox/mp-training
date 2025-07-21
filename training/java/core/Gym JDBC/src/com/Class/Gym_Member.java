package com.Class;

import java.time.LocalDate;

public class Gym_Member extends Gym_Person {
    private String id;
    private String membershipPlan;
    private LocalDate joiningDate;
    private LocalDate updatedDate;

    public Gym_Member(String id, String name, int age, String gender, String email,
                      String membershipPlan, LocalDate joiningDate, LocalDate updatedDate) {
        super(name, age, gender, email);  // call Gym_Person constructor
        this.id = id;
        this.membershipPlan = membershipPlan;
        this.joiningDate = joiningDate;
        this.updatedDate = updatedDate;
    }

    // Overloaded constructor: updatedDate = joiningDate
    public Gym_Member(String id, String name, int age, String gender, String email,
                      String membershipPlan, LocalDate joiningDate) {
        this(id, name, age, gender, email, membershipPlan, joiningDate, joiningDate);
    }

    public String getId() { return id; }
    public String getMembershipPlan() { return membershipPlan; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public void showDetails() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"
             + "Name: " + getName() + "\n"
             + "Age: " + getAge() + "\n"
             + "Gender: " + getGender() + "\n"
             + "Email: " + getEmail() + "\n"
             + "Plan: " + membershipPlan + "\n"
             + "Joined: " + joiningDate + "\n"
             + "Last Updated: " + updatedDate;
    }
}
