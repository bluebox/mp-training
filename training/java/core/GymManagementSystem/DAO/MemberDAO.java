package GymManagementSystem.DAO;

import java.util.List;

import GymManagementSystem.models.Member;

public interface MemberDAO {
	
	void addMember(Member member);
	void updateMember(Member member);
	void deleteMember(int id);
	List<Member> getAllMembers();
}
