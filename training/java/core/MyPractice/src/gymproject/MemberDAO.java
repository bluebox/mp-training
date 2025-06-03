package gymproject;

import java.util.*;

public class MemberDAO implements MemberRepository {
    private Map<String, Member> memberMap = new HashMap<>();

    @Override
    public void addMember(Member member) {
        memberMap.put(member.getMemberId(), member);
    }

    @Override
    public List<Member> getAllMembers() {
        return new ArrayList<>(memberMap.values());
    }

    @Override
    public Member getMemberById(String id) {
        return memberMap.get(id);
    }

    @Override
    public boolean existsById(String id) {
        return memberMap.containsKey(id);
    }
}