import java.util.List;

public interface MemberDAO {
    boolean registerMember(Member member);
    boolean updateMemberDetails(Member member);
    Member getMemberById(int memberId);
    List<Member> getAllMembers();
}