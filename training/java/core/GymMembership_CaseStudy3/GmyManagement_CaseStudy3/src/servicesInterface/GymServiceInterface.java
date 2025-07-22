package servicesInterface;

import java.sql.SQLException;
import java.util.List;

import model.Member;
import model.MembershipPlan;

public interface GymServiceInterface {

	public void addMember(String id,String name,int age) throws SQLException ;
	 public List<MembershipPlan> getAllPlans() throws SQLException ;
	 public boolean isMemberPresent(String id) throws SQLException;
	 public void assignPlan(String memberId,int planIndex) throws SQLException;
	 public List<Member> getAllMembers() throws SQLException;
	 public void showMemberDetails(Member member) throws SQLException ;
	 public void updateplan(String memberId,int newplanIndex) throws SQLException;
	 public void deleteMember(String memberId) throws SQLException;
	 
}
