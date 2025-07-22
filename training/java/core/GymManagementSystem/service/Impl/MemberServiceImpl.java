package GymManagementSystem.service.Impl;

import java.util.List;

import GymManagementSystem.DAO.MemberDAO;
import GymManagementSystem.DAO.Impl.MemberDAOImpl;
import GymManagementSystem.models.Member;
import GymManagementSystem.service.MemberService;

public class MemberServiceImpl  implements MemberService{
	private MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public void addMember(Member member) {
		memberDAO.addMember(member);
	}

	@Override
	public void updateMember(Member member) {
		memberDAO.updateMember(member);
	}

	@Override
	public void deleteMember(int memberId) {
		memberDAO.deleteMember(memberId);
	}
	
	@Override
	public List<Member> viewMembers() {
		return memberDAO.getAllMembers();
	}
}
