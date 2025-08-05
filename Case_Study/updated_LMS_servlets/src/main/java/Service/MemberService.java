package Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import DAO.Memberdao;
import domain.Member;

public class MemberService implements MemberServiceInterface {

    public Memberdao mdao = new Memberdao();

    public MemberService() {
        // default constructor
    }

    @Override
    public boolean addMember(Member member) throws SQLIntegrityConstraintViolationException {
    	System.out.println(member);
    	try {
        return mdao.addMember(member);
    	}
    	catch(SQLIntegrityConstraintViolationException e) {
    		throw new SQLIntegrityConstraintViolationException();
    	}
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
