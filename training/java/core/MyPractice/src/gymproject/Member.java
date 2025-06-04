package gymproject;

public class Member extends Person {
    private String memberId;
    private MembershipPlan plan=null;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
    }
    public MembershipPlan getPlan(){
        return this.plan;
    }
    public String getMemberId() {
        return memberId;
    }

    public void assignPlan(MembershipPlan plan) {
        this.plan = plan;
    }

    @Override
    public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            plan.showDetails();
        } else {
            System.out.println("No plan assigned");
        }
        System.out.println("-------------------------");
    }
}