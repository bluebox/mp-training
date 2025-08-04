package Service;

import domain.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberServiceInterface {

    boolean addMember(Member member) throws SQLException;

    boolean updateMember(Member member, int memberid) throws SQLException;

    List<Member> getAllMembers();

    Member getByid(int memberId);
}
