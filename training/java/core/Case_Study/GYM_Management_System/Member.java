public class Member extends Person implements Identity {

    private String memberId;
    private MemberShipPlan assignedPlan;

    public Member(String memberId, String name, int age) {
        super(name, age);
        this.memberId = memberId;
        this.assignedPlan = null;
    }

    public void assignPlan(MemberShipPlan plan) {
        this.assignedPlan = plan;
    }

    public String getMemberId() {
        return memberId;

    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public MemberShipPlan getAssignedPlan() {
        return assignedPlan;
    }

    @Override
    public String getId() {
        return memberId;
    }

    @Override
    public void showDetails() {
        System.out.printf(
                "Member ID: %s | Name: %s | Age: %d%n",
                getMemberId(), getName(), getAge());
        if (assignedPlan != null) {
            System.out.print("  → Assigned Plan: ");
            assignedPlan.showDetails();
        } else {
            System.out.println("  → No membership plan assigned yet.");
        }
    }
}
