package GymPackage;

import java.util.ArrayList;

public interface Interface_DAO {
	public ArrayList<Member> getMembers();
	public void addMember(Member member);
	public Member getMemberById(int id);
	public void saveMembersToFile();
	public void loadMembersFromFile();
	public ArrayList<MembershipPlan> getPlans();
	public void save();
	public void load();
}
