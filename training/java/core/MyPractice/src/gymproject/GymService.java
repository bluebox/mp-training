package gymproject;
import java.util.*;

public class GymService {
    private MemberRepository memberDAO = new MemberDAO();
    private Map<String, MembershipPlan> plans = new LinkedHashMap<>();

    public GymService() {
        plans.put("basic", new MembershipPlan("basic", 1, 999));
        plans.put("premium", new MembershipPlan("premium", 3, 2499));
        plans.put("gold", new MembershipPlan("gold", 6, 4499));
    }
    public Map getPlans() {
    	return this.plans;
    }

    public  void addMember(String memberId, String name, int age) {
        Member member = new Member(memberId, name, age);
        memberDAO.addMember(member);
        System.out.println("Member with "+ memberId + " added successfully.");
    }

        public void assignPlan(String memberId, String planName) {
            Member member = memberDAO.getMemberById(memberId);
           if(member.getPlan()!=null && member.getPlan().getPlanName().toLowerCase().equals(planName.toLowerCase())){
                System.out.println("Same plan already exists ! Please try some other plan");
            }else{
                MembershipPlan plan = plans.get(planName);
                if (plan != null) {
                    member.assignPlan(plan);
                    System.out.println("Plan assigned successfully.");
                } else {
                    System.out.println("Invalid plan name.");
                }
            } 
        }
    

    public void viewAllMembers() {
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("\n No members found. \n");
        } else {
            for (Member m : members) {
                m.showDetails();
            }
        }
    }

    public void viewAllPlans() {
        System.out.println("Available Membership Plans:\n");
        for (MembershipPlan plan : plans.values()) {
            plan.showDetails();
        }
    }
    public boolean existsById(String id){
        return memberDAO.existsById(id);
    }
    public void updateMember(String memberId,String name) {
    	 Member member = memberDAO.getMemberById(memberId);
    	 member.setName(name);
    }
    public void deleteMember(String memberId) {
    	Member member = memberDAO.getMemberById(memberId);
    	memberDAO.deleteMember(member);
    }
    public void updateMembershipPlan(String id,String oldPlan,String newPlan){
        Member member = memberDAO.getMemberById(id);
        if(member.getPlan()==null) {
            System.out.println("No plan exists for this member! You can update only when you have existing plan");
            return ;
        }
        //Validate oldPlan 
        if(!member.getPlan().getPlanName().toLowerCase().equals(oldPlan.toLowerCase())){
            System.out.println("Please enter valid existing plan only.");
            return;
        }
        // Add newPlan
        MembershipPlan plan = plans.get(newPlan);
        member.assignPlan(plan);
        System.out.println("Plan Updated successfully.");
    }
    public void deleteMembershipPlan(String id,String oldPlan){
        Member member = memberDAO.getMemberById(id);
        if (member.getPlan() == null) {
            System.out.println("No plan exists for this member! You can update only when you have existing plan");
            return;
        }
        // Validate oldPlan
        if (!member.getPlan().getPlanName().toLowerCase().equals(oldPlan.toLowerCase())) {
            System.out.println("Please enter valid existing plan only.");
            return;
        }
        member.assignPlan(null);
        System.out.println("Plan removed successfully.");
    }
}