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

    public  void addMember(String memberId, String name, int age) {
        if (memberDAO.existsById(memberId)) {
            System.out.println("Member ID already exists.");
            return;
        }
        Member member = new Member(memberId, name, age);
        memberDAO.addMember(member);
        System.out.println("Member added successfully.");
    }

    public void assignPlan(String memberId, String planName) {
        Member member = memberDAO.getMemberById(memberId);
        if (member != null) {
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
        } else {
            System.out.println("Member not found.");
        }
    }

    public void viewAllMembers() {
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("\n No members found. \n");
        } else {
            members.sort(Comparator.comparing(Member::getMemberId));
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
}