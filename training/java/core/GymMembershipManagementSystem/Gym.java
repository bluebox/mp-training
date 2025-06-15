package gymSystem;

import java.util.ArrayList;
public class Gym {
        ArrayList<Member> memberList=new ArrayList<>();
        ArrayList<MembershipPlan> membershipPlans=new ArrayList<>();
        public Gym() {
            membershipPlans.add((new MembershipPlan("Basic", 3, 3000)));
            membershipPlans.add(new MembershipPlan("Premium", 6, 6000));
            membershipPlans.add(new MembershipPlan("Gold", 9, 9000));
        }
        public void addNewMember(Member member){
            memberList.add(member);
        }
        MembershipPlan mPlan=null;
        public MembershipPlan getPlanByName(String plan){
            for(MembershipPlan mPlan:membershipPlans){
                if(plan.equalsIgnoreCase(mPlan.getPlan())){
                    return mPlan;
                }
            }
            return mPlan;
        }

        public boolean getPlanById(int id){
            for(Member mPlan:memberList){
                if(id==(mPlan.getMemberId())){
                    if(new MembershipPlan().getPlan()==null){
                        return true;
                    }
                }
            }
            return false;
        }
        public void viewMembers(){
            for(Member member:memberList){
                member.showDetails();
            }
        }
    public boolean addPlan(int id,MembershipPlan plan) {
        for (Member member : memberList) {
            if (member.getMemberId()==id) {
                if (member.getPlan() == null) {
                    member.assignPlan(plan);
                    return true;
                } else if(member.getPlan()!=plan) {
                    member.assignPlan(plan);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    public boolean idExistsOrNot(int id){
        for(Member member:memberList){
            if(id==member.getMemberId()){
                return true;
            }
        }
        return false;
        }
    public boolean removeMember(int id){
            memberList.removeIf(member -> member.getMemberId() == (id));
            return true;
    }
}

	
