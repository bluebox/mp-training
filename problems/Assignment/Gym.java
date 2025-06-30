package Assignment;

import java.util.ArrayList;

public class Gym{
	private  ArrayList<Member> members=new ArrayList<>();
	private  ArrayList<MemberShipPlan> plans=new ArrayList<>();
	private String name;
	
	public Gym(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public Member findmemeber(String name,int Id) {
		Member required=null;	
		for(int i=0;i<members.size();i++) {
			if(members.get(i).getMemeberShipId()==Id) {
				required= members.get(i);
			}
		}
		if(required==null)throw new IndexOutOfBoundsException();
				
		return required;	
	}
	
	public String getMemebers(){
		StringBuilder response=new StringBuilder();
		for(int i=0;i<members.size();i++) {
			if(members.get(i).getPlan() != null) {
			response.append("The MemebershipID : "+members.get(i).getMemeberShipId()+"the plan is "+members.get(i).getPlan().getName()+"\n");
		}else{
			response.append("The MemebershipID : "+members.get(i).getMemeberShipId()+"the plan is not yet assigned\n");
		}
		}
		return response.toString();
	}
	
	public String getPlans(){
		StringBuilder response=new StringBuilder();
		for(int i=0;i<plans.size();i++) {
			response.append("The plan is "+plans.get(i).getName()+"its duration is "+plans.get(i).getDuration()+"its fee per year is "+plans.get(i).getFee()+"\n");
		};
		return response.toString();
	}
	
	public ArrayList<MemberShipPlan>  getPlansList(){
	return plans;
	}
	public void addMember(Member person ) {
		System.out.println("The new member is added");
		members.add(person);
	}
	
	public void addPlan(MemberShipPlan plan) {
		System.out.println("The new plan is added");
		plans.add(plan);
	}
	
	public void removeMember(int memeberShipID) {
		System.out.println("The member is being removed");
		for(Member member:members) {
			if(member.getMemeberShipId()==memeberShipID) {
				members.remove(member);
			}
		}
		System.out.println("The member is successfully removed");
	}
	
	
}

