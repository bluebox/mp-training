package gym;
import java.util.ArrayList;


public class Gym  extends Main{
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
				return member; //returns the first member with the given Name if multiple members have same name
			}
			
		}
		return null;
	}
	
	private Member findMember(int memberId) {  //Method Overloading(Polymorphism)
		for(int i=0;i<this.members.size();i++) {
			Member member =this.members.get(i);
			String memberid=String.valueOf(memberId);
			String gotMemberId=String.valueOf(member.getMemberId());
			if(gotMemberId.equals(memberid)) {
				return member; //returns the first member with the given Name if multiple members have same name
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
	public boolean listAllMembersDetails() {
		ArrayList<Member> allMembers = getMembers();
		System.out.println("                              Details of all Members in the Gym  : ");
		for(int i=0;i<allMembers.size();i++) {
			Member member = allMembers.get(i);
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.println(" Member Name : "+ member.getName()+"\n Age         : "+member.getAge()+"\n Member Id   : "+member.getMemberId()+"\n Plan Chosen : "+member.getPlan());
			System.out.println("--------------------------------------------------------------------------------------------------------------\n");
			wait(1000);
		}
		return true;
	}
	public void showPlans() {
		System.out.println("--------------------------------------------");
		System.out.println("             Plans Overview :              |");           
		System.out.println("--------------------------------------------");
		System.out.println(" Name    | Duration |  Total cost          |");      
		System.out.println("--------------------------------------------");
		for(int i=0;i<this.plans.size();i++) {
			Plan plan =this.plans.get(i);
			String month=(plan.getDurationMonths()>1)?"months":"month  ";
			String discount =(plan.getDurationMonths()>1)?((plan.getDurationMonths()==12)?"(50% Discount) |":"(33% Discount)  |"):"               |";
			System.out.println(" "+plan.getPlanName()+" | "+plan.getDurationMonths()+month+" | Rs"+plan.getFee()+""+discount);
		}
		System.out.println("--------------------------------------------");
		System.out.print("\n");
			
	}
	public boolean removeMember(String memberName) {
		Member m1=findMember(memberName);
		if(m1==null) {
			return false;
		}
		else {
			members.remove(m1);
			return true;
		}
		
	}
	
	public boolean listMemberDetailsById(int memberId) {
		Member m1=findMember(memberId);
		if(m1==null) {
			System.out.println("Member with given Id does not exist.");
			return false;
		}else {
			System.out.println("\n\nDetails of the Member are : ");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.println(" Member Id   : "+ m1.getMemberId()+"\n Member Name : "+m1.getName()+"\n Age         : "+m1.getAge()+"\n Plan Chosen : "+m1.getPlan());
			System.out.println("--------------------------------------------------------------------------------------------------------------\n");
			
			return true;
		}
	}
	
	
}
