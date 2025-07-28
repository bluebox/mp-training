package Service;

import domain.Member;
import java.util.List;

public interface MemberServiceInterface {

    boolean addMember(Member member);

    boolean updateMember(Member member, int memberid);

    List<Member> getAllMembers();

    Member getByid(int memberId);
}
