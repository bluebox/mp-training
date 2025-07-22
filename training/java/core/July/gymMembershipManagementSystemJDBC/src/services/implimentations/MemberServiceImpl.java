package services.implimentations;

import java.util.List;

import dao.MemberDao;
import dao.implimentions.MemberDaoImpl;
import models.Member;
import services.MemberService;
import utils.CSVExporter;

public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao = new MemberDaoImpl();

	@Override
	public void addMember(Member member) {
		memberDao.addMember(member);
	}

	@Override
	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberDao.getAllMembers();
	}

	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}

	@Override
	public void deleteMember(int id) {
		memberDao.deleteMember(id);
	}

	@Override
	public void exportMembers() {

		List<Member> members = memberDao.getAllMembers();

		String[] headers = new String[] { "Id", "Name", "Age", "Contact" };

		CSVExporter.export(members, headers, s -> new String[] { String.valueOf(s.getId()), s.getName(),
				String.valueOf(s.getAge()), s.getContactDetails() }, "output/MembersDetails.csv");
	}
}
