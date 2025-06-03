package gym;
import java.util.ArrayList;

public class Gym {
	private ArrayList<Member> members;
	private ArrayList<Plan> plans;
	private String name;
	

	public Gym( String name) {
		super();
		this.members = new ArrayList<>();
		this.plans= new ArrayList<>();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Member> getMembers(){
		return members;
	}
	
	public ArrayList<Plan> getPlans(){
		return plans;
	}
	
	public boolean addMember(String memberName,int age, String plan, int memberId) {
		if(findMember(memberName)==null) {
			this.members.add(new Member(memberName,age,plan,memberId));
			return true;
		}
		return false;
	}
	public boolean addPlan(String planName,int durationMonths,int fee) {
		if(findPlan(planName)==null) {
			this.plans.add(new Plan(planName,durationMonths,fee));
			return true;
		}
		return false;
	}


	private Member findMember(String memberName) {
		for(int i=0;i<this.members.size();i++) {
			Member member =this.members.get(i);
			if(member.getName().equals(memberName)) {
				return member;
			}
			
		}
		return null;
	}
	private Plan findPlan(String planName) {
		for(int i=0;i<this.plans.size();i++) {
			Plan plan =this.plans.get(i);
			if(plan.getPlanName().equals(planName)) {
				return plan;
			}
			
		}
		return null;
	}
	public void showPlans() {
		System.out.println("Plans Overview : ");
		for(int i=0;i<this.plans.size();i++) {
			Plan plan =this.plans.get(i);
			String month=(plan.getDurationMonths()>1)?"months":"month";
			String discount =(plan.getDurationMonths()>1)?((plan.getDurationMonths()==12)?"(50% Discount)":"(33% Discount)"):"";
			System.out.println(plan.getPlanName()+" : "+plan.getDurationMonths()+month+" - Rs"+plan.getFee()+""+discount);
		}
			
	}
	
}
