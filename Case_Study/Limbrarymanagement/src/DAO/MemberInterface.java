package DAO;



import domain.Member;
import java.util.List;

public interface MemberInterface {
    
    boolean addMember(Member member);

    boolean updateMember(Member member, int memberid);

    List<Member> getAllMembers();

    Member getById(int memberId);
}
