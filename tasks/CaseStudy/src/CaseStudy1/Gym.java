package CaseStudy1;

import java.util.ArrayList;

public class Gym {
	public ArrayList<Member> members = new  ArrayList<Member>();
	public ArrayList<MembershipPlan> plans = new ArrayList<MembershipPlan>();
	public void addMember(Member member) {
		members.add(member);
		}
	public void getAllMembers() {
		for(int i=0;i<members.size();i++) {
			members.get(i).showDetails();
		}
		}
	public int findMemberById(long id) {
		for(int i=0;i<members.size();i++) {
			if(members.get(i).getMemberId()==id)
				return i;
		}
		return -1;
	}
//	public void updateAge(int myId, int age) {
//		if(findMemberById(myId) > 0) {
//			members.get(findMemberById(myId)).setAge(age);;
//			System.out.println("Age updated successfully");
//		}
//		else {
//			System.out.println("No member with id "+myId);
//		}
//	}
	public void deleteMemberById(long myId) {
		if(findMemberById(myId) > 0) {
			members.remove(findMemberById(myId));
			System.out.println("Member deleted successfully");
		}
		else {
			System.out.println("No member with id "+myId);
		}
		
	}
	public void updateAge(long ageId, int updatedAge) {
		// TODO Auto-generated method stub
		if(findMemberById(ageId) > 0) {
			members.get(findMemberById(ageId)).setAge(updatedAge);;
			System.out.println("Age updated successfully");
		}
		else {
			System.out.println("No member with id "+ageId);
		}
		
	}
	
}
