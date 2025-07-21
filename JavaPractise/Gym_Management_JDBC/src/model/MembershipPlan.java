package model;
public class MembershipPlan {
    private int id;
    private String name;
    private int durationMonths;
    private double fee;
    public MembershipPlan(int id, String name, int durationMonths, double fee) {
        this.id = id;
        this.name = name;
        this.durationMonths = durationMonths;
        this.fee = fee;
    }
    public String toString() {
        return "Plan ID: " + id + ", Name: " + name + ", Duration: " + durationMonths + " months, Fee: ₹" + fee;
    }
}
