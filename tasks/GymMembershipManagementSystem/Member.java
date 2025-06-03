public class Member extends Person
{

    private int memberId;
    MembershipPlan membershipPlan = null;

    public Member(int memberId, String name, int age) {
        super(age, name);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void assignPlan(String plan, int duration){
        try {
            var enumPlan = MembershipPlan.Plan.valueOf(plan);
            this.membershipPlan = new MembershipPlan(enumPlan, duration);
            System.out.println("Plan assigned successfully ");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid plan name: " + plan);
        }
    }

    public void updatePlan(String plan,int duration){
        assignPlan(plan ,duration);
        System.out.println("Plan Updated ");
    }

    public void updateDetails(String name, int age) {
        super.updateDetails(name, age);
    }

    public String toString() {
        if (membershipPlan == null) {
            return String.format("id: %-10s\t Name: %-14s\t Age: %-7d\t Plan: Not Selected\t\t   Duration: %-7s\t\t fee: %-10.2f", memberId, getName(), getAge(), "0 months", 0.0);
        }
        return String.format("id: %-10s\t Name: %-14s\t Age: %-7d\t Plan: %-11s\t\t   Duration: %-7s\t\t fee: %-10.2f",
                memberId,
                getName(),
                getAge(),
                membershipPlan.getPlan(),
                membershipPlan.getDurationMonths(),
                membershipPlan.getFee());
    }

}