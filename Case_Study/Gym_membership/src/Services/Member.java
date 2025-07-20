package Services;


public class Member extends Person{
    private int memberid;
    private MembershipPlan plan;
    
    public Member(String name,int age,int memberid){
        super(name, age);
        this.memberid=memberid;
    }
    
   public MembershipPlan getMembershipPlan() {
       return this.plan;
   }
   public void setMembershipPlan(MembershipPlan membershipPlan) {
       this.plan = membershipPlan;
   }
    public int getMemberid() {
        return memberid;
    }
    public void show_details(){
        System.out.println("Member-name : "+this.getName());
        System.out.println("Member-age : "+this.getAge());
        System.out.println("Member-Id : "+this.memberid);

    }
}
