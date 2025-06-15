package gymSystem;

public class Member extends Person{
    private int memberId;
    private MembershipPlan membershipPlan;

    public Member(int memberId, String name, int age) {
        setName(name);
        setAge(age);
        this.memberId=memberId;
    }
    public int getMemberId() {
        return memberId;
    }
    public void assignPlan(MembershipPlan membershipPlan){
        this.membershipPlan=membershipPlan;
    }
    public MembershipPlan getPlan() {
        return membershipPlan;
    }
    public Member() {
    }
    @Override
    void showDetails() {
        System.out.println("==================\n"+"MemberId = "+memberId+
                "\nMember name = "+getName()+
                "\nMember age = "+getAge()+"\n"+
                ((membershipPlan==null)?"Plan not assigned":"Membership Details:\n"+membershipPlan));
    }
}