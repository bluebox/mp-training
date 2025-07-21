package GymPackage;

import java.util.ArrayList;

public class GymCollections implements Interface_DAO{

	private ArrayList<Member> members = new ArrayList<>();
	 private ArrayList<MembershipPlan> plans = new ArrayList<>();
	 
	 public GymCollections() {
	     plans.add(new MembershipPlan("Basic", 3, 100));
	     plans.add(new MembershipPlan("Premium", 6, 180));
	     plans.add(new MembershipPlan("Gold", 12, 300));
	 }
	    public void addMember(Member member) {
	        members.add(member);
	        System.out.println("Member added successfully.");
	    }


	    public ArrayList<Member> getMembers() {
	        return members;
	    }

	    public ArrayList<MembershipPlan> getPlans() {
	        return plans;
	    }

	    public Member getMemberById(int id) {
	        for (Member m : members) {
	            if (m.getMemberId() == id) {
	                return m;
	            }
	        }
	        return null;
	    }
		@Override
		public void saveMembersToFile() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void loadMembersFromFile() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void save() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void load() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void updateMemberPlan(Member member) {
			// TODO Auto-generated method stub
			
		}

}



