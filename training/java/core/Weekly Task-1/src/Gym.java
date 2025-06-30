import java.util.ArrayList;

public class Gym {
	private ArrayList<Member> member;
	private ArrayList<MembershipPlan> plans;
	
	public Gym() {
		member =new ArrayList<>();
		plans=new ArrayList<>();
		
		plans.add(new MembershipPlan("Basic" , 1,3000));
		plans.add(new MembershipPlan("Premium", 3, 8000));
		plans.add(new MembershipPlan("Gold",6, 15000));
	}
	
	public void addMember(String id, String name,int age) {
			member.add(new Member(id,name,age));
	}
	
	public void assignMembershipPlan(String memberId, int index) {
		
		Member selected=null;
		for(Member mem: member) {
			if(mem.getMemberId().equals(memberId)) {
				selected=mem;
			}
		}
		
		MembershipPlan selectedPlan=plans.get(index);
		selected.setMembershipPlan(selectedPlan);
		
		if(index<0||index>plans.size()) {
			System.out.println("Invalid plan selection ");
			return;
		}
		
		System.out.println("plan : "+selectedPlan.getPlanName()+" is assigned to "+selected.getName());
		System.out.println("-".repeat(25));
		System.out.println();
	}
	
	
	public boolean MemberExist(String id) {
		for(Member mem:member) {
			if(mem.getMemberId().equals(id))
				return true;	
		}
		return false;
	}
	public void viewAllMember() {
		if(member.size()==0) {
			System.out.println("No Data found");
		}
		for(Member memu: member) {
			memu.showDetails();
		}
	}
	
	public void viewAllPlans() {
		for (int i = 0; i < plans.size(); i++) {
            System.out.println(i + ": " + plans.get(i).toString());
        }
	}

	public ArrayList<Member> getMember() {
		return member;
	}

	public void setMember(ArrayList<Member> member) {
		this.member = member;
	}

	public ArrayList<MembershipPlan> getPlans() {
		return plans;
	}

	public void setPlans(ArrayList<MembershipPlan> plans) {
		this.plans = plans;
	}
}
