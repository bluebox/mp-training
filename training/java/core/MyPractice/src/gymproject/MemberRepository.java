package gymproject;

import java.util.List;

public interface MemberRepository {
    void addMember(Member member);
    List<Member> getAllMembers();
    Member getMemberById(String id);
    boolean existsById(String id);
}