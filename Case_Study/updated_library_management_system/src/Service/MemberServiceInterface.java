package Service;

import domain.Member;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface MemberServiceInterface {

    boolean addMember(Member member) throws SQLIntegrityConstraintViolationException;

    boolean updateMember(Member member, int memberid);

    List<Member> getAllMembers();

    Member getByid(int memberId);
}
