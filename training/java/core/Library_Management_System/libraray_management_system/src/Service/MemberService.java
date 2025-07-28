package Service;

import java.util.List;
import DAO.Memberdao;
import domain.Member;

public class MemberService implements MemberServiceInterface {

    public Memberdao mdao = new Memberdao();

    public MemberService() {
        // default constructor
    }

    @Override
    public boolean addMember(Member member) {
        return mdao.addMember(member);
    }

    @Override
    public boolean updateMember(Member member, int memberid) {
        return mdao.updateMember(member, memberid);
    }

    @Override
    public List<Member> getAllMembers() {
        return mdao.getAllMembers();
    }

    @Override
    public Member getByid(int memberId) {
        return mdao.getById(memberId);
    }
}
