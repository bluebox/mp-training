
import java.util.ArrayList;
public class Gym {
    ArrayList<Member> members=new ArrayList<Member>();
    ArrayList<MembershipPlan> plans=new ArrayList<MembershipPlan>();
    
    public void Addmember(Member member){
        members.add(member);
        System.out.println(" successfully added member");
    }
    
    public void assignPlan(int memberid,MembershipPlan membershipPlan){
        
        Member member=new Member("", 0,0 );
        for(int i=0;i<members.size();i++){
            if(members.get(i).getMemberid()==memberid){
             member=members.get(i);
            }
        }
        if(member.getName().equals("")){
            System.out.println("No Member");
            return;
        }

        if(membershipPlan.getPlanName().equals("")){
            
            member.setMembershipPlan(membershipPlan);
            return;
            
        }
        for(int i=0;i<plans.size();i++){
            if((!plans.get(i).getPlanName().equals(membershipPlan.getPlanName()))||(plans.get(i).getDurationMonths()!=membershipPlan.getDurationMonths())||(plans.get(i).getFee()!=membershipPlan.getFee())){
                plans.add(membershipPlan);

            }
        }
        member.setMembershipPlan(membershipPlan);

        
    }
    public void show_All_Members(){
        for(int i=0;i<members.size();i++){
                System.out.println("member-name : "+members.get(i).getName());
                System.out.println("member-age : "+members.get(i).getAge());
                System.out.println("member-memberid : "+members.get(i).getMemberid());
                System.out.println("plan details :");
                members.get(i).getMembershipPlan().planDetails();
                
        }

    }

}
